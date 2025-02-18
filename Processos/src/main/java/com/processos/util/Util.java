package com.processos.util;

import com.processos.dto.ProcessoDTO;
import com.processos.exception.DataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Util {

    public static Boolean checkNpu(String npu){
        return Pattern.matches("^[0-9]{7}[-][0-9]{2}[.][0-9]{4}[.][0-9]{1}[.][0-9]{2}[.][0-9]{4}?$", npu);
    }

    public static Date formatarData(String data) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(data);
    }

    public static void validarData(ProcessoDTO processoDTO){
        if(null == processoDTO.getDataVisualizacao() || null == processoDTO.getDataCadastro()){
            throw new DataException();
        }
    }

}
