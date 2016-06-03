package com.springapp.mvc.model;

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

    private String beginTime;

    private String endTime;

    private String code;

    private String name;

    private Date time;

    private List<Date> dateTime;

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
}
