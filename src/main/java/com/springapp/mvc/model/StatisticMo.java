package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author dongyang
 * @description TODO
 * @date 2016/5/25 9:24
 */
public class StatisticMo implements Serializable {

    private static final long serialVersionUID = -4641203330251711483L;

    private String dateType;
    @JsonFormat(
            pattern  = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;
    @JsonFormat(
            pattern  = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endTime;

    /**
     * 对比数据查询开始时间
     */
    @JsonFormat(
            pattern  = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date contrastStartDate;

    /**
     * 对比数据查询结束时间
     */
    @JsonFormat(
            pattern  = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date contrastEndDate;
    private String code;

    private String name;

    private Date time;

    private List<Date> dateTime;

    private int page;

    private int size;

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Date> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<Date> dateTime) {
        this.dateTime = dateTime;
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

       public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
