package com.company;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleBean{

    private int num;
    private byte num1;
    private short num2;
    private long num3;
    private float num4;
    private double num5;
    private boolean bool;
    private char charSimple;
    private Number number;
    private String text;
    private Character character;
    private Instant time;
//    private BigDecimal bigDecimal;
    private Map<?, ?> map;
    private List<?> list;
    private Set<?> set;


    public ExampleBean(){    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNum1(byte num1) {
        this.num1 = num1;
    }

    public void setNum2(short num2) {
        this.num2 = num2;
    }

    public void setNum3(long num3) {
        this.num3 = num3;
    }

    public void setNum4(float num4) {
        this.num4 = num4;
    }

    public void setNum5(double num5) {
        this.num5 = num5;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void setCharSimple(char charSimple) {
        this.charSimple = charSimple;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setMap(Map<?, ?> map) {
        this.map = map;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public void setSet(Set<?> set) {
        this.set = set;
    }

    public int getNum() {
        return num;
    }

    public byte getNum1() {
        return num1;
    }

    public short getNum2() {
        return num2;
    }

    public long getNum3() {
        return num3;
    }

    public float getNum4() {
        return num4;
    }

    public double getNum5() {
        return num5;
    }

    public boolean isBool() {
        return bool;
    }

    public char getCharSimple() {
        return charSimple;
    }

    public Number getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Character getCharacter() {
        return character;
    }

    public Instant getTime() {
        return time;
    }

    public Map<?, ?> getMap() {
        return map;
    }

    public List<?> getList() {
        return list;
    }

    public Set<?> getSet() {
        return set;
    }
}
