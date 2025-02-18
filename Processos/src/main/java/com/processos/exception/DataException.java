package com.processos.exception;

import com.processos.constants.Constantes;

public class DataException extends  RuntimeException{

    public DataException(String message){
        super(message);
    }

    public DataException(String message, Throwable value){
        super(message, value);
    }

    public DataException(){
        super(Constantes.DATA_NOT_VALID_EXCEPTION);
    }

}
