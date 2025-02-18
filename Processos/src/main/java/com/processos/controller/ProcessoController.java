package com.processos.controller;

import com.processos.dto.ProcessoDTO;
import com.processos.dto.ProcessoPageDTO;
import com.processos.dto.response.ErrorResponseDTO;
import com.processos.service.IProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@Validated
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@RequestMapping(path = "/v1/api/processos")
@RequiredArgsConstructor
public class ProcessoController {

    private final IProcessoService processoService;

    /**
     * Endpoint responsible for get all processos
     * @return List<Processo>
     */
    @Operation(
            summary = "All Processos",
            description = "Endpoint for get all processos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ProcessoPageDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping(path = "/all")
    public ResponseEntity<ProcessoPageDTO> getAllProcessos(@RequestParam(name = "page", defaultValue = "0") @PositiveOrZero String numeroPagina,
                                                           @RequestParam(name = "size", defaultValue = "5") @Positive @Max(1000) String tamanhoPagina){
        return ResponseEntity.status(HttpStatus.OK).body(processoService.getAllProcessos(numeroPagina, tamanhoPagina));
    }

    /**
     * Endpoint responsible for get processo by npu
     * @return ProcessoDTO
     */
    @Operation(
            summary = "Processos by NPU",
            description = "Endpoint for get processos by npu"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ProcessoPageDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping(path = "/{npu}")
    public ResponseEntity<ProcessoPageDTO> getProcessoByNpu(@PathVariable String npu){
        return ResponseEntity.status(HttpStatus.OK).body((ProcessoPageDTO) processoService.getProcessoByNpu(npu, ""));
    }

    /**
     * Endpoint responsible for create processo
     * @param processoDTO
     * @return String
     */
    @Operation(
            summary = "Create Processos",
            description = "Endpoint for create processos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping(path = "/cadastros", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> cadastrarProcesso(@Valid @RequestBody ProcessoDTO processoDTO){
        return processoService.cadastrarProcesso(processoDTO);
    }

    /**
     * Endpoint responsible for update processo
     * @param processoDTO
     * @return
     */
    @Operation(
            summary = "Update Processos",
            description = "Endpoint for update processos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping(path = "/atualizar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> atualizarProcesso(@Valid @RequestBody ProcessoDTO processoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(processoService.atualizarProcesso(processoDTO));
    }

    /**
     * Endpoint responsible for update visualizações
     * @param npu
     * @return String
     * @throws ParseException
     */
    @Operation(
            summary = "Update visualização of Processos",
            description = "Endpoint for update visualização processos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping(path = "/atualizar/visualizacoes/{npu}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> atualizarVisualizacoes(@PathVariable String npu) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(processoService.atualizarVisualizacao(npu));
    }

    /**
     * Endpoint responsible for delete processo
     * @param npu
     * @return
     */
    @Operation(
            summary = "Delete Processos",
            description = "Endpoint for delete processos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping(path = "/{npu}")
    public ResponseEntity<String> deleteProcesso(@Valid @PathVariable String npu){
        return ResponseEntity.status(HttpStatus.OK).body(processoService.deleteProcesso(npu));
    }


}
