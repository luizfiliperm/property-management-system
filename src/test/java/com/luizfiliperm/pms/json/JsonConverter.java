package com.luizfiliperm.pms.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    public static String convertToJson(final Object o){
        try{
            return new ObjectMapper().writeValueAsString(o);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
