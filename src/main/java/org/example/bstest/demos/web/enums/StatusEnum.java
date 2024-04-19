package org.example.bstest.demos.web.enums;

public enum StatusEnum {
    SUCESS("200"),FAIL("-1");

    final String code;

    private StatusEnum(String code) {
        this.code = code;
    }

}
