package com.processos.exception;

import com.processos.constants.Constantes;

public class ProcessoAlreadyExistsException extends RuntimeException{

    public ProcessoAlreadyExistsException(String message){
        super(message);
    }

    public ProcessoAlreadyExistsException(String message, Throwable value){
        super(message, value);
    }

    public ProcessoAlreadyExistsException(){
        super(Constantes.PROCESSO_ALREADY_EXISTS_EXCEPTION);
    }
}
