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
			                _this.$dispatch('dispatch-dataList-errorStatistics-provincesFilter', _this.provincesFilter);
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
			                _this.$dispatch('dispatch-dataList-errorStatistics-interfaceTypeFilter', _this.interfaceTypeFilter);
			            }
			}).catch(function(ex) {
				console.log('failed', ex)
			});
		},
		getErrorType: function() {
			var _this = this;
			var url = config.host+'statistic/getErrorType';
			var result = fetch(url,{method: 'get',mode: 'cors',});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				if(j.errorType!==null && j.errorType.length != 0){
					_this.errorTypeFilter=[];
				            $.each(j.errorType, function(i,val){    
				                      var obj = {name:j.errorType[i][0] ,value: j.errorType[i][1]}
				                      _this.errorTypeFilter.push(obj);
				            });
			                	_this.$dispatch('dispatch-dataList-errorStatistics-errorTypeFilter', _this.errorTypeFilter);
			            }
			}).catch(function(ex) {
				console.log('failed', ex)
			});
		},
		getErrorSource: function() {
			var _this = this;
			var url = config.host+'statistic/getErrorSource';
			var result = fetch(url,{method: 'get',mode: 'cors',});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				if(j.errorSource!==null && j.errorSource.length != 0){
					_this.errorSourceFilter=[];
				            $.each(j.errorSource, function(i,val){    
				                      var obj = {name:j.errorSource[i][0] ,value: j.errorSource[i][1]}
				                      _this.errorSourceFilter.push(obj);
				            });
			                	_this.$dispatch('dispatch-dataList-errorStatistics-errorSourceFilter', _this.errorSourceFilter);
			            }
			}).catch(function(ex) {
				console.log('failed', ex)
			});
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
	            	var url = config.host+'statistic/getErrorInfoDetail';
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
		 		   	'errorTypes': queryData.errorType,
				 	'page': queryData.page, 
				 	'size': queryData.size
				  })
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.errorInfo = Object.assign(_this.errorInfo, j);
				//分页赋值处理
				_this.dispatchPageData(_this.errorInfo);
			}).catch(function(ex) {
				console.log('failed', ex)
			});
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

<style type="text/css" scoped>
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