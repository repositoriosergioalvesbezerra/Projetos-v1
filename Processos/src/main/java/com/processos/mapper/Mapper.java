package com.processos.mapper;

import com.processos.dto.ProcessoDTO;
import com.processos.entity.Processo;

public class Mapper {

    /**
     * Method responsible for convert ProcessoDTO to Processo
     * @param processoDTO
     * @return Processo
     */
    public static Processo convertToProcesso(ProcessoDTO processoDTO){
        return Processo.builder()
            .id(processoDTO.getId())
            .npu(processoDTO.getNpu())
            .dataCadastro(processoDTO.getDataCadastro())
            .dataVisualizacao(processoDTO.getDataVisualizacao())
            .municipio(processoDTO.getMunicipio())
            .uf(processoDTO.getUf())
            .pdf(processoDTO.getPdf())
                .visibilidade(processoDTO.getVisibilidade())
            .build();
    }

    /**
     * Method responsible for convert Processo to ProcessoDTO
     * @param processo
     * @return ProcessoDTO
     */
    public static ProcessoDTO convertToProcessoDTO(Processo processo){
    return ProcessoDTO.builder()
        .id(processo.getId())
        .npu(processo.getNpu())
        .dataCadastro(processo.getDataCadastro())
        .dataVisualizacao(processo.getDataVisualizacao())
        .municipio(processo.getMunicipio())
        .uf(processo.getUf())
        .pdf(processo.getPdf())
        .visibilidade(processo.getVisibilidade())
        .build();
    }

    /**
     * Method responsible for update Processo
     * @param processoDTO
     * @return Processo
     */
    public static void updateProcesso(Processo processo, ProcessoDTO processoDTO){

        processo.setNpu((processoDTO.getNpu() != null && !processoDTO.getNpu().isEmpty())? processoDTO.getNpu() : processo.getNpu());
        processo.setDataCadastro(processoDTO.getDataCadastro() != null ? processoDTO.getDataCadastro() : processo.getDataCadastro());
        processo.setDataVisualizacao(processoDTO.getDataVisualizacao() != null ? processoDTO.getDataVisualizacao() : processo.getDataVisualizacao());
        processo.setMunicipio((processoDTO.getMunicipio() != null && !processoDTO.getMunicipio().isEmpty()) ? processoDTO.getMunicipio() : processo.getMunicipio());
        processo.setUf((processoDTO.getUf() != null && !processoDTO.getUf().isEmpty()) ? processoDTO.getUf() : processo.getUf());
        processo.setVisibilidade((processoDTO.getVisibilidade() != null && !processoDTO.getVisibilidade().isEmpty()) ? processoDTO.getVisibilidade() : processo.getVisibilidade());

    }

}
