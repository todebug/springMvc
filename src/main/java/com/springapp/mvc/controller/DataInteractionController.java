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
