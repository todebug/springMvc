package com.springapp.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 获取交易统计数据查询条件
 * Created by pengfarui on 2016/5/19.
 */
public class TransReportQueryCondition implements Serializable {

    private static final long serialVersionUID = -2752950906485285177L;

    /**
     * 查询开始时间
     */
    private Date startDate;

    /**
     * 查询结束时间
     */
    private Date endDate;

    /**
     * 对比数据查询开始时间
     */
    private Date contrastStartDate;

    /**
     * 对比数据查询结束时间
     */
    private Date contrastEndDate;

    /**
     * 时间段类型
     */
    private PeriodType periodType;

    /**
     * 保险公司
     */
    private List<String> insurers;

    /**
     * 承保地区
     */
    private List<String> provinces;

    /**
     * 交易类型
     */
    private List<String> interfaceTypes;

    /**
     * 显示的指标
     */
    private List<IisIndicator> indicators;

    /**
     * 分组统计字段
     */
    private List<IisField> groups;

    /**
     * 排序字段
     */
    private IisField order;

    /**
     * 排序方式
     */
    private Sort sort;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getContrastStartDate() {
        return contrastStartDate;
    }

    public void setContrastStartDate(Date contrastStartDate) {
        this.contrastStartDate = contrastStartDate;
    }

    public Date getContrastEndDate() {
        return contrastEndDate;
    }

    public void setContrastEndDate(Date contrastEndDate) {
        this.contrastEndDate = contrastEndDate;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public List<String> getInsurers() {
        return insurers;
    }

    public void setInsurers(List<String> insurers) {
        this.insurers = insurers;
    }

    public List<String> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<String> provinces) {
        this.provinces = provinces;
    }

    public List<String> getInterfaceTypes() {
        return interfaceTypes;
    }

    public void setInterfaceTypes(List<String> interfaceTypes) {
        this.interfaceTypes = interfaceTypes;
    }

    public List<IisIndicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<IisIndicator> indicators) {
        this.indicators = indicators;
    }

    public List<IisField> getGroups() {
        return groups;
    }

    public void setGroups(List<IisField> groups) {
        this.groups = groups;
    }

    public IisField getOrder() {
        return order;
    }

    public void setOrder(IisField order) {
        this.order = order;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
