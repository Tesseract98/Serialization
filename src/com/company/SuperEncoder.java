package com.company;

public interface SuperEncoder {
    byte[] serialize(Object anyBean);
    Object deserialize(byte[] data);
}
