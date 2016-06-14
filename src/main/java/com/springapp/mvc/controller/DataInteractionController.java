package com.springapp.mvc.controller;

import com.springapp.mvc.model.StatisticMo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dongyang
 * @description TODO
 * @date 2016/5/25 9:17
 */
@Controller
@RequestMapping("/statistic")
public class DataInteractionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // http://localhost:8080/statistic/getStatistic/1000
    /**
     * @title
     * @description
     * @author dongyang
     * @Date 2016-05-25
     * @param statisticMo
     * @return StatisticMo
     * @throws Exception
     */
    @RequestMapping(value = "/getStatistic",method = RequestMethod.POST)
    public @ResponseBody StatisticMo getCustomerInfo(@RequestBody StatisticMo statisticMo) throws Exception {
        StatisticMo statistic = new StatisticMo();
        statistic.setStartDate(new Date());
        System.out.println(statisticMo);
        System.out.println("2222");
        return statistic;
    }

    /**
     * @title  
     * @description 
     * @author dongyang
     * @Date 2016-05-25
     * @param statisticMo
     * @return StatisticMo
     */
    @RequestMapping(
        value  = "/addFollowUpInfo",
        method = RequestMethod.POST
    )
    @ResponseBody
    public StatisticMo addFollowUpInfo(@RequestBody StatisticMo statisticMo) {
        StatisticMo statistic = new StatisticMo();

        return statistic;
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void test(){
        System.out.println("2222");
        System.out.println("2222");

    }

    @RequestMapping("/getSucessSaveHistory/{beginDate}/{endDate}")
    public @ResponseBody StatisticMo getSucessSaveHistory(@PathVariable String beginDate,@PathVariable String endDate) throws Exception {
        StatisticMo statistic = new StatisticMo();
        List<Date>dates = new ArrayList<>();
        dates.add(new Date());
        statistic.setDateTime(dates);
        return statistic;
    }

    @RequestMapping("/getFailureInfoCount/{beginDate}/{endDate}")
    public @ResponseBody
    StatisticMo getFailureInfoCount(@PathVariable String beginDate,@PathVariable String endDate) throws Exception {
        StatisticMo statistic = new StatisticMo();

        return statistic;
    }
}
//交易数
//{"reportData":{"dataTitle":["08:54","08:56","08:58","09:00","09:02","09:04","09:06","09:08","09:10","09:12","09:14","09:16","09:18","09:20","09:22","09:24","09:26","09:28","09:30","09:32","09:34","09:36","09:38","09:40","09:42","09:44","09:46","09:48","09:50","09:52"],"indicators":{"picc_TOTAL_COUNT":"总交易数-人保财险","gpic_TOTAL_COUNT":"总交易数-国寿财险","cpic_TOTAL_COUNT":"总交易数-太保财险"},"items":{"picc_TOTAL_COUNT":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"gpic_TOTAL_COUNT":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,6,0,0,0,0,0,0,0,0,0,0,0,0,0],"cpic_TOTAL_COUNT":[0,0,0,0,0,0,0,0,0,0,0,0,2,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"sumTitle":{"cpic_calculatePremium":"保费计算","gpic_calculatePremium":"保费计算","gpic":"合计","picc_calculatePremium":"保费计算","picc_submitApplication":"保单提交","cpic":"合计","cpic_submitApplication":"保单提交","picc":"合计","gpic_submitApplication":"保单提交"},"sumItems":{"cpic_calculatePremium":[2,0,2,0.00,2,1,null,1.5],"gpic_calculatePremium":[3,3,0,100.00,26,7,null,14.67],"gpic":[9,9,0,100.00,26,0,null,6],"picc_calculatePremium":[0,0,0,0,0,0,null,0],"picc_submitApplication":[0,0,0,0,0,0,null,0],"cpic":[4,2,2,50.00,2,1,null,1.75],"cpic_submitApplication":[0,0,0,0,0,0,null,0],"picc":[0,0,0,0,0,0,null,0],"gpic_submitApplication":[0,0,0,0,0,0,null,0]}}}
//最大时长
////{"reportData":{"dataTitle":["08:54","08:56","08:58","09:00","09:02","09:04","09:06","09:08","09:10","09:12","09:14","09:16","09:18","09:20","09:22","09:24","09:26","09:28","09:30","09:32","09:34","09:36","09:38","09:40","09:42","09:44","09:46","09:48","09:50","09:52"],"indicators":{"gpic_MAX_DURATION":"最大时长-国寿财险","cpic_MAX_DURATION":"最大时长-太保财险","picc_MAX_DURATION":"最大时长-人保财险"},"items":{"gpic_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,26,11,0,0,0,0,0,0,0,0,0,0,0,0,0],"cpic_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,2,0,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"picc_MAX_DURATION":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"sumTitle":{"cpic_calculatePremium":"保费计算","gpic_calculatePremium":"保费计算","gpic":"合计","picc_calculatePremium":"保费计算","picc_submitApplication":"保单提交","cpic":"合计","cpic_submitApplication":"保单提交","picc":"合计","gpic_submitApplication":"保单提交"},"sumItems":{"cpic_calculatePremium":[2,0,2,0.00,2,1,null,1.5],"gpic_calculatePremium":[3,3,0,100.00,26,7,null,14.67],"gpic":[9,9,0,100.00,26,0,null,6],"picc_calculatePremium":[0,0,0,0,0,0,null,0],"picc_submitApplication":[0,0,0,0,0,0,null,0],"cpic":[4,2,2,50.00,2,1,null,1.75],"cpic_submitApplication":[0,0,0,0,0,0,null,0],"picc":[0,0,0,0,0,0,null,0],"gpic_submitApplication":[0,0,0,0,0,0,null,0]}}}