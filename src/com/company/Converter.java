package com.company;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter implements SuperEncoder {

    private static final String ENCODING = "UTF-8";

    @Override
    public byte[] serialize(Object anyBean) {
        ArrayList<Byte> arrayBefore = new ArrayList<>();
        byte[] finalArray;
        String className = anyBean.getClass().getCanonicalName();
        try {
            arrayBefore.add(0, Integer.valueOf(className.getBytes(ENCODING).length).byteValue());
            for (byte b : className.getBytes(ENCODING)) {
                arrayBefore.add(b);
            }
            List<Object> fields = new LinkedList<>();
            for (Field field : anyBean.getClass().getDeclaredFields()) {
                fields.add(field.get(anyBean));
            }
            for (Object obj : fields) {
                int length = String.valueOf(obj).getBytes(ENCODING).length;
                arrayBefore.add(Integer.valueOf(length).byteValue());
                for (byte b : obj.toString().getBytes(ENCODING)) {
                    arrayBefore.add(b);
                }
            }
        } catch (IllegalAccessException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        finalArray = new byte[arrayBefore.size()];
        for(int i = 0; i < finalArray.length; i++){
            finalArray[i] = arrayBefore.get(i);
        }
        return finalArray;
    }

    @Override
    public Object deserialize(byte[] data) {
        int byteArrIterator = data[0];
        char[] charArray = new char[byteArrIterator];
        for (int i = 0; i < byteArrIterator; i++) {
            charArray[i] = (char) data[i + 1];
        }
        try {
            Class<?> aClass = Class.forName(String.valueOf(charArray));
            Constructor<?>[] constructor = aClass.getConstructors();
            Map<String, Object> var = getValuesForFields(byteArrIterator, data, aClass);
            Object obj;
            if(constructor.length > 1 && constructor[1].getParameterTypes().length != 0) {
                obj = constructor[1].newInstance(var.get("num"), var.get("num1"), var.get("text"), var.get("tempMap"));
            }
            else{
                obj = constructor[0].newInstance(var.get("num"), var.get("num1"), var.get("text"), var.get("tempMap"));
            }
            return obj;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> getValuesForFields(int byteArrIterator, byte[] byteArray, Class aClass) {
        Map<String, Object> valuesForFields = new HashMap<>();
        for (Field field : aClass.getDeclaredFields()) {
            int length = byteArray[++byteArrIterator];
            char[] chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = (char) byteArray[++byteArrIterator];
            }
            switch (field.getType().getName()) {
                case ("int"):
                    int arg1 = Integer.parseInt(new String(chars));
                    valuesForFields.put(field.getName(), arg1);
                    break;
                case ("double"):
                    double arg2 = Double.parseDouble(new String(chars));
                    valuesForFields.put(field.getName(), arg2);
                    break;
                case ("char"):
                    char arg3 = chars[0];
                    valuesForFields.put(field.getName(), arg3);
                    break;
                case ("boolean"):
                    boolean arg4 = Boolean.parseBoolean(new String(chars));
                    valuesForFields.put(field.getName(), arg4);
                    break;
                case ("java.util.Map"):
                    Map<String, String> map = new HashMap<>();
                    Pattern pattern = Pattern.compile("(\\w+)=(\\w+)");
                    Matcher matcher = pattern.matcher(new String(chars));
                    matcher.find();
                    map.put(matcher.group(1), matcher.group(2));
                    valuesForFields.put(field.getName(), map);
                    break;
                case ("java.util.Set"):
                    Set<String> set = new HashSet<>();
                    set.add(new String(chars));
                    valuesForFields.put(field.getName(), set);
                    break;
                case ("java.util.List"):
                    List<String> list = new LinkedList<>();
                    list.add(new String(chars));
                    valuesForFields.put(field.getName(), list);
                    break;
                default:
                    valuesForFields.put(field.getName(), boxClass(field, new String(chars)));
            }
        }
        return valuesForFields;
    }

    private Object boxClass(Field field, String chars){
        switch (field.getType().getName()) {
            case ("java.lang.String"):
                return chars;
            case ("java.math.BigDecimal"):
                return BigDecimal.valueOf(Double.parseDouble(chars));
            case ("java.time.Instant"):
                return Instant.parse(chars);
            case ("java.lang.Long"):
                return Long.parseLong(chars);
            case ("java.lang.Float"):
                return Float.parseFloat(chars);
            case ("java.lang.Double"):
                return Double.parseDouble(chars);
            case ("java.lang.Character"):
                return chars.charAt(0);
            case ("java.lang.Boolean"):
                return Boolean.parseBoolean(chars);
            case ("java.lang.Integer"):
                return Integer.parseInt(chars);
        }
        return null;
    }
}