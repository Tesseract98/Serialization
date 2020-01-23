package com.company;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConverter {

    @Test
    public void testSerializeDeserialize(){
        Converter converter = new Converter();
        ExampleBean exampleBean = new ExampleBean();

        Map<String, String> map = new HashMap<>();
        map.put("hello", "bye");
        Set<StringBuilder> set = new HashSet<>();
        set.add(new StringBuilder("beautiful world"));
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("12.5E+100"));

        exampleBean.setList(list);
        exampleBean.setSet(set);
        exampleBean.setMap(map);
        exampleBean.setTime(Clock.systemUTC().instant());
        exampleBean.setCharacter('5');
        exampleBean.setText("hello");
        exampleBean.setNumber(new AtomicInteger(122));
        exampleBean.setCharSimple('5');
        exampleBean.setBool(true);
        exampleBean.setNum5((float) 123E-5);
        exampleBean.setNum4((float) 123E-5);
        exampleBean.setNum3(1234241412);
        exampleBean.setNum2((short) 123);
        exampleBean.setNum1((byte) 125);
        exampleBean.setNum(1_3____2); // 132

        byte[] bytes = converter.serialize(exampleBean);
        ExampleBean exampleBean2 = (ExampleBean) converter.deserialize(bytes);

        assertAll(
                () -> assertEquals(exampleBean.getSet().toString(), exampleBean2.getSet().toString()),
                () -> assertEquals(exampleBean.getMap().toString(), exampleBean2.getMap().toString()),
                () -> assertEquals(exampleBean.getList().toString(), exampleBean2.getList().toString()),
                () -> assertEquals(exampleBean.getNumber().toString(), exampleBean2.getNumber().toString()),
                () -> assertEquals(exampleBean.getText(), exampleBean2.getText()),
                () -> assertEquals(exampleBean.getTime(), exampleBean2.getTime()),
                () -> assertEquals(exampleBean.getCharacter(), exampleBean2.getCharacter()),
                () -> assertEquals(exampleBean.getNum(), exampleBean2.getNum()),
                () -> assertEquals(exampleBean.getNum1(), exampleBean2.getNum1()),
                () -> assertEquals(exampleBean.getNum2(), exampleBean2.getNum2()),
                () -> assertEquals(exampleBean.getNum3(), exampleBean2.getNum3()),
                () -> assertEquals(exampleBean.getNum4(), exampleBean2.getNum4()),
                () -> assertEquals(exampleBean.getNum5(), exampleBean2.getNum5()),
                () -> assertEquals(exampleBean.isBool(), exampleBean2.isBool()),
                () -> assertEquals(exampleBean.getCharSimple(), exampleBean2.getCharSimple())
        );

    }

}
