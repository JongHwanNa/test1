package com.mobon.kafka.consumer.model;

import lombok.Data;

@Data
public class CookieVo {

    private String  key;
    private String  value;
    private Long expire;
    private String  name;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CookieVo{");
        sb.append('}');
        return sb.toString();
    }
}
