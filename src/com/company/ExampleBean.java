package com.company;

import java.util.HashMap;
import java.util.Map;

public class ExampleBean{

    public Integer num1;
    public int num = 0;
    public String text = "";
    public Map<String, String> tempMap = new HashMap<>();



    public ExampleBean(int num, Integer num1, String text, Map<String, String> tempMap){
        this.num1 = num1;
        this.tempMap.putAll(tempMap);
        this.num = num;
        this.text = text;
    }

    public ExampleBean(){

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
