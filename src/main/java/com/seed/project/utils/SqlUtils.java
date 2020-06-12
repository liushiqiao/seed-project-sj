package com.seed.project.utils;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {

    private static final String PER_CENT = "%";

    private SqlUtils() {
    }

    public static String spliceAllFuzzyQuery(String keyWord) {
        if (StringUtils.isBlank(keyWord)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PER_CENT).append(keyWord).append(PER_CENT);
        return stringBuilder.toString();
    }

    public static String spliceLeftFuzzyQuery(String keyWord) {
        if (StringUtils.isBlank(keyWord)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PER_CENT).append(keyWord);
        return stringBuilder.toString();
    }

    public static String spliceRightFuzzyQuery(String keyWord) {

        if (StringUtils.isBlank(keyWord)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(keyWord).append(PER_CENT);
        return stringBuilder.toString();
    }
}
