<template>
	<div v-show="pageErrorData.all!==0" class="listDiv">
			<table class="dataList">
				<thead>
					<tr>
						<td align="center">{{errorInfo.reportDetailData.columnNames[0][1]}}</td>
						<td align="center">{{errorInfo.reportDetailData.columnNames[1][1]}}</td>
						<td align="left">{{errorInfo.reportDetailData.columnNames[2][1]}}</td>
						<td align="left">{{errorInfo.reportDetailData.columnNames[3][1]}}</td>
						<td align="left" >{{errorInfo.reportDetailData.columnNames[4][1]}}</td>
						<td align="left">{{errorInfo.reportDetailData.columnNames[5][1]}}</td>
						<td align="right">{{errorInfo.reportDetailData.columnNames[6][1]}}</td>
						<td align="left">{{errorInfo.reportDetailData.columnNames[7][1]}}</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in errorInfo.reportDetailData.items.content" :item="item">
						<td align="center" >
							{{item[0]|insurance}}
						</td>
						<td align="center">
							{{item[1]}}
						</td>
						<td align="left">
							{{item[2] | tradeType}}
						</td>
						<td align="left">
							{{item[3] | area}}
						</td>
						<td align="left">
							{{item[4] | errorSource}}
						</td>
						<td align="left" style="width:120;">
							{{item[5] | errorType}}
						</td>
						<td align="right" style="width:80;" >
							<span  class="spanFloatDiv"data-value="{{item[0]}}|||{{item[1]}}|||{{item[2]}}|||{{item[3]}}|||{{item[4]}}|||{{item[5]}}|||{{item[6]}}|||{{item[7]}}" @click="getFloatDiv">{{item[6]}}</span>
						</td>
						<td align="left" style="width:300;">
							<p class="ellipsis-p" title="{{item[7]|parseNum}}">
								{{item[7]|parseNum}}
							</p>
						</td>
					</tr>
				</tbody>
			</table>
			<page-bar></page-bar>
			<float-bar></float-bar>
	</div>
	<div v-else class="noneDataDiv">
		暂无接口交互数据!
	</div>
</template>

