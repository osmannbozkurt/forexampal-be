package com.fep.forexampal.common.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class CommonLocale {

    private static final Locale TR = new Locale("tr", "TR");

    public static Locale getTr() {
        return TR;
    }

}
