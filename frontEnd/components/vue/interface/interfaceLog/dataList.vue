<template>
	<div v-show="info.total>0" class="listDiv">
			<table class="dataList">
				<thead>
					<tr>
						<td align="left">Id</td>
						<td>保险公司</td>
						<td align="left">承保省份</td>
						<td align="left">请求类型</td>
						<td align="left">订单号</td>
						<td align="left">车牌号</td>
						<td align="left">vin码</td>
						<td align="left">被保险人</td>
						<td>投保单号</td>
						<!-- <td>请求业务号</td>
						<td>返回业务号</td>
						<td>平台查询码</td>
						<td>平台确认码</td> -->
						<td>请求时间</td>
						<!-- <td>响应时间</td> -->
						<td>处理状态</td>
						<!-- <td align="left">经销商</td> -->
						<td align="left">错误提示信息</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in info.logList" :item="item" id="_TR{{item.id}}">
						<td align="left" >
							<a href="javascript:void(0);" style="text-decoration: none; cursor: pointer;" @click="getLogDetail">{{item.id}}</a>
						</td>
						<td>
							{{item.insurer | insurance}}
						</td>
						<td align="left">
							{{item.province | area | line}}
						</td>
						<td align="left">
							{{item.interfaceType | tradeType | line}}
						</td>
						<td align="left">
							{{item.orderNo | line}}
						</td>
						<td align="left">
							{{item.plateNo | line}}
						</td>
						<td align="left">
							{{item.vin | line}}
						</td>
						<td align="left">
							{{item.insured | line}}
						</td>
						<td>
							{{item.applicationNo | line}}
						</td>
						<!-- <td>
							{{item.requestNo}}
						</td>
						<td>
							{{item.responseNo}}
						</td>
						<td>
							{{item.queryNo}}
						</td>
						<td>
							{{item.confirmNo}}
						</td> -->
						<td>
							{{item.requestTime | date | line}}
						</td>
						<!-- <td>
							{{item.responseTime | date}}
						</td> -->
						<td>
							{{item.status | line}}
						</td>
						<!-- <td align="left">
							{{item.dealerName}}
						</td> -->
						<td align="left" style="width:200;">
							<p class="ellipsis-p" title="{{item.errorMessage}}" style="width:200;">
								{{item.errorMessage | line}}
							</p>
						</td>
					</tr>
					<tr id="showTr" style='display:none'>
						<td colspan="12">
							<show-Tr :show="show"></show-Tr>
						</td>
					</tr>
				</tbody>
			</table>
			<page-bar></page-bar>
	</div>
	<!-- <div v-show="info.total===-1" class="noneDataDiv">
		正在查询......
	</div> -->
	<div v-show="info.total===0" class="noneDataDiv">
		暂无接口交互数据!
	</div>

</template>

