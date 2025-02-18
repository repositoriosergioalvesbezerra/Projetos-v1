package com.processos.exception;

import com.processos.constants.Constantes;

public class ProcessoNotFoundException extends RuntimeException{

    public ProcessoNotFoundException(String message){
        super(message);
    }

    public ProcessoNotFoundException(String message, Throwable value){
        super(message, value);
    }

    public ProcessoNotFoundException(){
        super(Constantes.PROCESSO_NOT_FOUND_EXCEPTION);
    }

}
