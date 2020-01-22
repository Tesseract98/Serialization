package com.company;

import java.io.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Converter implements SuperEncoder {

//    private static final String ENCODING = "UTF-8";

    @Override
    public byte[] serialize(Object anyBean) {
        ExampleBean exampleBean = (ExampleBean)anyBean;
        try(ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(exampleBean.getMap());
            out.writeObject(exampleBean.getList());
            out.writeObject(exampleBean.getSet());
            out.writeObject(exampleBean.getNumber());
            out.writeUTF(exampleBean.getText());
            out.writeObject(exampleBean.getTime());
            out.writeObject(exampleBean.getCharacter());
            out.writeInt(exampleBean.getNum());
            out.writeByte(exampleBean.getNum1());
            out.writeShort(exampleBean.getNum2());
            out.writeLong(exampleBean.getNum3());
            out.writeFloat(exampleBean.getNum4());
            out.writeDouble(exampleBean.getNum5());
            out.writeBoolean(exampleBean.isBool());
            out.writeChar(exampleBean.getCharSimple());
            out.close();
            return byteOut.toByteArray();
        }
        catch (IOException exc){
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] data) {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        ExampleBean exampleBean = new ExampleBean();
        try (ObjectInputStream in = new ObjectInputStream(byteIn);){
            exampleBean.setMap((Map<?, ?>)in.readObject());
            exampleBean.setList((List<?>)in.readObject());
            exampleBean.setSet((Set<?>)in.readObject());
            exampleBean.setNumber((Number) in.readObject());
            exampleBean.setText(in.readUTF());
            exampleBean.setTime((Instant) in.readObject());
            exampleBean.setCharacter((Character) in.readObject());
            exampleBean.setNum(in.readInt());
            exampleBean.setNum1(in.readByte());
            exampleBean.setNum2(in.readShort());
            exampleBean.setNum3(in.readLong());
            exampleBean.setNum4(in.readFloat());
            exampleBean.setNum5(in.readDouble());
            exampleBean.setBool(in.readBoolean());
            exampleBean.setCharSimple(in.readChar());
            byteIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exampleBean;
    }
}