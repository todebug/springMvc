<template>
    <div class="errorEchart">
                <div id="cpicErrorData" class="errorData">
                </div>
                <div id="piccErrorData" class="errorData">
                </div>
                <div id="gpicErrorData" class="errorData">
                </div>
                <div id="errorData" class="errorData">
                </div>
    </div>
</template>

<script type="text/javascript">
'use strict';
var echarts = require('echarts');
var config = require('../../../lib/js/config');
var theme = require('../../../lib/js/echartTheme');
var myChart = {
        cpic: {},
        picc: {},
        gpic: {},
        error: {}
}
var schedule = require('node-schedule');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化查询条件
var queryErrorCondition={
        startDate: today,
        endDate: today,
        periodType: 'TWO_MINUTE',//默认实时
        insurance: [],
        provinces: [],
        interfaceTypes: [],
        errorType: [],
        errorSource: []
}
var scheduleJob;
var queryTime={
        beginTime: '',
        endTime: ''
}
module.exports= {
	data: function () {
	           //绑定查询条件
	           return  {queryErrorCondition,scheduleJob,queryTime}
	},
	ready: function() {
	           this.drawEchart(this.queryErrorCondition);
                        this.nodeSchedule();
  	},
            created: function() {
                        this.initQueryData();
            },
            beforeDestroy: function() {
                        this.scheduleJob.cancel();
            },
      	methods: {
                        echartClick: function(insurance,errorType,_this) {
                                this.$dispatch('dispatch-dataEchart-errorStatistics-dataList-floatData', insurance,errorType,_this.queryTime);
                        },
                        initQueryData: function() {
                                var today=moment().format('YYYY-MM-DD');
                                this.queryErrorCondition.startDate = today;
                                this.queryErrorCondition.endDate = today;
                                this.queryErrorCondition.periodType = 'TWO_MINUTE';
                                this.queryErrorCondition.insurance = [];
                                this.queryErrorCondition.provinces = [];
                                this.queryErrorCondition.interfaceTypes = [];
                                this.queryErrorCondition.errorType=[];
                                this.queryErrorCondition.errorSource=[];
                                this.queryTime.beginTime = '';
                                this.queryTime.endTime = '';
                                myChart.cpic={};
                                myChart.picc={};
                                myChart.gpic={};
                                myChart.error={};
                        },
                        nodeSchedule: function() {
                                //页面定时刷新处理
                                var rule = new schedule.RecurrenceRule();  
                                var times = [];
                                //每三分钟执行一次
                                for(var i=0; i<60; i=i+3){
                                　　times.push(i);
                                }
                                rule.minute  = times;
                                var _this = this;
                                //var count = 0;
                                this.scheduleJob = schedule.scheduleJob(rule, function(){
                                    //指标为实时时才刷新
                                    var query = _this.getQueryData();
                                    if(query.periodType==='TWO_MINUTE'){
                                        //根据查询条件动态渲染echart
                                        _this.drawEchart(query);
                                        //count++;
                                        //var myDate = new Date();
                                        //console.log('npm schedule 第'+count+'次执行,当前时间是:'+myDate.getHours()+':'+myDate.getMinutes());
                                    }
                                });
                        },
                        getQueryData: function() {
                                //实时获取查询vue绑定的查询条件
                                return this.queryErrorCondition;
                        },
                        isEmptyObject: function(obj) {
                                if (obj == null) return true;
                                if (obj.length > 0)    return false;
                                if (obj.length === 0)  return true;
                                for (var key in obj) {
                                    if (Object.prototype.hasOwnProperty.call(obj, key)) return false;
                                }
                                return true;
                        },
  		drawEchart: function(queryData) {
		        //echart渲染
                                var _this = this;
                                if((null!=document.getElementById('cpicErrorData'))&&(null!=document.getElementById('piccErrorData'))&&(null!=document.getElementById('gpicErrorData'))&&(null!=document.getElementById('errorData'))){
                                    if(!this.isEmptyObject(myChart.cpic) && !this.isEmptyObject(myChart.picc) && !this.isEmptyObject(myChart.gpic) && !this.isEmptyObject(myChart.error)){//防止频繁刷新内存不足,销毁echart实例
                                        myChart.cpic.dispose(); 
                                        myChart.picc.dispose(); 
                                        myChart.gpic.dispose(); 
                                        myChart.error.dispose(); 
                                        myChart.cpic.off('click');
                                        myChart.picc.off('click');
                                        myChart.gpic.off('click');
                                    }
    		            myChart.cpic = echarts.init(document.getElementById('cpicErrorData'),theme);	
                                    myChart.picc = echarts.init(document.getElementById('piccErrorData'),theme);
                                    myChart.gpic = echarts.init(document.getElementById('gpicErrorData'),theme);
                                    myChart.error = echarts.init(document.getElementById('errorData'),theme);  
    	                        this.renderChart(myChart,queryData,_this);	
                                }
  		},
  		getQueryData: function() {
  	                        //实时获取查询vue绑定的查询条件
  		            return this.queryErrorCondition;
  		},
                        showLoading: function(dataChart) {
                                    dataChart.cpic.showLoading({textStyle : {fontSize : 20}});
                                    dataChart.picc.showLoading({textStyle : {fontSize : 20}});
                                    dataChart.gpic.showLoading({textStyle : {fontSize : 20}});
                                    dataChart.error.showLoading({textStyle : {fontSize : 20}});
                        },
                        showEchart: function(dataChart,_this) {
                                    
                                    dataChart.error.hideLoading();
                                    dataChart.error.clear();
                                    dataChart.error.setOption(errorOption);
                                    dataChart.error.on('click',function (params) {
                                            if(params.name==='太保财险'){
                                                    _this.echartClick('cpic','',_this);
                                            }else if(params.name==='人保财险'){
                                                    _this.echartClick('picc','',_this);
                                            }else if(params.name==='国寿财险'){
                                                    _this.echartClick('gpic','',_this);
                                            }
                                    });

                                    dataChart.cpic.hideLoading();
                                    dataChart.cpic.clear();
                                    dataChart.cpic.setOption(cpicOption);
                                    dataChart.cpic.on('click',function (params) {
                                            if(params.name!=='暂无数据'){
                                                    _this.echartClick('cpic',params.name,_this);
                                            }
                                    });

                                    dataChart.picc.hideLoading();
                                    dataChart.picc.clear();
                                    dataChart.picc.setOption(piccOption);
                                    dataChart.picc.on('click',function (params) {
                                            if(params.name!=='暂无数据'){
                                                    _this.echartClick('picc',params.name,_this);
                                            }
                                    });

                                    dataChart.gpic.hideLoading();
                                    dataChart.gpic.clear();
                                    dataChart.gpic.setOption(gpicOption);
                                    dataChart.gpic.on('click',function (params) {
                                            if(params.name!=='暂无数据'){
                                                    _this.echartClick('gpic',params.name,_this);
                                            }
                                    });

                                    window.onresize = function(){
                                         dataChart.error.resize();
                                         dataChart.cpic.resize(); 
                                         dataChart.picc.resize();
                                         dataChart.gpic.resize();
                                    }
                        },
                        dispatchDataLoad: function(data) {
                                if(data!==''&&data!==undefined&&data.error!=='错误统计echart查询失败!'){
                                    //将查询数据上载到父组件进行分发处理
                                    this.$dispatch('dispatch-dataEchart-dataUl-loadingErrorData', data);
                                }
                        },
                        getSetColorIndex: function(data) {
                                var indexOne = 0;
                                var indexTwo = null;
                                for(var item in data) {
                                           if(item==='UNKNOWN'){
                                            indexTwo = indexOne;
                                           }
                                           indexOne++;
                                    }
                                    return indexTwo;
                        },
                        setCpicData: function(data) {
                            cpicOption.series[0].data = [];
                             if((data !== null) &&(data.reportData !== null) && (data.reportData.cpic !== null) && (data.reportData.cpic.items !== null) && (data.reportData.cpic.indicators !== null)){
                                var index = 0;
                                for(var item in data.reportData.cpic.indicators) {
                                    var obj = {value: index,name: index};
                                   cpicOption.series[0].data.push(obj);
                                   index ++;
                                }
                                var indexOne = 0;
                                for(var item in data.reportData.cpic.indicators) {
                                    cpicOption.series[0].data[indexOne].name=data.reportData.cpic.indicators[item];
                                    indexOne ++;
                                }
                                var indexTwo = 0;
                                for(var item in data.reportData.cpic.items) {
                                    cpicOption.series[0].data[indexTwo].value=data.reportData.cpic.items[item];
                                    indexTwo ++;
                                }
                            }
                            if(cpicOption.series[0].data.length === 0){
                                var cpicObj = {value: '-',name: '暂无数据'};
                                cpicOption.series[0].data.push(cpicObj);
                                cpicOption.color=['#DDD'];
                                cpicOption.series[0].label.normal.position = 'inside';
                            }else{
                                cpicOption.series[0].label.normal.position = 'outside';
                                cpicOption.color=['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'];
                                if((data.reportData.cpic !== null) && (data.reportData.cpic.items !== null) && (data.reportData.cpic.indicators !== null) && (data.reportData.cpic.indicators.UNKNOWN !== null)){
                                    var index = this.getSetColorIndex(data.reportData.cpic.indicators);//获取UNKNOWN位置
                                    if(index !== null && index < 39){
                                        cpicOption.color.splice(index, 0, '#ff0000');
                                    }
                                }
                            }
                        },
                        setPiccData: function(data) {
                            piccOption.series[0].data = [];
                            if((data !== null) &&(data.reportData !== null) && (data.reportData.picc !== null) && (data.reportData.picc.items !== null) && (data.reportData.picc.indicators !== null)){
                                var index = 0;
                                for(var item in data.reportData.picc.indicators) {
                                    var obj = {value: index,name: index};
                                    piccOption.series[0].data.push(obj);
                                    index ++;
                                }
                                var indexOne = 0;
                                for(var item in data.reportData.picc.indicators) {
                                    piccOption.series[0].data[indexOne].name=data.reportData.picc.indicators[item];
                                    indexOne ++;
                                }
                                var indexTwo = 0;
                                for(var item in data.reportData.picc.items) {
                                    piccOption.series[0].data[indexTwo].value=data.reportData.picc.items[item];
                                    indexTwo ++;
                                }
                            }
                            if(piccOption.series[0].data.length === 0){
                                var piccObj = {value: '-',name: '暂无数据'};
                                piccOption.series[0].data.push(piccObj);
                                piccOption.color=['#DDD'];
                                piccOption.series[0].label.normal.position = 'inside';
                            }else{
                                piccOption.series[0].label.normal.position = 'outside';
                                piccOption.color=['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'];
                                if((data.reportData.picc !== null) && (data.reportData.picc.items !== null) && (data.reportData.picc.indicators !== null) && (data.reportData.picc.indicators.UNKNOWN !== null)){
                                    var index = this.getSetColorIndex(data.reportData.picc.indicators);//获取UNKNOWN位置
                                    if(index !== null && index < 39){
                                        piccOption.color.splice(index, 0, '#ff0000');
                                    }
                                }
                            }
                        },
                        setGpicData: function(data) {
                            gpicOption.series[0].data = [];
                             if((data !== null) && (data.reportData !== null) && (data.reportData.gpic !== null) && (data.reportData.gpic.items !== null) && (data.reportData.gpic.indicators !== null)){
                                var index = 0;
                                for(var item in data.reportData.gpic.indicators) {
                                    var obj = {value: index,name: index};
                                    gpicOption.series[0].data.push(obj);
                                    index ++;
                                }
                                var indexOne = 0;
                                for(var item in data.reportData.gpic.indicators) {
                                    gpicOption.series[0].data[indexOne].name=data.reportData.gpic.indicators[item];
                                    indexOne ++;
                                }
                                var indexTwo = 0;
                                for(var item in data.reportData.gpic.items) {
                                    gpicOption.series[0].data[indexTwo].value=data.reportData.gpic.items[item];
                                    indexTwo ++;
                                }
                            }
                            if(gpicOption.series[0].data.length === 0){
                                var gpicObj = {value: '-',name: '暂无数据'};
                                gpicOption.series[0].data.push(gpicObj);
                                gpicOption.color=['#DDD'];
                                gpicOption.series[0].label.normal.position = 'inside';
                            }else{
                                gpicOption.series[0].label.normal.position = 'outside';
                                gpicOption.color=['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'];
                                if((data.reportData.gpic !== null) && (data.reportData.gpic.items !== null) && (data.reportData.gpic.indicators !== null) && (data.reportData.gpic.indicators.UNKNOWN !== null)){
                                    var index = this.getSetColorIndex(data.reportData.gpic.indicators);//获取UNKNOWN位置
                                    if(index !== null && index < 39){
                                        gpicOption.color.splice(index, 0, '#ff0000');
                                    }
                                }
                            }
                        },
                        setErrorData: function(data) {
                            errorOption.series[0].data = [];
                            var cpicColor = false;
                            var piccColor = false;
                            var gpicColor = false;
                            if(data !== null && data.reportData !== null && data.reportData.cpic !== null && data.reportData.cpic.sumItems !== null && data.reportData.cpic.sumItems.ERROR_COUNT[0] !== null){
                                var cpic = data.reportData.cpic.sumItems.ERROR_COUNT[0];
                                var cpicObj = {value: cpic,name: '太保财险'};
                                errorOption.series[0].data.push(cpicObj);
                                cpicColor = true;
                            }
                            if(data !== null && data.reportData !== null && data.reportData.picc !== null && data.reportData.picc.sumItems !== null && data.reportData.picc.sumItems.ERROR_COUNT[0] !== null){
                                var picc = data.reportData.picc.sumItems.ERROR_COUNT[0];
                                var piccObj = {value: picc,name: '人保财险'};
                                errorOption.series[0].data.push(piccObj);
                                piccColor = true;
                            }
                            if(data !== null && data.reportData !== null && data.reportData.gpic !== null && data.reportData.gpic.sumItems !== null && data.reportData.gpic.sumItems.ERROR_COUNT[0] !== null){
                                var gpic = data.reportData.gpic.sumItems.ERROR_COUNT[0];
                                var gpicObj = {value: gpic,name: '国寿财险'};
                                errorOption.series[0].data.push(gpicObj);
                                gpicColor = true;
                            }
                            if(errorOption.series[0].data.length === 0){//如果都没有数据,则显示统一置灰数据
                                var errorObj = {value: '-',name: '暂无数据'};
                                errorOption.series[0].data.push(errorObj);
                                errorOption.color=['#DDD'];
                                errorOption.series[0].label.normal.position = 'inside';
                            }else{
                                errorOption.series[0].label.normal.position = 'outside';
                                //颜色处理
                                this.handleErrorDataColor(cpicColor,piccColor,gpicColor);
                            }
                        },
                        handleErrorDataColor: function(cpicColor,piccColor,gpicColor) {
                            if(cpicColor===true && piccColor===false && gpicColor===false ){
                                errorOption.color=['#004a97'];
                            }else if(cpicColor===false && piccColor===true && gpicColor===false ){
                                 errorOption.color=['#e8380d'];
                            }else if(cpicColor===false && piccColor===false && gpicColor===true ){
                                 errorOption.color=[ '#009B63'];
                            }else if(cpicColor===true && piccColor===true && gpicColor===false ){
                                 errorOption.color=['#004a97','#e8380d'];
                            }else if(cpicColor===true && piccColor===false && gpicColor===true ){
                                 errorOption.color=['#004a97','#009B63'];
                            }else if(cpicColor===false && piccColor===true && gpicColor===true ){
                                 errorOption.color=['#e8380d', '#009B63'];
                            }else if(cpicColor===true && piccColor===true && gpicColor===true ){
                                 errorOption.color=['#004a97','#e8380d', '#009B63'];
                            }
                        },
                        renderChart: function(dataChart,queryData,operate) {
                                     //showLoading
                                    operate.showLoading(dataChart); 
                                    var url = config.host+'statistic/getErrorInfo';
                                    var result = fetch(url,{
                                          method: 'post',
                                          mode: 'cors',
                                          headers: {
                                                'Accept': 'application/json',
                                                'Content-Type': 'application/json'
                                          },
                                          body: JSON.stringify({
                                                'startDate': queryData.startDate,
                                                'endDate': queryData.endDate,
                                                'periodType': queryData.periodType,
                                                'insurers': queryData.insurance,
                                                'provinces': queryData.provinces,
                                                'interfaceTypes': queryData.interfaceTypes,
                                                'errorSources': queryData.errorSource,
                                                'errorTypes': queryData.errorType
                                          })
                                    });
                                    result.then(function(response) {
                                                return response.json();
                                    }).then(function(j) {
                                                //console.log(j);
                                                //实时设置其他vue数据
                                                operate.dispatchDataLoad(j);
                                                //设置数据
                                                //console.log(JSON.parse(JSON.stringify(queryData)));
                                                //console.log(JSON.parse(JSON.stringify(j)));
                                                operate.setPieQueryTime(j);
                                                operate.setCpicData(j);
                                                operate.setPiccData(j);
                                                operate.setGpicData(j);
                                                operate.setErrorData(j);
                                                //显示Echart
                                                operate.showEchart(dataChart,operate);
                                    }).catch(function(ex) {
                                                console.log('failed', ex)
                                    });
                        },
                        setPieQueryTime: function(j) {
                                this.queryTime.beginTime = j.reportData.cpic.timeSpan[0];
                                this.queryTime.endTime = j.reportData.cpic.timeSpan[1];
                        }	
  	},
  	events: {
	    'broadcast-errorStatistics-dataEchart-dataList-queryCondition': function(data) {
	      // 动态绑定查询对象数据
	      this.queryErrorCondition = Object.assign({}, this.queryErrorCondition, data);
	      this.drawEchart(this.queryErrorCondition);
	    }
	}
}

