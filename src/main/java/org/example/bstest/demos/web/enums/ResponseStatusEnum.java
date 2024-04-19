package org.example.bstest.demos.web.enums;

public enum ResponseStatusEnum {
    SUCESS("200"),FAIL("-1");

    final String code;

    private ResponseStatusEnum(String code) {
        this.code = code;
    }

}