<script>
'use strict';
var config = require('../../../lib/js/config');
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
            errorSource: [],
            page: 1,
            size: 20//默认每页20条
}
var judgePage={
            startDate: today,
            endDate: today,
            periodType: 'TWO_MINUTE',//默认实时
            insurance: [],
            provinces: [],
            interfaceTypes: [],
            errorType: [],
            errorSource: [],
}
var pageErrorData = {
	cur: 0,
	all: 0
}
var array = [['',''],['',''],['',''],['',''],['',''],['',''],['',''],['','']];
var errorInfo = {
	reportDetailData: {
		columnNames: array,
		items: {
			total: {},
			content: {},
			pageSize: {}
		}
	}
}
var provincesFilter = [];
var interfaceTypeFilter = [];
var errorTypeFilter = [];
var errorSourceFilter = [];
module.exports= {
	data: function() {
	    	//绑定数据
	    	return  {errorInfo,queryErrorCondition,pageErrorData,provincesFilter,interfaceTypeFilter,errorTypeFilter,errorSourceFilter,judgePage}
	},
	created: function() {
		this.getProvinces();
		this.getInterfaceType();
		this.getErrorType();
		this.getErrorSource();
		this.initQueryData();
		this.getDataList(this.queryErrorCondition);
	},
	filters: {
                  	area: function(value) {
		        value = $.trim(value);
		        var district = null;
		        if (value.indexOf('.') !== -1) {
		            district = value.substr(0, value.indexOf('.'));
		        } else {
		            district = value;
		        }
		        this.provincesFilter.forEach(function(e) {
		            if (e.name === district) {
		                district = e.value;
		            }
		        })
		        if(district === null){
		            district = value;
		        }
		        return district;
	    	},
	    	tradeType: function(value) {
		        value = $.trim(value);
		        var tradeName = null;
		        this.interfaceTypeFilter.forEach(function(e) {
		            if (e.name === value) {
		                tradeName = e.value;
		            }
		        })
		        if(tradeName === null){
		            tradeName =value;
		        }
		        return tradeName;
	    	},
	    	errorType: function(value) {
		        value = $.trim(value);
		        var errorType = null;
		        this.errorTypeFilter.forEach(function(e) {
		            if ($.trim(e.name)  === value) {//车型错误去空格...
		                errorType = e.value;
		            }
		        })
		        if(errorType === null){
		            errorType = value;
		        }
		        return errorType;
    		},
    		errorSource: function(value) {
		        value = $.trim(value);
		        var errorSource = null;
		        this.errorSourceFilter.forEach(function(e) {
		            if (e.name  === value) {
		                errorSource = e.value;
		            }
		        })
		        if(errorSource === null){
		            errorSource = value;
		        }
		        return errorSource;
    		}
       	 },
	methods: {
		broadcastFilterData: function() {
			this.$broadcast('broadcast-dataList-floatBar-filterData', this.provincesFilter,this.interfaceTypeFilter,this.errorTypeFilter,this.errorSourceFilter);
		},
		getFloatDiv: function(event) {
			this.broadcastFilterData();
			var queryData = event.target.dataset.value;
			if(queryData!==''){
				this.$broadcast('broadcast-dataList-floatBar-queryId', queryData,this.queryErrorCondition.periodType,1);
			}
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
                            	this.judgePage.startDate = today;
                            	this.judgePage.endDate = today;
                            	this.judgePage.periodType = 'TWO_MINUTE';
                            	this.judgePage.insurance = [];
                            	this.judgePage.provinces = [];
                           	this.judgePage.interfaceTypes = [];
                            	this.judgePage.errorType=[];
                            	this.judgePage.errorSource=[];
                            	this.pageErrorData.cur=0;
                            	this.pageErrorData.all=0;
                            	this.provincesFilter = [];
			this.interfaceTypeFilter = [];
			this.errorTypeFilter = [];
			this.errorSourceFilter = [];
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
		getProvinces: function() {
			var _this = this;
			var url = config.host+'statistic/get';
			//var result = fetch(url,{method: 'get',mode: 'cors',});
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
                                                var j = {"provincesInfo":[["110000","北京市"],["120000","天津市"],["130000","河北省"],["140000","山西省"],["150000","内蒙古自治区"],["210000","辽宁省"],["220000","吉林省"],["230000","黑龙江省"],["310000","上海市"],["320000","江苏省"],["330000","浙江省"],["340000","安徽省"],["350000","福建省"],["360000","江西省"],["370000","山东省"],["410000","河南省"],["420000","湖北省"],["430000","湖南省"],["440000","广东省"],["450000","广西壮族自治区"],["460000","海南省"],["500000","重庆市"],["510000","四川省"],["520000","贵州省"],["530000","云南省"],["540000","西藏自治区"],["610000","陕西省"],["620000","甘肃省"],["630000","青海省"],["640000","宁夏回族自治区"],["650000","新疆维吾尔自治区"],["710000","台湾省"],["810000","香港特别行政区"],["820000","澳门特别行政区"]]};
				if(j.provincesInfo!==null && j.provincesInfo.length != 0){
				_this.provincesFilter=[];
			                $.each(j.provincesInfo, function(i,val){    
			                      var obj = {name:j.provincesInfo[i][0] ,value: j.provincesInfo[i][1]}
			                      _this.provincesFilter.push(obj);
			                  }); 
			                _this.$dispatch('dispatch-dataList-errorStatistics-provincesFilter', _this.provincesFilter);
			            }
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		},
		getInterfaceType: function() {
			var _this = this;
			var url = config.host+'statistic/get';
			// var result = fetch(url,{method: 'get',mode: 'cors',});
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
                                                var j= {"interfaceTypeInfo":[["nonAutoDutyPolicyApply","非车责任险保单提交"],["tempSaveApplication","投保单暂存"],["queryActualPrice","车辆实际价值查询"],["calculatePremium","保费计算"],["noCarPolicyApply","非车险保单提交"],["noCarPolicyQuery","非车险保单查询"],["noCarEpolicyInfoReprint","非车险电子保单补打"],["queryOrderApplication","订单状态查询"],["queryPolicyInfoByVin","按车架号查询保单信息"],["personalPolicyApply","人意险保单提交"],["personalEpolicyInfoReprint","人意险电子保单补打"],["queryBizApplication","商业险投保单查询"],["queryCtpApplication","交强险投保单查询"],["queryApplicationDetail","保单详细查询"],["queryVehicleModel","车型查询"],["queryVehicleModelPF","平台车型查询"],["nonAutoPersonalPolicyApply","非车人意险保单提交"],["printDocument","保单打印"],["vehicleQueryValidation","承保交管车辆查询校验"],["printPayNotice","打印缴款通知书"],["vehicleQueryConfirm","承保交管车辆查询确认"],["submitApplication","保单提交"],["applyPayNo","支付号申请"],["printInvoice","发票打印"],["personalPolicyQuery","人意险保单查询"],["4444","555"],["444","888777"],["88","998"]]};
				if(j.interfaceTypeInfo!==null && j.interfaceTypeInfo.length != 0){
				_this.interfaceTypeFilter=[];
			                $.each(j.interfaceTypeInfo, function(i,val){    
			                      var obj = {name:j.interfaceTypeInfo[i][0] ,value: j.interfaceTypeInfo[i][1]}
			                      _this.interfaceTypeFilter.push(obj);
			                  });
			                _this.$dispatch('dispatch-dataList-errorStatistics-interfaceTypeFilter', _this.interfaceTypeFilter);
			            }
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		},
		getErrorType: function() {
			var _this = this;
			var url = config.host+'statistic/get';
			// var result = fetch(url,{method: 'get',mode: 'cors',});
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
                                                var j = {"errorType":[["ACTUAL_DISCOUNT_ERROR","实际折扣率错误"],["AGENT_ERROR","代理点错误"],["AGREEMENT_CONFIG_ERROR","协议配置错误"],["BILL_ERROR","单证错误"],["FUNCTION_ERROR","出单权限错误"],["HANDLER_ERROR","经办人配置错误"],["INSIDE_SYSTEM_ERROR","我司系统错误"],["INSURER_ERROR","保险公司系统错误"],["ORG_CONFIG_ERROR","归属机构配置错误"],["PAYINFO_ERROR","支付信息错误"],["PERSONAL_ERROR","投被保人错误"],["POLICY_PERIOD_ERROR","保险期限错误"],["POLICY_STATUS_ERROR","保单状态错误"],["REPEAT_INSURANCE","重复投保"],["SPECIAL_TEXT_ERROR","特约错误"],["SUM_INSURED_ERROR","保额错误"],["TRAVEL_TAX_ERROR","车船税错误"],["VEHICLE_ERROR","车辆信息错误"],["VEHICLE_MODELS_ERROR ","车型错误"],["NO_RECORD_ERROR","未查询到结果"],["TRANSFER_QUESTION_ERROR","转保问卷错误"],["PRODUCT_CONFIG_ERROR","产品配置错误"],["TERMINAL_NO_ERROR","终端号无效"],["VEHICLE_OWNER_ERROR","车主信息错误"],["RISK_ERROR","子险错误"],["QUERY_CONDITION_ERROR","查询条件错误"],["CHECK_CODE_ERROR","校验码错误"],["CONNECT_ERROR","接口连接错误"],["PLATFORM_ERROR","行业平台系统错误"],["UNKNOWN","未知错误"],["999","999"],["00","001"],["UNKNOWN_ERROR1","未知错误1"]]};
				if(j.errorType!==null && j.errorType.length != 0){
					_this.errorTypeFilter=[];
				            $.each(j.errorType, function(i,val){    
				                      var obj = {name:j.errorType[i][0] ,value: j.errorType[i][1]}
				                      _this.errorTypeFilter.push(obj);
				            });
			                	_this.$dispatch('dispatch-dataList-errorStatistics-errorTypeFilter', _this.errorTypeFilter);
			            }
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		},
		getErrorSource: function() {
			var _this = this;
			var url = config.host+'statistic/get';
			// var result = fetch(url,{method: 'get',mode: 'cors',});
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
                                                var j = {"errorSource":[["INSIDE_SYSTEM_ERROR","我司系统错误"],["INSURER_ERROR","保险公司错误"],["USER_OPERATE_ERROR","用户操作错误"],["UNKNOWN_ERROR","未知错误"],["88uu","uu88"],["UNKNOWN_ERROR1","未知错误adsf1"]]};
				if(j.errorSource!==null && j.errorSource.length != 0){
					_this.errorSourceFilter=[];
				            $.each(j.errorSource, function(i,val){    
				                      var obj = {name:j.errorSource[i][0] ,value: j.errorSource[i][1]}
				                      _this.errorSourceFilter.push(obj);
				            });
			                	_this.$dispatch('dispatch-dataList-errorStatistics-errorSourceFilter', _this.errorSourceFilter);
			            }
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		},
		dispatchPageData: function(pageData) {//将页数总数传递到父组件中,最终传递至子组件[pageBar.vue]中
            		//设置点击页数
            		this.pageErrorData.cur = this.queryErrorCondition.page;
            		this.pageErrorData.all = pageData.reportDetailData.items.pageSize;
            		this.$dispatch('dispatch-error-dataList-pageBar-PageData', this.pageErrorData);
	            },
	            copyProperty: function(queryData) {
                        	//不用Object.assign这个方法,属性值自动替换...不能实时判断是否相等
                        	this.judgePage.startDate = queryData.startDate;
                            	this.judgePage.endDate = queryData.endDate;
                            	this.judgePage.periodType = queryData.periodType;
                            	this.judgePage.insurance.$set(0,queryData.insurance[0]);
                            	this.judgePage.provinces.$set(0,queryData.provinces[0]);
                           	this.judgePage.interfaceTypes.$set(0,queryData.interfaceTypes[0]);
                           	this.judgePage.errorSource.$set(0,queryData.errorSource[0]);
                           	this.judgePage.errorType.$set(0,queryData.errorType[0]);
                        },
                        judgePageForQueryData: function(queryData) {
                        	//防止页码不为一时,执行查询,导致查询不到分页数据
                        	if(this.isEmptyObject(this.judgePage)){
                        		this.copyProperty(queryData);
                        		return;
                        	}
                        	if(this.judgePage.startDate !== queryData.startDate){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.endDate !== queryData.endDate){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.periodType !== queryData.periodType){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.insurance[0] !== queryData.insurance[0]){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.provinces[0] !== queryData.provinces[0]){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.interfaceTypes[0] !== queryData.interfaceTypes[0]){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.errorSource[0] !== queryData.errorSource[0]){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        	if(this.judgePage.errorType[0] !== queryData.errorType[0]){
                        		this.copyProperty(queryData);
                        		queryData.page = 1;
                        		return;
                        	}
                        },
	            getDataList: function(queryData) {
	            	this.judgePageForQueryData(queryData);
	            	var _this = this;
	            	var url = config.host+'statistic/addFollowUpInfo';
			// var result = fetch(url,{
			// 	method: 'post',
			// 	mode: 'cors',
			// 	headers: {
			// 		'Accept': 'application/json',
			// 		'Content-Type': 'application/json'
			// 	},
			// 	  body: JSON.stringify({
			// 	 	'startDate': queryData.startDate,
			// 	 	'endDate': queryData.endDate,
			// 	 	'periodType': queryData.periodType,
			// 	 	'insurers': queryData.insurance,
			// 	 	'provinces': queryData.provinces,
		 // 		   	'interfaceTypes': queryData.interfaceTypes,
		 // 		   	'errorSources': queryData.errorSource,
		 // 		   	'errorTypes': queryData.errorType,
			// 	 	'page': queryData.page, 
			// 	 	'size': queryData.size
			// 	  })
			// });
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
                                                var j = {"reportDetailData":{"columnNames":[["insurer","保险公司"],["period","时间"],["interfaceType","交易类型"],["province","承保地区"],["errorSource","错误来源"],["errorType","错误类型"],["errorCount","错误数"],["errorDesc","错误信息"]],"items":{"total":6,"content":[["cpic","2016/08/25 13:56","queryApplicationDetail","340000","INSURER_ERROR","NO_RECORD_ERROR",1,"没有查询到相应的数据"],["cpic","2016/08/25 13:58","queryApplicationDetail","340000","INSURER_ERROR","NO_RECORD_ERROR",3,"没有查询到相应的数据"],["cpic","2016/08/25 14:06","queryApplicationDetail","340000","INSURER_ERROR","INSURER_ERROR",1,"调用后端服务超时"],["cpic","2016/08/25 14:06","queryApplicationDetail","340000","INSURER_ERROR","NO_RECORD_ERROR",1,"没有查询到相应的数据"],["cpic","2016/08/25 14:08","queryApplicationDetail","340000","INSURER_ERROR","NO_RECORD_ERROR",1,"没有查询到相应的数据"],["cpic","2016/08/25 14:14","queryApplicationDetail","340000","INSURER_ERROR","NO_RECORD_ERROR",1,"没有查询到相应的数据"]],"pageNumber":0,"pageSize":1,"totalPage":0}}};
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.errorInfo = Object.assign(_this.errorInfo, j);
				//分页赋值处理
				_this.dispatchPageData(_this.errorInfo);
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
	            }
	},
	events: {
		'broadcast-errorStatistics-dataEchart-dataList-floatData': function(insurance,errorType,queryTime) {
			this.broadcastFilterData();
			var errorTypeCode = '';
			if(errorType !== ''){
				this.errorTypeFilter.forEach(function(e) {
				            if ($.trim(e.value)  === errorType) {//车型错误去空格...
				                errorTypeCode = e.name;
				            }
			        	});
			}
			var queryData = {
				'insurer': insurance,
				'period': '',
				'periodType': '',
				'interfaceType': '',
				'province': '',
				'errorSource': '',
				'errorType': errorTypeCode,
				'errorDesc': '',
				'periodBegin': queryTime.beginTime,
				'periodEnd': queryTime.endTime,
				'page': 1
			}
			this.$broadcast('broadcast-dataList-floatBar-dataEchart-querydata', queryData);
		},
		'broadcast-errorStatistics-dataList-curPage': function(curPage) {//获取[pageBar.vue]传递至父组件的数据,当前点击页数
	    		//查询当前页数信息
	    		this.queryErrorCondition.page = curPage;
	    		//调用查询
	    		this.getDataList(this.queryErrorCondition);
	    	},
	    	 'broadcast-errorStatistics-dataEchart-dataList-queryCondition': function(data) {
		      	// 获取共享查询条件
		      	this.queryErrorCondition = Object.assign({}, this.queryErrorCondition, data);
		      	this.getDataList(this.queryErrorCondition);
		    }
	},
	components: {
		pageBar: require ('./pageBar.vue'),
		floatBar: require ('./floatBar.vue')
	}
}
</script>

<style type="text/css" >
.spanFloatDiv {
	display: block;
	color: #428bca;
	width: 80px;
	height: 30px; 
	cursor: pointer; 
	text-align: right;
	line-height: 30px;
}

.listDiv {
    margin-left: 5px;
    margin-right: 5px;

    min-width: 1000px;
}

.dataList {
    width: 100%;
    text-align: center;
    vertical-align: middle;
    min-width: 1000px;
}

.dataList table {
            table-layout: fixed;
}

.dataList thead tr {
    border-radius: 5px;
    border: 1px solid #d6d6d6;
    background: #e2edfb;
    vertical-align: middle;
}

.dataList thead tr td {
    font-size: 12px;
    height: 25px;
    font-weight: bold;
    font-family: 微软雅黑;
    min-width: 45px;
    vertical-align: middle;
        padding:0 10px;
}

.dataList tbody tr {
    border-bottom: 1px #e5e5e5 solid;
}

.dataList tbody tr td {
        padding:0 10px;
    height: 30px;
    font-size:12px;
    font-family: 微软雅黑;
    vertical-align: middle;
}

.dataList tbody tr td  .ellipsis-p{
        width: 300px;
        overflow:hidden; /* td不生效所以要放在标签中 */
        text-overflow: ellipsis;
        white-space: nowrap;
        margin-bottom: 0;
}

.dataList tbody tr:nth-child(odd)  {
     background: #EEE;
}

.noneDataDiv {
    text-align: center;
    vertical-align: middle;
    font-weight: bold;
    font-family: 微软雅黑;
}
</style>