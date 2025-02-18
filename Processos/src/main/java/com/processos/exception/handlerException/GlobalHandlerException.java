package com.processos.exception.handlerException;

import com.processos.constants.Constantes;
import com.processos.dto.response.ErrorResponseDTO;
import com.processos.exception.DataException;
import com.processos.exception.NpuException;
import com.processos.exception.ProcessoAlreadyExistsException;
import com.processos.exception.ProcessoNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    /**
     * Method responsible for validate DTO
     * @param ex the exception to handle
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        List<ErrorResponseDTO> listError = new ArrayList<>();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            String errorCode = String.valueOf(status.value());
            ErrorResponseDTO errorResponseDto =
                    ErrorResponseDTO.builder()
                            .errorCode(errorCode)
                            .message(validationMsg + " " + fieldName)
                            .build();
            listError.add(errorResponseDto);
        });
        return new ResponseEntity<>(listError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Method responsible for manage ProcessoNotFoundException
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(ProcessoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProcessoNotFoundException(ProcessoNotFoundException exception,
                                                                            WebRequest webRequest){
        ErrorResponseDTO errorResponseDto =
                ErrorResponseDTO.builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .message(exception.getMessage())
                        .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }

    /**
     * Method responsible for manage NpuException
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(NpuException.class)
    public ResponseEntity<ErrorResponseDTO> handleNpuExceptionException(NpuException exception,
                                                                        WebRequest webRequest){
        ErrorResponseDTO errorResponseDto =
                ErrorResponseDTO.builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .message(exception.getMessage())
                        .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }

    /**
     * Method responsible for manage ProcessoAlreadyExistsException
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(ProcessoAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleProcessoAlreadyExistsException(ProcessoAlreadyExistsException exception,
                                                                                 WebRequest webRequest){
        ErrorResponseDTO errorResponseDto =
                ErrorResponseDTO.builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .message(exception.getMessage())
                        .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }

    /**
     * Method responsible for manage DataException
     * @param exception
     * @param webRequest
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(DataException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataException(DataException exception,
                                                                WebRequest webRequest){
        ErrorResponseDTO errorResponseDto =
                ErrorResponseDTO.builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .message(exception.getMessage())
                        .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }

    /**
     * Method responsible for manage Exception
     * @param exception
     * @param webRequest
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest){
        ErrorResponseDTO errorResponseDto =
                ErrorResponseDTO.builder()
                        .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .message(Constantes.GENERAL_FIELD_EXCEPTION)
                        .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
