package com.lujw.fortunetelling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Element {

    List<String> upCodes;

    List<String> downCodes;


}