var cpicOption = {
    backgroundColor: '#f5f6f6',
    color: ['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
    title: {
      show: true,
      text: '太保财险',
      y: 'bottom',
      x: 'center',
      textStyle: {
            color: '#004a97',
            fontWeight: 'bold'
      }
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: [
        {
            name: '错误统计',
            type: 'pie',
            selectedMode: 'single',
            center: ['50%', '50%'],
            radius: [0, '120px'],
            avoidLabelOverlap: true,
            label: {
                normal: {
                    position: 'outside'
                }
            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            minAngle: 10,
            data:[]
        }
    ]
};
var piccOption = {
    backgroundColor: '#f5f6f6',
    color: ['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
    title: {
      show: true,
      text: '人保财险',
      y: 'bottom',
      x: 'center',
      textStyle: {
            color: '#e8380d',
            fontWeight: 'bold'
      }
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: [
        {
            name: '错误统计',
            type: 'pie',
            selectedMode: 'single',
            center: ['50%', '50%'],
            radius: [0, '120px'],
            avoidLabelOverlap: true,
            label: {
                normal: {
                    position: 'outside'
                }
            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            minAngle: 10,
            data:[]
        }
    ]
};
var gpicOption = {
    backgroundColor: '#f5f6f6',
    color: ['#2ec7c9','#44B7D3','#b6a2de','#E95569','#5ab1ef','#ffb980','#d87a80','#E42B6D','#8d98b3','#e5cf0d','#97b552','#ff6347','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089','#F4E24E','#FE9616','#8AED35','#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0','#7b68ee','#00fa9a','#ffd700','#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
    title: {
      show: true,
      text: '国寿财险',
      y: 'bottom',
      x: 'center',
      textStyle: {
            color: '#009B63',
            fontWeight: 'bold'
      }
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: [
        {
            name: '错误统计',
            type: 'pie',
            selectedMode: 'single',
            center: ['50%', '50%'],
            radius: [0, '120px'],
            avoidLabelOverlap: true,
            label: {
                normal: {
                    position: 'outside'
                }
            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            minAngle: 10,
            data:[]
        }
    ]
};
var errorOption = { 
      color:['#004a97','#e8380d', '#009B63'],
      backgroundColor: '#f5f6f6',
      title: {
          show: true,
          text: '保险公司错误统计',
          y: 'bottom',
          x: 'center',
          textStyle: {
                color: '#050505',
                fontWeight: 'bold'
          }
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: [
        {
            name:'错误统计',
            type:'pie',
            selectedMode: 'single',
            center:['50%','50%'],
            radius: [0, '120px'],
            avoidLabelOverlap: true,
            label: {
                normal: {
                    position: 'outside'
                }
            },
             labelLine: {
                normal: {
                    show: true
                }
            },
            minAngle: 10,
            data:[]
        }
    ]
};
</script>

<style type="text/css" scoped>
.errorEchart {
        height: 710px;
        min-width: 990px;
        margin-left: 5px;
        margin-right: 5px;
        margin-top: 5px;
        border: 1px solid #d6d6d6;
        border-radius: 5px;
}

.errorData {
        width: 50%;
        height: 350px;
        min-width: 445px;
        float: left;
}

.dataEchart {
    margin-left: 5px;
    margin-right: 5px;
    margin-top: 5px;
    border: 1px solid #d6d6d6;
        border-radius: 5px;
        width: 99.3%;
        min-width: 1000px;
}

.statisticsData {
    width: 99%;
    height: 650px;
    min-width: 990px;
}
.statisticsCalculateData {
    width: 99%;
    height: 650px;
    min-width: 990px;
}

.calculatePremium {
    margin-left: 27px;
    display: inline-block;
    width: 44.5%;
    height: 650px;
    min-width: 445px;
}

.submitApplication {
    margin-left: 50px;
    display: inline-block;
    width: 44.5%;
    height: 650px;
    min-width: 445px;
}

.divHide {
    display: none;
}

.divShow {
    display: block;
}
</style>