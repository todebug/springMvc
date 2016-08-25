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
			var url = config.host+'statistic/getProvinces';
			var result = fetch(url,{method: 'get',mode: 'cors',});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				if(j.provincesInfo!==null && j.provincesInfo.length != 0){
					_this.provincesFilter=[];
			                	$.each(j.provincesInfo, function(i,val){    
			                      		var obj = {name:j.provincesInfo[i][0] ,value: j.provincesInfo[i][1]}
			                      		_this.provincesFilter.push(obj);
			                  	}); 
					_this.$dispatch('dispatch-dataList-interfaceLog-provincesFilter', _this.provincesFilter);
			            }
			}).catch(function(ex) {
				console.log('failed', ex)
			});
		},
		getInterfaceType: function() {
			var _this = this;
			var url = config.host+'statistic/getInterfaceType';
			var result = fetch(url,{method: 'get',mode: 'cors',});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				if(j.interfaceTypeInfo!==null && j.interfaceTypeInfo.length != 0){
					_this.interfaceTypeFilter=[];
			                	$.each(j.interfaceTypeInfo, function(i,val){    
			                      		var obj = {name:j.interfaceTypeInfo[i][0] ,value: j.interfaceTypeInfo[i][1]}
			                      		_this.interfaceTypeFilter.push(obj);
			                  	}); 
			               	_this.$dispatch('dispatch-dataList-interfaceLog-interfaceTypeFilter', _this.interfaceTypeFilter);
			            }
			}).catch(function(ex) {
				console.log('failed', ex)
			});
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
	            	var url = config.host+'statistic/getlogDetail';
			var result = fetch(url,{
				method: 'post',
				mode: 'cors',
				headers: {
					'Accept': 'application/json',
				     	'Content-Type': 'application/json'
				},
				body: JSON.stringify({
				 	'id': Number(logId)
				})
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.show = Object.assign(_this.show, j);
				_this.$nextTick(function() {
			  		$('#_TR'+logId).after($('#showTr').clone().show().addClass('trld'));
			  	});
			}).catch(function(ex) {
				console.log('failed', ex)
			});
		} 
	},
	components: {
		pageBar: require ('./pageBar.vue'),
		showTr: require ('./showTr.vue')
	}
}
</script>

<style type="text/css" scoped>

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