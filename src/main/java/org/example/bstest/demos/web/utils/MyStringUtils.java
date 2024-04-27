package org.example.bstest.demos.web.utils;

public class MyStringUtils {


    static final String CHARACTER_HEAD_AND_TAIL = "'";

    //拼接表名字符串
    public static String concatenationWithQuotationMarks(String string) {
        return CHARACTER_HEAD_AND_TAIL + string + CHARACTER_HEAD_AND_TAIL;
    }



}
