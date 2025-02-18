package com.processos.service;

import com.processos.dto.ProcessoDTO;
import com.processos.dto.ProcessoPageDTO;
import org.springframework.http.ResponseEntity;
import java.text.ParseException;


public interface IProcessoService {

    /**
     * Method responsible for get all processos
     * @param numeroPagina
     * @param tamanhoPagina
     * @return ProcessoPageDTO
     */
    ProcessoPageDTO getAllProcessos(String numeroPagina, String tamanhoPagina);

    /**
     * Method responsible for get processo by npu
     * @return Object
     */
    Object getProcessoByNpu(String npu, String flag);

    /**
     * Method responsible for cadastrar processo
     * @param processoDTO
     * @return String
     */
    ResponseEntity<String> cadastrarProcesso(ProcessoDTO processoDTO);

    /**
     * Method responsible for update processo
     * @param processoDTO
     * @return String
     */
    String atualizarProcesso(ProcessoDTO processoDTO);

    /**
     * Method responsible for delete processo
     * @param npu
     * @return
     */
    String deleteProcesso(String npu);

    /**
     * Method responsible for update Visualização
     * @param npu
     * @return String
     */
    String atualizarVisualizacao(String npu) throws ParseException;


}
