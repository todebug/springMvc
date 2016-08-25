<template>
	<div class="floatBar" v-bind:class="{ 'displayDivHide': isHide}">
		<div class="floatbar-header">
			<h3 class="floatbar-title">错误信息明细</h3>
			<button @click="closeFloatDiv" class="floatbar-close">关闭</button>
		</div>
		<div class="floatbar-body" v-show="info.iisErrorLogList.length>0">
			<table class="dataList">
				<thead>
					<tr>
						<td align="left">Id</td>
						<td>保险公司</td>
						<td>统计时间</td>
						<td align="left">交易类型</td>
						<td align="left">承保地区</td>
						<td align="left">错误来源</td>
						<td align="left">错误类型</td>
						<td align="left">错误描述</td>
						<td align="left">错误信息</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in info.iisErrorLogList" :item="item" id="_TR{{item.id}}">
						<td align="left" >
							<a href="javascript:void(0);" style="text-decoration: none; cursor: pointer;" @click="getLogDetail">{{item.id}}</a>
						</td>
						<td>
							{{item.insurer | insurance}}
						</td>
						<td>
							{{item.period | date}}
						</td>
						<td align="left">
							{{item.interfaceType | tradeType | line}}
						</td>
						<td align="left">
							{{item.province | area | line}}
						</td>
						<td align="left">
							{{item.errorSource | errorSource}}
						</td>
						<td align="left">
							{{item.errorType| errorType}}
						</td>
						<td align="left" style="width:200;">
							<p class="ellipsis-p" title="{{item.errorDesc}}" style="width:200;">
								{{item.errorDesc | line}}
							</p>
						</td>
						<td align="left" style="width:200;">
							<p class="ellipsis-p" title="{{item.errorMessage}}" style="width:200;">
								{{item.errorMessage | line}}
							</p>
						</td>
					</tr>
					<tr id="showTr" style='display:none'>
						<td colspan="10">
							<show-Tr :show="show"></show-Tr>
						</td>
					</tr>
				</tbody>
			</table>
			<page-bar-sub></page-bar-sub>
		</div>
		<div class="floatbar-body" v-show="info.iisErrorLogList.length===0">
			暂无接口交互数据!
		</div>
		<div class="floatbar-footer"></div>	
	</div>
</template>

