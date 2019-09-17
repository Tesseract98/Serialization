package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "bye");
	    ExampleBean exampleBean = new ExampleBean(5, 6, "TEXT", map);
	    Converter converter = new Converter();
        byte[] bytes = converter.serialize(exampleBean);
//        for(byte i : bytes) {
//            System.out.println(i);
//        }
        ExampleBean exampleBeanRestored = (ExampleBean) converter.deserialize(bytes);
        System.out.println(exampleBeanRestored.getNum());
        System.out.println(exampleBeanRestored.getText());
    }
}
