package com.lujw.fortunetelling.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private String year;

    private String month;

    private String day;

    private String time;
    //上卦
    private Up up;
    //下卦
    private Down down;
    //宫像
    private Map<String, Element> elementMap;

}
