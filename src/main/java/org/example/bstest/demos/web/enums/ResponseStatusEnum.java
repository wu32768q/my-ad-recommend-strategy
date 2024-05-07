package org.example.bstest.demos.web.enums;

import java.util.Objects;

public enum ResponseStatusEnum {
    SUCESS("200"),
    FAIL("-1");

    final String code;

    private ResponseStatusEnum(String code) {
        this.code = code;
    }

    public static Boolean compare(ResponseStatusEnum o1, ResponseStatusEnum o2) {
        if(Objects.isNull(o1) || Objects.isNull(o2)) {
            return false;
        }
        return o1.equals(o2);
    }

}
