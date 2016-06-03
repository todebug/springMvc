package com.springapp.mvc.model;

/**
 * 保险对接平台相关指标
 * Created by pengfarui on 2016/5/20.
 */
public enum IisIndicator {
    // 按保险公司
    /**
     * 保险公司总交易数
     */
    insurer_totalCount("保险公司总交易数"),
    /**
     * 保险公司成功数
     */
    insurer_successCount("保险公司成功数"),
    /**
     * 保险公司失败数
     */
    insurer_failureCount("保险公司失败数"),
    /**
     * 保险公司最大时长
     */
    insurer_maxDuration("保险公司最大时长"),
    /**
     * 保险公司最小时长
     */
    insurer_minDuration("保险公司最小时长"),
    /**
     * 保险公司平均时长
     */
    insurer_averageDuration("保险公司平均时长"),
    /**
     * 保险公司90%时长
     */
    insurer_ninetyDuration("保险公司90%时长"),

    // 按承保地区
    /**
     * 承保地区总交易数
     */
    provinceprovince_totalCount("承保地区总交易数"),
    /**
     * 承保地区成功数
     */
    provinceprovince_successCount("承保地区成功数"),
    /**
     * 承保地区失败数
     */
    provinceprovince_failureCount("承保地区失败数"),
    /**
     * 承保地区最大时长
     */
    provinceprovince_maxDuration("承保地区最大时长"),
    /**
     * 承保地区最小时长
     */
    provinceprovince_minDuration("承保地区最小时长"),
    /**
     * 承保地区平均时长
     */
    provinceprovince_averageDuration("承保地区平均时长"),
    /**
     * 承保地区90%时长
     */
    provinceprovince_ninetyDuration("承保地区90%时长"),

    // 按交易类型
    /**
     * 交易类型总交易数
     */
    interfaceType_totalCount("交易类型总交易数"),
    /**
     * 交易类型成功数
     */
    interfaceType_successCount("交易类型成功数"),
    /**
     * 交易类型失败数
     */
    interfaceType_failureCount("交易类型失败数"),
    /**
     * 交易类型最大时长
     */
    interfaceType_maxDuration("交易类型最大时长"),
    /**
     * 交易类型最小时长
     */
    interfaceType_minDuration("交易类型最小时长"),
    /**
     * 交易类型平均时长
     */
    interfaceType_averageDuration("交易类型平均时长"),
    /**
     * 交易类型90%时长
     */
    interfaceType_ninetyDuration("交易类型90%时长");

    /**
     * 值描述
     */
    private String value;

    IisIndicator(String value) {
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
