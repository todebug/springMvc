package com.springapp.mvc.model;

/**
 * 保险对接平台相关字段
 * Created by pengfarui on 2016/5/20.
 */
public enum IisField {
    /**
     * ID
     */
    id("ID"),
    /**
     * 统计时间段
     */
    period("统计时间段"),
    /**
     * 保险公司
     */
    insurer("保险公司"),
    /**
     * 承保地区
     */
    province("承保地区"),
    /**
     * 交易类型
     */
    interfaceType("交易类型"),
    /**
     * 总交易数
     */
    totalCount("总交易数"),
    /**
     * 成功数
     */
    successCount("成功数"),
    /**
     * 失败数
     */
    failureCount("失败数"),
    /**
     * 最大时长
     */
    maxDuration("最大时长"),
    /**
     * 最小时长
     */
    minDuration("最小时长"),
    /**
     * 平均时长
     */
    averageDuration("平均时长"),
    /**
     * 90%时长
     */
    ninetyDuration("90%时长"),
    /**
     * 创建时间
     */
    createTime("创建时间");

    /**
     * 值描述
     */
    private String value;

    IisField(String value) {
        this.value = value;
    }

    /**
     * 返回值描述
     *
     * @return 返回值描述
     */
    public String getValue() {
        return this.value;
    }
}
