package com.processos.exception;

import com.processos.constants.Constantes;

public class NpuException extends RuntimeException{

    public NpuException(String message){
        super(message);
    }

    public NpuException(String message, Throwable value){
        super(message, value);
    }

    public NpuException(){
        super(Constantes.NPU_NOT_VALID_EXCEPTION);
    }
}
