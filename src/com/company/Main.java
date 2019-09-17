package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "bye");
	    ExampleBean exampleBean = new ExampleBean(5, 6, "TEXT", map);
	    Converter converter = new Converter();
        byte[] bytes = converter.serialize(exampleBean);
        ExampleBean exampleBeanRestored = (ExampleBean) converter.deserialize(bytes);
        System.out.println(exampleBeanRestored.getNum());
        System.out.println(exampleBeanRestored.getText());
    }
}
