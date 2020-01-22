package com.company;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ExampleBean exampleBean = new ExampleBean();
        Map<String, String> map = new HashMap<>();
        map.put("hello", "bye");
        exampleBean.setMap(map);
        exampleBean.setText("hello");
        exampleBean.setNumber(new BigDecimal("12.5E+100"));
        exampleBean.setTime(Clock.systemUTC().instant());

	    Converter converter = new Converter();
        byte[] bytes = converter.serialize(exampleBean);
        if(bytes != null) {
            ExampleBean exampleBeanRestored = (ExampleBean) converter.deserialize(bytes);
            System.out.println(exampleBeanRestored.getMap());
            System.out.println(exampleBeanRestored.getList());
            System.out.println(exampleBeanRestored.getSet());
            System.out.println(exampleBeanRestored.getNumber());
            System.out.println(exampleBeanRestored.getText());
            System.out.println(exampleBeanRestored.getTime());
            System.out.println(exampleBeanRestored.getCharacter());
            System.out.println(exampleBeanRestored.getNum());
            System.out.println(exampleBeanRestored.getNum1());
            System.out.println(exampleBeanRestored.getNum2());
            System.out.println(exampleBeanRestored.getNum3());
            System.out.println(exampleBeanRestored.getNum4());
            System.out.println(exampleBeanRestored.getNum5());
            System.out.println(exampleBeanRestored.isBool());
            System.out.println(exampleBeanRestored.getCharSimple());
        }
        else {
            System.err.println("Something wrong");
        }
    }

}
