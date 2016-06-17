<template>
	<div v-show="pageData.all!==0" class="listDiv">
			<table class="dataList">
				<thead>
					<tr>
						<td align="center">{{info.reportDetailData.columnNames[0][1]}}</td>
						<td align="center">{{info.reportDetailData.columnNames[1][1]}}</td>
						<td align="left">{{info.reportDetailData.columnNames[2][1]}}</td>
						<td align="center">{{info.reportDetailData.columnNames[3][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[4][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[5][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[6][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[7][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[8][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[9][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[10][1]}}</td>
						<td align="right" style="padding-right:4px;">{{info.reportDetailData.columnNames[11][1]}}</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in info.reportDetailData.items.content" :item="item">
						<td align="center" style="width:10%; padding-right:4px;">
							{{item[0]|insurance}}
						</td>
						<td align="center"style="width:15%;padding-right:4px;">
							{{item[1]}}
						</td>
						<td align="left"style="width:15%;padding-right:4px;">
							{{item[2]|tradeType}}
						</td>
						<td align="center"style="width:11%;padding-right:4px;">
							{{item[3]|area}}
						</td>
						<td align="right" class="setWidth"style="width:7%;padding-right:4px;">
							{{item[4]}}
						</td>
						<td align="right"style="width:7%;padding-right:4px;">
							{{item[5]}}
						</td>
						<td align="right"style="width:7%;padding-right:4px;">
							{{item[6]}}
						</td>
						<td align="right" style="width:6%;padding-right:4px;">
							{{item[7]|parseNum}}%
						</td>
						<td align="right"style="width:6%;padding-right:4px;">
							{{item[8]}}
						</td>
						<td align="right"style="width:6%;padding-right:4px;">
							{{item[9]}}
						</td>
						<td align="right"style="width:6%;padding-right:4px;">
							{{item[10]}}
						</td>
						<td align="right"style="width:6%;padding-right:4px;">
							{{item[11]|parseNum}}
						</td>
					</tr>
				</tbody>
			</table>
			<page-bar></page-bar>
	</div>
	<div v-else class="noneDataDiv">
		暂无接口交互数据!
	</div>
</template>

<script>
'use strict';
var config = require('../../lib/js/config');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化查询条件
var arr1 = [];
var arr2 = [];
var queryCondition={
            startDate: today,
            endDate: today,
            contrastStartDate: '',
            contrastEndDate: '',
            periodType: 'TWO_MINUTE',//默认实时
            indicators: ['TOTAL_COUNT','MAX_DURATION'],//默认总交易数
            provinces: arr1,
            interfaceTypes: arr2,
            page: 1,
            size: 20//默认每页20条
}
var pageData = {
	cur: 0,
	all: 0
}
var array = [['',''],['',''],['',''],['',''],['',''],['',''],['',''],['',''],['',''],['',''],['',''],['','']];
var info = {
	reportDetailData: {
		columnNames: array,
		items: {
			total: {},
			content: {},
			pageSize: {}
		}
	}
}
module.exports= {
	data: function() {
	    //绑定数据
	    return  {info,queryCondition,pageData}
	},
	created: function() {
		this.getDataList(this.queryCondition);
	},
	methods: {
		dispatchPageData: function(pageData) {//将页数总数传递到父组件中,最终传递至子组件[pageBar.vue]中
	            		//设置点击页数
	            		this.pageData.cur = this.queryCondition.page;
	            		this.pageData.all = pageData.reportDetailData.items.pageSize;
	            		this.$dispatch('dispatch-dataList-pageBar-PageData', this.pageData);
	            },
	            getDataList: function(queryData) {
	            		var _this = this;
	            		var url = config.host+'statistic/getStatisticDetail';
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
				 		    'contrastStartDate': queryData.contrastStartDate,
				 		    'contrastEndDate': queryData.contrastEndDate,
				 		    'periodType': queryData.periodType,
				 		    'indicators': queryData.indicators,
				 		    'provinces': queryData.provinces,
		 		   		    'interfaceTypes': queryData.interfaceTypes,
				 		    'page': queryData.page, 
				 		    'size': queryData.size
				  })
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				_this.info = Object.assign(_this.info, j);
				//分页赋值处理
				_this.dispatchPageData(_this.info);
			}).catch(function(ex) {
				console.log('failed', ex)
			});
	            }
	},
	events: {
		'broadcast-todayStatistics-dataList-curPage': function(curPage) {//获取[pageBar.vue]传递至父组件的数据,当前点击页数
	    		//查询当前页数信息
	    		this.queryCondition.page = curPage;
	    		//调用查询
	    		this.getDataList(this.queryCondition);
	    	},
	    	 'broadcast-todayStatistics-dataEchart-dataList-queryCondition': function(data) {
		      	// 获取共享查询条件
		      	this.queryCondition = Object.assign({}, this.queryCondition, data);
		      	this.getDataList(this.queryCondition);
		    }
	},
	components: {
		pageBar: require ('./pageBar.vue')
	}
}
</script>

<style type="text/css">
.listDiv {
	margin-left: 5px;
	margin-right: 5px;
	border-radius: 5px;
	height: 28px;
	border: 1px solid #d6d6d6;
	background: #e2edfb;
	vertical-align: middle;
	min-width: 1000px;
}

.dataList {
	margin-top: 3px;
	width: 100%;
	display:block;
	text-align: center;
	vertical-align: middle;
	min-width: 1000px;
}

.dataList thead {
	width: 100%;
	min-width: 1000px;
	vertical-align: middle;
}

.dataList thead tr{
	width: 100%;
	min-width: 1000px;
	height: 25px;
	vertical-align: middle;
}

.dataList thead tr td {
	font-size: 15px;
	height: 25px;
	font-weight: bold;
	font-family: 微软雅黑;
	min-width: 30px;
	vertical-align: middle;
	overflow: hidden;
    	text-overflow: ellipsis;
    	white-space: nowrap;
}

.dataList tbody {
	margin-top: 10px;
}

.dataList tbody tr {
	height: 30px;
	width: 100%;
	padding-top: 5px;
	padding-bottom: 5px;
	vertical-align: middle;
	padding: 10px 0px 0px 10px;
	border-bottom: 1px #e5e5e5 solid;
}

.dataList tbody tr td {
	height: 30px;
	font-size:14px;
	font-family: 微软雅黑;
	vertical-align: middle;
    	width: 200px;
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

.setWidth {
	width: 30px;
}
</style>