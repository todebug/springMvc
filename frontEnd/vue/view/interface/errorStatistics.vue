<template>
	<div id="errorStatistics">
		<head-Bar></head-Bar>
		<data-Ul :info="info"></data-Ul> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<data-List></data-List>
	</div>
</template>

<script>
'use strict';
//初始化对象属性
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化默认值
var queryErrorData = {
	startDate: today,
	endDate: today,
	periodType: 'TWO_MINUTE',//默认实时
            insurance: [],
	provinces: [],
            interfaceTypes: [],
            errorType: [],
            errorSource: []
};
var info = {
	reportData: {
		cpic: {
			sumTitle: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			},
			sumItems: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			}
		},
		picc: {
			sumTitle: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			},
			sumItems: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			}
		},
		gpic: {
			sumTitle: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			},
			sumItems: {
				INSURER_ERROR: '',
				INSIDE_SYSTEM_ERROR: '',
				ERROR_COUNT: '',
				USER_OPERATE_ERROR: '',
				UNKNOWN_ERROR: ''
			}
		}
	}
};
module.exports= {
	data: function () {
	    	//绑定数据
	    	return  {queryErrorData,info}
	},
	created: function() {
		this.initQueryData();
	},
	events: {
	    	'dispatch-dataEchart-errorStatistics-dataList-floatData': function(insurance,errorType,queryTime) {
	    		this.$broadcast('broadcast-errorStatistics-dataEchart-dataList-floatData', insurance,errorType,queryTime);
	    	},
	    	'dispatch-dataList-errorStatistics-provincesFilter': function(provincesFilter) {
			this.$broadcast('broadcast-errorStatistics-conditionBar-provincesFilter', provincesFilter);
	     	},
	    	'dispatch-dataList-errorStatistics-interfaceTypeFilter': function(interfaceTypeFilter) {
			this.$broadcast('broadcast-errorStatistics-conditionBar-interfaceTypeFilter',interfaceTypeFilter);
	    	},
	     	'dispatch-dataList-errorStatistics-errorTypeFilter': function(errorTypeFilter) {
			this.$broadcast('broadcast-errorStatistics-conditionBar-errorTypeFilter', errorTypeFilter);
	     	},
	    	'dispatch-dataList-errorStatistics-errorSourceFilter': function(errorSourceFilter) {
			this.$broadcast('broadcast-errorStatistics-conditionBar-errorSourceFilter',errorSourceFilter);
	     	},
	    	'dispatch-dataEchart-dataUl-loadingErrorData': function (loadingData) {//获取[dataEchart.vue的fetch的j数据,绑定到info上,通过prop传递给子组件
	      		// 动态设置属性
	      		this.info = Object.assign({}, this.info, loadingData);
	    	},
	    	'dispatch-headBar-dataEchart-queryCondition': function (dateData) {//获取[headBar.vue]中时间查询条件
		      	// 事件回调内的 `this` 自动绑定到注册它的实例上
		      	this.queryErrorData.startDate = dateData.startDate;
		      	this.queryErrorData.endDate = dateData.endDate;
		      	this.queryErrorData.periodType = dateData.periodType;
		      	this.getDateTime(this.queryErrorData);
	    	},
	  	'dispatch-conditionBar-dataEchart-insurance': function (insurance) {//获取[conditionBar.vue]中的查询数据
		      	this.queryErrorData.insurance.shift();
		      	if(insurance!==''){
		      		this.queryErrorData.insurance.push(insurance);
		      	}
		      	this.getDateTime(this.queryErrorData);
	    	},
	   	'dispatch-conditionBar-dataEchart-provinces': function (provinces) {//获取[conditionBar.vue]中的查询数据
		    	this.queryErrorData.provinces.shift();
		    	if(provinces!==''){
		      		this.queryErrorData.provinces.push(provinces);
		      	}
		      	this.getDateTime(this.queryErrorData);
	    	},
	   	'dispatch-conditionBar-dataEchart-interfaceTypes': function (interfaceTypes) {//获取[conditionBar.vue]中的查询数据
		      	this.queryErrorData.interfaceTypes.shift();
		      	if(interfaceTypes!==''){
		      		this.queryErrorData.interfaceTypes.push(interfaceTypes);
		      	}
		      	this.getDateTime(this.queryErrorData);
	    	},
	    	'dispatch-conditionBar-dataEchart-errorType': function (errorType) {//获取[conditionBar.vue]中的查询数据
		      	this.queryErrorData.errorType.shift();
		      	if(errorType!==''){
		      		this.queryErrorData.errorType.push(errorType);
		      	}
		      	this.getDateTime(this.queryErrorData);
	    	},
	    	'dispatch-conditionBar-dataEchart-errorSource': function (errorSource) {//获取[conditionBar.vue]中的查询数据
	      	this.queryErrorData.errorSource.shift();
	      		if(errorSource!==''){
	      			this.queryErrorData.errorSource.push(errorSource);
	      		}
	      		this.getDateTime(this.queryErrorData);
	    	},
	    	'dispatch-error-pageBar-dataList-curPage': function(curPage) {//获取[pageBar.vue]当前点击页数,将数据传递至子组件[dataList.vue]中
	    		this.$broadcast('broadcast-errorStatistics-dataList-curPage', curPage);
	    	},
	    	'dispatch-error-dataList-pageBar-PageData': function(pageData) {//通知[pageBar.vue]当前页数及当前总页数
	    		this.$broadcast('broadcast-errorStatistics-pageBar-getPageData', pageData);
	    	}
	},
	methods: {
		initQueryData: function() {
			var today=moment().format('YYYY-MM-DD');
			this.queryErrorData.startDate = today;
			this.queryErrorData.endDate = today;
			this.queryErrorData.periodType = 'TWO_MINUTE';
			this.queryErrorData.insurance = [];
			this.queryErrorData.provinces = [];
			this.queryErrorData.interfaceTypes = [];
			this.queryErrorData.errorType=[];
			this.queryErrorData.errorSource=[];

		},
		getDateTime: function(queryData){//将查询条件广播到子组件中.[dataEchart.vue]
			this.$broadcast('broadcast-errorStatistics-dataEchart-dataList-queryCondition', queryData);
		}
	},
	components: {
		headBar: require ('../../../components/vue/interface/errorStatistics/headBar.vue'),
		dataUl: require ('../../../components/vue/interface/errorStatistics/dataUl.vue'),
		dataEchart: require ('../../../components/vue/interface/errorStatistics/dataEchart.vue'),
		conditionBar: require ('../../../components/vue/interface/errorStatistics/conditionBar.vue'),
		dataList: require ('../../../components/vue/interface/errorStatistics/dataList.vue')
	}
}
</script>
