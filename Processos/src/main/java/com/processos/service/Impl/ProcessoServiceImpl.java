package com.processos.service.Impl;

import com.processos.constants.Constantes;
import com.processos.dto.ProcessoDTO;
import com.processos.dto.ProcessoPageDTO;
import com.processos.entity.Processo;
import com.processos.exception.NpuException;
import com.processos.exception.ProcessoAlreadyExistsException;
import com.processos.exception.ProcessoNotFoundException;
import com.processos.mapper.Mapper;
import com.processos.repository.IProcessoRepository;
import com.processos.service.IProcessoService;
import com.processos.util.Util;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessoServiceImpl implements IProcessoService {

    private final IProcessoRepository processoRepository;


    /**
     * Method responsible for get all processos
     * @param numeroPagina
     * @param tamanhoPagina
     * @return ProcessoPageDTO
     */
    @Override
    @Transactional(readOnly = true)
    public ProcessoPageDTO getAllProcessos(@PositiveOrZero String numeroPagina, @Positive String tamanhoPagina) {
        Page<Processo> page = processoRepository.findAll(PageRequest.of(Integer.parseInt(numeroPagina), Integer.parseInt(tamanhoPagina), Sort.by("npu").ascending()));
        List<ProcessoDTO> processos = page.get().map(value -> Mapper.convertToProcessoDTO(value)).collect(Collectors.toList());
        return new ProcessoPageDTO(processos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * Method responsible for get processo by npu
     * @param npu
     * @return Object
     */
    @Override
    @Transactional(readOnly = true)
    public Object getProcessoByNpu(String npu, String flag) {
        isNpuValid(npu);
        Optional<Processo> processo = Optional.ofNullable(processoRepository.findByNpu(npu).orElseThrow(() -> {
            throw new ProcessoNotFoundException();
        }));
        if(flag.equals(Constantes.PROCESSO_UPDATED)){
            return processo.get();
        }
        return new ProcessoPageDTO(Arrays.asList(Mapper.convertToProcessoDTO(processo.get())), 1,1);
    }

    /**
     * Method responsible for cadastrar processo
     * @param processoDTO
     * @return String
     */
    @Override
    @Transactional
    public ResponseEntity<String> cadastrarProcesso(ProcessoDTO processoDTO) {
        try{
            Util.validarData(processoDTO);
            this.getProcessoByNpu(processoDTO.getNpu(), "");
            throw new ProcessoAlreadyExistsException();
        } catch(ProcessoNotFoundException ex){
            Processo processo = Mapper.convertToProcesso(processoDTO);
            processo.setId(UUID.randomUUID().toString());
            processoRepository.save(processo);
            return ResponseEntity.status(HttpStatus.CREATED).header("id_processo", processo.getId()).body(Constantes.PROCESSO_CREATED);
        } catch (ProcessoAlreadyExistsException ex) {
            throw new ProcessoAlreadyExistsException();
        }
    }

    /**
     * Method responsible for update processo
     * @param processoDTO
     * @return String
     */
    @Override
    @Transactional
    public String atualizarProcesso(ProcessoDTO processoDTO) {
        Processo processo = (Processo) this.getProcessoByNpu(processoDTO.getNpu(), Constantes.PROCESSO_UPDATED);
        Mapper.updateProcesso(processo, processoDTO);
        processoRepository.save(processo);
        return Constantes.PROCESSO_UPDATED;
    }

    /**
     * Method responsible for delete processo
     * @param npu
     * @return
     */
    @Override
    @Transactional
    public String deleteProcesso(String npu) {
        ProcessoDTO processoDTO = ProcessoDTO.builder().npu(npu).build();
        Processo processo = (Processo) this.getProcessoByNpu(processoDTO.getNpu(), Constantes.PROCESSO_UPDATED);
        processoRepository.delete(processo);
        return Constantes.PROCESSO_DELETED;
    }

    /**
     * Method responsible for update Visualização
     * @param npu
     * @return String
     */
    @Override
    @Transactional
    public String atualizarVisualizacao(String npu) throws ParseException {
    ProcessoDTO processoDTO =
        ProcessoDTO.builder()
            .npu(npu)
            .dataVisualizacao(Util.formatarData(String.valueOf(LocalDate.now())))
            .visibilidade("S")
            .build();
        return this.atualizarProcesso(processoDTO);
    }

    /**
     * Method responsible for check if MPU is valid
     * @param npu
     */
    private void isNpuValid(String npu){
        boolean isValid = Util.checkNpu(npu);
        if(!isValid){
            throw new NpuException();
        }
    }

}
