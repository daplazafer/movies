package com.dpf.movies.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class Hasher {

    public String from(String value){
        return from(value.getBytes());
    }

    public String from(byte[] bytes){
        return DigestUtils.sha512Hex(bytes);
    }

}
