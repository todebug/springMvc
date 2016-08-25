<template>
	<div id="interfaceLog">
		<query-Bar></query-Bar>
		<data-List :info="info"></data-List>
		<loading></loading>
	</div>
</template>

<script>
'use strict';
//初始化对象属性
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
var config = require('../../../components/lib/js/config');
var today=moment().format('YYYY-MM-DD');
var tomorrow= moment().add(1, 'days').format('YYYY-MM-DD');
var info = {
	logList: [],
	total: -1
}
var pageData = {
	cur: 0,
	all: 0
}
var queryData={
	interfaceType: '',
	plateNo: '',
	vin: '',
	insured: '',
	insurer: '',
	province: '',
	orderNo: '',
	status: '',
	dealer: '',
	requestBeginTime: today,
	requestEndTime: tomorrow,
	responseBeginTime: '',
	responseEndTime: '',
	errorMessage: '',
            page: 1,
            size: 20//默认每页20条
}
module.exports= {
	data: function () {
	    	//绑定数据
	    	return  {info,queryData,pageData}
	},
	created: function() {
		this.initQueryData();
		//this.getDataList(this.queryData);
	},
	events: {
		'dispatch-queryBar-interfaceLog-queryData': function(queryCondition) {
			this.queryData = Object.assign({}, this.queryData, queryCondition);
			this.getDataList(this.queryData);
		},
		'dispatch-log-pageBar-dataList-curPage': function(curPage) {
			this.queryData.page = curPage;
			this.getDataList(this.queryData);
		},
		'dispatch-dataList-interfaceLog-provincesFilter': function(provincesFilter) {
			this.$broadcast('broadcast-interfaceLog-queryBar-provincesFilter', provincesFilter);
		},
		'dispatch-dataList-interfaceLog-interfaceTypeFilter': function(interfaceTypeFilter) {
			this.$broadcast('broadcast-interfaceLog-queryBar-interfaceTypeFilter',interfaceTypeFilter);
		},
		'dispatch--pageBar-interfaceLog-closeTr': function(closeTr) {
			this.$broadcast('broadcast-interfaceLog-dataList-closeTr',closeTr);
		}
	},
	methods: {
		showLoading: function() {
			this.$broadcast('broadcast-loading-show', true);
		},
		hideLoading: function() {
			this.$broadcast('broadcast-loading-hide',true);
		},
		initQueryData: function() {
			var today=moment().format('YYYY-MM-DD');
			var tomorrow= moment().add(1, 'days').format('YYYY-MM-DD');
			this.queryData.interfaceType = '';
			this.queryData.plateNo = '';
			this.queryData.vin = '';
			this.queryData.insured = '';
			this.queryData.insurer = '';
			this.queryData.province = '';
			this.queryData.orderNo = '';
			this.queryData.status = '';
			this.queryData.dealer = '';
			this.queryData.requestBeginTime=today;
			this.queryData.requestEndTime=tomorrow;
			this.queryData.responseBeginTime='';
			this.queryData.responseEndTime='';
			this.queryData.errorMessage='';
			this.queryData.page=1;
			this.queryData.size=20;
			this.info.logList=[];
			this.info.total=-1;
			pageData.all=0;
			pageData.cur=0;
		},
		getDataList: function(queryData) {
            		var _this = this;
            		var url = config.host+'statistic/getlogList';
            		this.showLoading();
			var result = fetch(url,{
				method: 'post',
				mode: 'cors',
				headers: {
					'Accept': 'application/json',
				     	'Content-Type': 'application/json'
				},
				body: JSON.stringify({
				 	'interfaceType': queryData.interfaceType,
					'plateNo': queryData.plateNo,
					'vin': queryData.vin,
					'insured': queryData.insured,
					'insurer': queryData.insurer,
					'province': queryData.province,
					'orderNo': queryData.orderNo,
					'status': queryData.status,
					'dealerCode': queryData.dealer,
					'requestBeginTime': queryData.requestBeginTime,
					'requestEndTime': queryData.requestEndTime,
					'responseBeginTime': queryData.responseBeginTime,
					'responseEndTime': queryData.responseEndTime,
					'errorMessage': queryData.errorMessage,
				 	'page': queryData.page, 
				 	'size': queryData.size
				})
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				//console.log(JSON.parse(JSON.stringify(j)));
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.info = Object.assign(_this.info, j);
				_this.dispatchPageData(_this);
				_this.hideLoading();
			}).catch(function(ex) {
				console.log('failed', ex)
			});
	            },
	            dispatchPageData: function(_this) {
            		//设置点击页数
            		_this.pageData.cur = _this.queryData.page;
            		_this.pageData.all = _this.info.total;
            		_this.$broadcast('broadcast-interfaceLog-pageBar-PageData', _this.pageData);
            		_this.$broadcast('broadcast-interfaceLog-dataList-closeTr',true);
	            },
	},
	components: {
		queryBar: require ('../../../components/vue/interface/interfaceLog/queryBar.vue'),
		dataList: require ('../../../components/vue/interface/interfaceLog/dataList.vue'),
		loading: require ('../../../components/vue/commonVue/loading.vue')
	}
}

</script>

