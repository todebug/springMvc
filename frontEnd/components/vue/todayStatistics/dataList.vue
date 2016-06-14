<template>
	<div class="listDiv">
			<table class="dataList">
				<thead>
					<tr>
						<td>{{info.reportDetailData.columnNames[0][1]}}</td>
						<td>{{info.reportDetailData.columnNames[1][1]}}</td>
						<td>{{info.reportDetailData.columnNames[2][1]}}</td>
						<td>{{info.reportDetailData.columnNames[3][1]}}</td>
						<td>{{info.reportDetailData.columnNames[4][1]}}</td>
						<td>{{info.reportDetailData.columnNames[5][1]}}</td>
						<td>{{info.reportDetailData.columnNames[6][1]}}</td>
						<td>{{info.reportDetailData.columnNames[7][1]}}</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in info.reportDetailData.items.content" :item="item">
					<td>
						{{item[0]}}
					</td>
					<td>
						{{item[1]}}
					</td>
					<td>
						{{item[2]}}
					</td>
					<td>
						{{item[3]}}
					</td>
					<td>
						{{item[4]}}
					</td>
					<td>
						{{item[5]}}
					</td>
					<td>
						{{item[6]}}
					</td>
					<td>
						{{item[7]}}%
					</td>
				</tr>
				</tbody>
			</table>
			<page-bar></page-bar>
	</div>
	<!-- <data-Item  v-for="item in info.reportDetailData.items.content"  :item="item"></data-Item> -->
</template>

<script>
'use strict';
var config = require('../../lib/js/config');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
//初始化查询条件
var queryCondition={
            startDate: today,
            endDate: today,
            contrastStartDate: '',
            contrastEndDate: '',
            periodType: 'TWO_MINUTE',//默认实时
            indicators: ['TOTAL_COUNT'],//默认总交易数
            page: 1,
            size: 20//默认每页20条
}
var pageData = {
	cur: 0,
	all: 0
}
var array = [['',''],['',''],['',''],['',''],['',''],['',''],['',''],['','']]
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
	ready: function() {
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
	    	 'broadcast-todayStatistics-dataEchart-dataList-queryCondition': function(date) {
		      	// 获取共享查询条件
		      	this.queryCondition = Object.assign({}, this.queryCondition, date);
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
	height: 26px;
	border: 1px solid #d6d6d6;
	background: grey;
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
	text-align: center;
	vertical-align: middle;
}

.dataList thead tr td {
	color: #fff;
	font-weight: bold;
	min-width: 25px;
	text-align: center;
	vertical-align: middle;
	overflow: hidden;
    	text-overflow: ellipsis;
    	white-space: nowrap;
}

.dataList tbody tr {
	height: 30px;
	width: 100%;
	padding-top: 5px;
	padding-bottom: 5px;
	text-align: center;
	vertical-align: middle;
	padding: 10px 0px 0px 10px;
	border-bottom: 1px #e5e5e5 solid;
}

.dataList tbody tr td {
	height: 30px;
	font-size:10px;
	text-align: center;
	vertical-align: middle;
    	width: 200px;
}

.dataList tbody tr:nth-child(odd)  {
   	 background: #EEE;
}
</style>