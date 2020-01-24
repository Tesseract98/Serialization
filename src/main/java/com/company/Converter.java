package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Converter implements SuperEncoder {

    private static final String ENCODING = "UTF-8";

    @Override
    public byte[] serialize(Object anyBean) {
        List<Byte> arrayBefore = new ArrayList<>();
        ExampleBean exampleBean = (ExampleBean) anyBean;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            String className = anyBean.getClass().getCanonicalName();
//            arrayBefore.add(Integer.valueOf(className.getBytes(ENCODING).length).byteValue());
            Field[] fields = exampleBean.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                byte[] bytes = new byte[1];
                try {
                    if(field.get(exampleBean) instanceof  List || field.get(exampleBean) instanceof  Set
                            || field.get(exampleBean) instanceof  Map) {
                        bytes = objectMapper.writeValueAsBytes(field.get(exampleBean));
                    }
                    else {
                        bytes = field.get(exampleBean).toString().getBytes();
                    }
                }
                catch (NullPointerException exc ){
                    bytes = "null".getBytes();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                for(byte b: bytes){
                    arrayBefore.add(b);
                }
                arrayBefore.add((byte)'\uE000');
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        byte[] finalArray = new byte[arrayBefore.size()];
        for(int i = 0; i < finalArray.length; i++){
            finalArray[i] = arrayBefore.get(i);
        }
        return finalArray;
    }

    @Override
    public Object deserialize(byte[] data) {
        ExampleBean exampleBean = new ExampleBean();
        Field[] fields = exampleBean.getClass().getDeclaredFields();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Byte> varList = new ArrayList<>();
        int i = 0;
        for(byte b: data){
            if(b == 0){
                fields[i].setAccessible(true);
                StringBuilder varStr = new StringBuilder();
                for(Byte vb: varList){
                    varStr.append((char)vb.byteValue());
                }
                try {
                    String builderResult = varStr.toString();
                    if(!builderResult.equals("null")) {
                        switch (i) {
                            case 0:
                                fields[i].set(exampleBean, Integer.valueOf(builderResult));
                                break;
                            case 1:
                                fields[i].set(exampleBean, Byte.valueOf(builderResult));
                                break;
                            case 2:
                                fields[i].set(exampleBean, Short.valueOf(builderResult));
                                break;
                            case 3:
                                fields[i].set(exampleBean, Long.valueOf(builderResult));
                                break;
                            case 4:
                                fields[i].set(exampleBean, Float.valueOf(builderResult));
                                break;
                            case 5:
                                fields[i].set(exampleBean, Double.valueOf(builderResult));
                                break;
                            case 6:
                                fields[i].set(exampleBean, Boolean.valueOf(builderResult));
                                break;
                            case 7:
                                fields[i].set(exampleBean, builderResult.charAt(0));
                                break;
                            case 8:
                                fields[i].set(exampleBean, builderResult);
                                break;
                            case 9:
                                fields[i].set(exampleBean, builderResult.charAt(0));
                                break;
                            case 10:
                                fields[i].set(exampleBean, Instant.parse(builderResult));
                                break;
                            case 11:
                                fields[i].set(exampleBean, new BigDecimal((builderResult)));
                                break;
                            case 12:
                                fields[i].set(exampleBean, objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                                        .readValue(builderResult, Map.class));
                                break;
                            case 13:
                                fields[i].set(exampleBean, objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                                        .readValue(builderResult, List.class));
                                break;
                            case 14:
                                fields[i].set(exampleBean, objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                                        .readValue(builderResult, Set.class));
                                break;
                        }
                    }
                } catch (IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }
                varList.clear();
                i++;
                continue;
            }
            varList.add(b);
        }
        return exampleBean;
    }

}