package com.processos.dto;

import java.util.List;

public record ProcessoPageDTO (List<ProcessoDTO> processos,
                               long totalProcessos,
                               int totalPaginas) {}
