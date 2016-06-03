package com.springapp.mvc.model;

/**
 * 时间段类型
 * Created by pengfarui on 2016/5/18.
 */
public enum PeriodType {
    /**
     * 按两分钟
     */
    TWO_MINUTE("按两分钟"),
    /**
     * 按小时
     */
    HOUR("按小时"),
    /**
     * 按日
     */
    DAILY("按日"),
    /**
     * 按周
     */
    WEEKLY("按周"),
    /**
     * 按月
     */
    MONTHLY("按月"),
    /**
     * 按年
     */
    YEARLY("按年");

    private String value;

    PeriodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