<script>
'use strict';
var config = require('../../../lib/js/config');
var show={
	res: {},
	req: {},
	requestNo: '',
	responseNo: '',
	queryNo: '',
	confirmNo: '',
	responseTime: '',
	dealerName: '',
}
var showFlag = {
	flag: true,
	id: -1
};
var provincesFilter = [];
var interfaceTypeFilter = [];
module.exports= {
	props: ['info'],  
	data: function () {
	    	//绑定数据
	    	return  {show,showFlag,provincesFilter,interfaceTypeFilter}
	},
	created: function() {
		this.getProvinces();
		this.getInterfaceType();
		this.initQueryData();
	},
	events: {
		'broadcast-interfaceLog-dataList-closeTr': function(closeTr) {
			$('.trld').remove();
		}
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
       	 },
	methods:  {
		initQueryData: function() {
			this.show.res = {};
			this.show.req = {};
			this.show.requestNo = '';
			this.show.responseNo = '';
			this.show.queryNo = '';
			this.show.confirmNo = '';
			this.show.responseTime = '';
			this.show.dealerName = '';
			this.showFlag.flag=true;
			this.showFlag.id=-1;
		},
		getProvinces: function() {
			var _this = this;
			var url = config.host+'statistic/get';
			// var result = fetch(url,{method: 'get',mode: 'cors',});
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
					_this.$dispatch('dispatch-dataList-interfaceLog-provincesFilter', _this.provincesFilter);
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
				var j = {"interfaceTypeInfo":[["nonAutoDutyPolicyApply","非车责任险保单提交"],["tempSaveApplication","投保单暂存"],["queryActualPrice","车辆实际价值查询"],["calculatePremium","保费计算"],["noCarPolicyApply","非车险保单提交"],["noCarPolicyQuery","非车险保单查询"],["noCarEpolicyInfoReprint","非车险电子保单补打"],["queryOrderApplication","订单状态查询"],["queryPolicyInfoByVin","按车架号查询保单信息"],["personalPolicyApply","人意险保单提交"],["personalEpolicyInfoReprint","人意险电子保单补打"],["queryBizApplication","商业险投保单查询"],["queryCtpApplication","交强险投保单查询"],["queryApplicationDetail","保单详细查询"],["queryVehicleModel","车型查询"],["queryVehicleModelPF","平台车型查询"],["nonAutoPersonalPolicyApply","非车人意险保单提交"],["printDocument","保单打印"],["vehicleQueryValidation","承保交管车辆查询校验"],["printPayNotice","打印缴款通知书"],["vehicleQueryConfirm","承保交管车辆查询确认"],["submitApplication","保单提交"],["applyPayNo","支付号申请"],["printInvoice","发票打印"],["personalPolicyQuery","人意险保单查询"],["4444","555"],["444","888777"],["88","998"]]};
				if(j.interfaceTypeInfo!==null && j.interfaceTypeInfo.length != 0){
					_this.interfaceTypeFilter=[];
			                	$.each(j.interfaceTypeInfo, function(i,val){    
			                      		var obj = {name:j.interfaceTypeInfo[i][0] ,value: j.interfaceTypeInfo[i][1]}
			                      		_this.interfaceTypeFilter.push(obj);
			                  	}); 
			               	_this.$dispatch('dispatch-dataList-interfaceLog-interfaceTypeFilter', _this.interfaceTypeFilter);
			            }
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		},
		getLogDetail: function(event) {
			var logId = event.target.innerHTML;
			if(showFlag.flag===false && showFlag.id===logId){
				showFlag.flag = true;
				$('.trld').remove();
				return;
			}
			var _this = this;
			$('.trld').remove();		
			this.handleInfoShowData(logId);
			this.getLogDetailInfo(logId);
		  	showFlag.flag = false;	
		  	showFlag.id = logId;
		},
		handleInfoShowData: function(logId) {
			var _this = this;
			if(this.info.logList !==null && this.info.logList.length != 0){
				$.each(this.info.logList, function(i,val){    
				      	if(val.id===Number(logId)){
				      		_this.show = Object.assign(_this.show, val);
				      	}
				}); 
			}
		},
		getLogDetailInfo: function(logId) {
			var _this = this;
	            	var url = config.host+'statistic/addFollowUpInfo';
			// var result = fetch(url,{
			// 	method: 'post',
			// 	mode: 'cors',
			// 	headers: {
			// 		'Accept': 'application/json',
			// 	     	'Content-Type': 'application/json'
			// 	},
			// 	body: JSON.stringify({
			// 	 	'id': Number(logId)
			// 	})
			// });
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
				var j = {"res":{"messageId":null,"logid":659951,"serialNo":null,"messageType":null,"content1":"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<response> \n  <head> \n    <partnerCode>SAIC</partnerCode>  \n    <transactionCode>106012</transactionCode>  \n    <messageId>LSVAB2BR8FN148461wsjg8QLz</messageId>  \n    <transactionEffectiveDate>2016-08-25 14:15:22</transactionEffectiveDate>  \n    <responseCompleteMessageStatus> \n      <messageStatusCode>111111</messageStatusCode>  \n      <messageStatusDescriptionList> \n        <messageStatusDescription> \n          <messageStatusSubCode>111111</messageStatusSubCode>  \n          <messageStatusSubDescription>未找到符合条件的保单信息。</messageStatusSubDescription> \n        </messageStatusDescription> \n      </messageStatusDescriptionList>  \n      <messageStatusDescriptionNumber>1</messageStatusDescriptionNumber> \n    </responseCompleteMessageStatus> \n  </head> \n</response>\n","content2":null,"content3":null,"content4":null,"createTime":null},"req":{"messageId":null,"logid":659951,"serialNo":null,"messageType":null,"content1":"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<request> \n  <head> \n    <partnerCode>SAIC</partnerCode>  \n    <transactionCode>106012</transactionCode>  \n    <messageId>LSVAB2BR8FN148461wsjg8QLz</messageId>  \n    <transactionEffectiveDate>2016-08-25 14:15:22</transactionEffectiveDate>  \n    <user>SAIC</user>  \n    <password>UJNR/UNX9u7FiBqfaSRyoEiuOkHWbmbQQbOUOi6vNXn9B+ZeA2Z0INZlMv0DU+zGjI4GgC2UTPOOl/zJGPjpAQ==</password> \n  </head>  \n  <body> \n    <QueryPolicyDetailRequest> \n      <policyNo>AHEFHB1ZH915B005214U</policyNo>  \n      <applicationNo/>  \n      <agentCode>T6M</agentCode> \n    </QueryPolicyDetailRequest> \n  </body> \n</request>\n","content2":null,"content3":null,"content4":null,"createTime":null}};
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.show = Object.assign(_this.show, j);
				_this.$nextTick(function() {
			  		$('#_TR'+logId).after($('#showTr').clone().show().addClass('trld'));
			  	});
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		} 
	},
	components: {
		pageBar: require ('./pageBar.vue'),
		showTr: require ('./showTr.vue')
	}
}
</script>

<style type="text/css" >

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