<script>
'use strict';
var config = require('../../../lib/js/config');
var isHide=true;
var info = {
	iisErrorLogList: [],
	total: ''
}
var show={
	res: {},
	req: {},
	interfaceLogData: {}
}
var showFlag = {
	flag: true,
	id: -1
};
var provincesFilter = [];
var interfaceTypeFilter = [];
var errorTypeFilter = [];
var errorSourceFilter = [];
var queryData = {
	insurer: '',
	period: '',
	periodType: '',
	interfaceType: '',
	province: '',
	errorSource: '',
	errorType: '',
	errorDesc: '',
	periodBegin: null,
	periodEnd: null,
	page: 1
};
var pageDataSub = {
	cur: 0,
	all: 0
}
module.exports= {
	data: function() {
	    	return  {isHide,info,show,showFlag,provincesFilter,interfaceTypeFilter,errorTypeFilter,errorSourceFilter,queryData,pageDataSub}
	},            
	created: function() {
                    	this.initQueryData();
            },
      	methods: {
                        initQueryData: function() {
                   		this.isHide=true;
                   		this.info.iisErrorLogList = [];
                   		this.info.total = '';
                   		this.queryData.insurer = '';
			this.queryData.period = '';
			this.queryData.periodType = '';
			this.queryData.interfaceType = '';
			this.queryData.province = '';
			this.queryData.errorSource = '';
			this.queryData.errorType = '';
			this.queryData.errorDesc = '';
			this.queryData.periodBegin = null;
			this.queryData.periodEnd = null;
			this.queryData.page = 1;
                   		this.show.res = {};
			this.show.req = {};
			this.show.interfaceLogData = {};
			this.showFlag.flag=true;
			this.showFlag.id=-1;
			this.pageDataSub.cur=0;
                        	this.pageDataSub.all=0;
                        	this.provincesFilter = [];
			this.interfaceTypeFilter = [];
			this.errorTypeFilter = [];
			this.errorSourceFilter = [];
                        },
                        closeFloatDiv: function() {
                        	$('.trld').remove();
                        	this.isHide=true;
                        },
                        getPageData: function(total, page) {
                        	this.pageDataSub.cur=page;
                        	this.pageDataSub.all=total;
                        	this.$broadcast('broadcast-floatBar-pageBarSub-getPageData', this.pageDataSub);
                        },
                        getIisErrorLogList: function(queryData) {
                        	var _this = this;
	            	var url = config.host+'statistic/getIisErrorLogList';
			var result = fetch(url,{
				method: 'post',
				mode: 'cors',	
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
		 		    	'insurer': queryData.insurer,
		 		    	'period': queryData.period,
		 		    	'periodType': queryData.periodType,
		 		    	'interfaceType': queryData.interfaceType,
		 		    	'province': queryData.province,
		 		    	'errorSource': queryData.errorSource,
		 		    	'errorType': queryData.errorType,
 		   		    	'errorDesc': queryData.errorDesc,
 		   		    	'periodBegin': queryData.periodBegin,
 		   		    	'periodEnd': queryData.periodEnd,
 		   		    	'page': queryData.page, 
		 		    	'size': 20
				})
			});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.info = Object.assign(_this.info, j);
				_this.getPageData(_this.info.total, queryData.page);
				_this.$nextTick(function() {
			  		_this.isHide=false;
			  	});
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
			if(this.info.iisErrorLogList !==null && this.info.iisErrorLogList.length != 0){
				$.each(this.info.iisErrorLogList, function(i,val){    
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
				 	'id': Number(logId),
				 	'errorFLag': true
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
	events: {
		'broadcast-dataList-floatBar-dataEchart-querydata': function(queryPieData) {
			this.queryData = Object.assign(this.queryData, queryPieData);
			this.getIisErrorLogList(this.queryData);
		},
		'dispatch--pageBarSub-floatBar-closeTr': function(closeTr) {
			$('.trld').remove();
		},
		'dispatch-pageBarSub-floatBar-curPage': function(curPage) {
			this.queryData.page = curPage;
			this.getIisErrorLogList(this.queryData);
		},
		'broadcast-dataList-floatBar-queryId': function(query,periodType,page) {
			var data = query.split('|||');
			this.queryData.periodType = periodType;
			this.queryData.insurer = data[0];
			this.queryData.period = data[1];
			this.queryData.interfaceType = data[2];
			this.queryData.province = data[3];
			this.queryData.errorSource = data[4];
			this.queryData.errorType = data[5];
			this.queryData.errorDesc = data[7];
			this.queryData.periodBegin = null;
			this.queryData.periodEnd = null;
			this.queryData.page = page;
			this.getIisErrorLogList(this.queryData);
		},
		'broadcast-dataList-floatBar-filterData': function(provinces,interfaceType,errorType,errorSource) {
			var _this = this;
			if(_this.provincesFilter.length===0){
				provinces.forEach(function(e) {
					_this.provincesFilter.push(e);
				});
			}
			if(_this.interfaceTypeFilter.length===0){
				interfaceType.forEach(function(e) {
					_this.interfaceTypeFilter.push(e);
				});
			}
			if(_this.errorTypeFilter.length===0){
				errorType.forEach(function(e) {
					_this.errorTypeFilter.push(e);
				});
			}
			if(_this.errorSourceFilter.length===0){
				errorSource.forEach(function(e) {
					_this.errorSourceFilter.push(e);
				});
			}
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
	components: {
		showTr: require ('./showTr.vue'),
		pageBarSub: require ('./pageBarSub.vue'),
	}
}
</script>

<style type="text/css">
.floatBar {
	position: fixed;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	z-index: 10000;
	background: #fff;
	border: 1px #ccc solid;
	overflow: auto;
}
.floatbar-header {
	height: 40px;
	width: 100%;
	padding: 0 20px;
	border-bottom: 1px #ccc solid;
	background: #f7f7f7;
	margin-bottom: 10px;
}
.floatbar-header .floatbar-title {
	float: left;
	line-height: 40px;
	font-size: 16px;
	margin: 0;
}

.floatbar-header  .floatbar-close{
	float: right;
	padding: 2px 10px;
	border: 1px #ccc solid;
	margin-top: 6px;
	background: #fff;
	border-radius: 3px;
}

.displayDivHide {
	display: none;
}

</style>