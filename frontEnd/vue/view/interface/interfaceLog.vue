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
            		var url = config.host+'statistic/addFollowUpInfo';
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
				j = {"total":3,"logList":[{"id":659951,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472105722000,"responseTime":1472105722000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659948,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472105302000,"responseTime":1472105303000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659945,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472105275000,"responseTime":1472105276000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659942,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472105194000,"responseTime":1472105194000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，调用后端服务超时","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659939,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472104786000,"responseTime":1472104787000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659936,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472104742000,"responseTime":1472104743000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659933,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472104694000,"responseTime":1472104695000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659930,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472104671000,"responseTime":1472104672000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659927,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472098140000,"responseTime":1472098140000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659924,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472098013000,"responseTime":1472098014000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659921,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097958000,"responseTime":1472097958000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659918,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097897000,"responseTime":1472097898000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659915,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097788000,"responseTime":1472097788000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659912,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097728000,"responseTime":1472097728000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659909,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097651000,"responseTime":1472097651000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659906,"interfaceType":"calculatePremium","orderNo":"BK16000045450001","plateNo":"LS325245","vin":"LSGKE52H3FW105390","insured":"测试","applicationNo":"null","requestNo":"null","responseNo":"106002SAIC160000000000000016560G","queryNo":"V0101CPIC510016001465214916886","confirmNo":"null","insurer":"cpic","province":"510000","requestTime":1472097622000,"responseTime":1472097622000,"status":"成功","errorMessage":"1商业险重复投保。2该车辆对应的平台车型（凯迪拉克CADILLAC SRX 3.0L 旗舰版）与当前所选车型不一致，请核实车型。如需变更车型，请提交平台电子联系单，否则该车的平台纯风险保费将与车型不一致。R2:RuleJ0542:车损险:家庭自用车投保车损导致非标|","dealerCode":"BK2000602SA","dealerName":"四川测试"},{"id":659903,"interfaceType":"queryVehicleModel","orderNo":"BK16000045450001","plateNo":"LS325245","vin":"LSGKE52H3FW105390","insured":"测试","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"510000","requestTime":1472097621000,"responseTime":1472097622000,"status":"成功","errorMessage":"null","dealerCode":"BK2000602SA","dealerName":"四川测试"},{"id":659900,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097519000,"responseTime":1472097519000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659894,"interfaceType":"queryApplicationDetail","orderNo":"LSVAB2BR8FN148461","plateNo":"null","vin":"null","insured":"null","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"cpic","province":"340000.340600","requestTime":1472097457000,"responseTime":1472097458000,"status":"失败","errorMessage":"交易订单号为：LSVAB2BR8FN148461的保单详细信息查询失败，未找到符合条件的保单信息。","dealerCode":"VW00434","dealerName":"安徽淮北华松汽车销售服务有限责任公司"},{"id":659897,"interfaceType":"calculatePremium","orderNo":"BK16000045450001","plateNo":"LS325245","vin":"LSGKE52H3FW105390","insured":"测试","applicationNo":"null","requestNo":"null","responseNo":"106002SAIC160000000000000016560G","queryNo":"V0101CPIC510016001465214916886","confirmNo":"null","insurer":"cpic","province":"510000","requestTime":1472097450000,"responseTime":1472097450000,"status":"成功","errorMessage":"null","dealerCode":"BK2000602SA","dealerName":"四川测试"}]};
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

