<template>
	<div class="queryBarError">
		<ul>
			<li>
				<div>
					<span>
						<label>保险公司</label>
						<select name="policyCompany" id="policyCompany" @change="selectInsurance ">
							<option value="" selected="selected">全部</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>承保地区</label>
						<select  name="province" id="province"@change="selectProvinces">
							<option value="" selected="selected">全部</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>交易类型</label>
						<select name="interfaceType" id="interfaceType"@change="selectInterfaceTypes">
							<option value="" selected="selected">全部</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>错误来源</label>
						<select name="errorSource" id="errorSource"@change="selectErrorSource">
							<option value="" selected="selected">全部</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>错误类型</label>
						<select name="errorType" id="errorType"@change="selectErrorType">
							<option value="" selected="selected">全部</option>
						</select>
					</span>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
'use strict';
var config = require('../../../lib/js/config');
var base = require('../../../lib/js/base');
var insuranceErrorInfo;
var provincesErrorInfo;
var interfaceTypesErrorInfo;
var errorTypeErrorInfo;
var errorSourceErrorInfo;
module.exports= {
	data: function () {
		//绑定数据
		return {insuranceErrorInfo,provincesErrorInfo,interfaceTypesErrorInfo,errorTypeErrorInfo,errorSourceErrorInfo}
	},
	ready: function() {
		this.loadInsurer();
	},
	events: {
		'broadcast-errorStatistics-conditionBar-provincesFilter': function(provincesFilter) {
			this.loadProvince(provincesFilter);
		},
		'broadcast-errorStatistics-conditionBar-interfaceTypeFilter': function(interfaceTypeFilter) {
			this.loadInterfaceType(interfaceTypeFilter);
		},
		'broadcast-errorStatistics-conditionBar-errorTypeFilter': function(errorTypeFilter) {
			this.loadErrorType(errorTypeFilter);
		},
		'broadcast-errorStatistics-conditionBar-errorSourceFilter': function(errorSourceFilter) {
			this.loadErrorSource(errorSourceFilter);
		}
	},
	methods: {
		selectInsurance: function(event) {
			this.insuranceErrorInfo = event.target.value;
			this.dispatchInsuranceData(this.insuranceErrorInfo);
		},
		selectProvinces: function(event) {
			this.provincesErrorInfo = event.target.value;
			this.dispatchProvincesData(this.provincesErrorInfo);
		},
		selectInterfaceTypes: function(event) {
			this.interfaceTypesErrorInfo = event.target.value;
			this.dispatchInterfaceTypesData(this.interfaceTypesErrorInfo);
		},
		selectErrorType: function(event) {
			this.errorTypeErrorInfo = event.target.value;
			this.dispatchErrorTypeData(this.errorTypeErrorInfo);
		},
		selectErrorSource: function(event) {
			this.errorSourceErrorInfo = event.target.value;
			this.dispatchErrorSourceData(this.errorSourceErrorInfo);
		},
		dispatchInsuranceData: function(data) {//将查询条件通知到[errorStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-insurance', data);
		},
		dispatchProvincesData: function(data) {//将查询条件通知到[errorStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-provinces', data);
		},
		dispatchInterfaceTypesData: function(data) {//将查询条件通知到[errorStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-interfaceTypes', data);
		},
		dispatchErrorTypeData: function(data) {//将查询条件通知到[errorStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-errorType', data);
		},
		dispatchErrorSourceData: function(data) {//将查询条件通知到[errorStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-errorSource', data);
		},
		appendOption: function (elm, data) {
		            var option = '';
		            $.each(data, function(i,val){      
			            option += '<option value="' + val.name + '">' + val.value + '</option>';
			}); 
		            if (option !== '') {
		                	elm.append(option);
		            }
		},	
		loadInsurer: function(){
	         		var policyCompany = $('#policyCompany');
	            	this.appendOption(policyCompany, base.policyCompany);
	        	},        	
		loadProvince: function(provinces){
	         		var province = $('#province');
	            	this.appendOption(province, provinces);
	        	},
	        	loadInterfaceType: function(interfaceTypes){
	         		var interfaceType = $('#interfaceType');
	            	this.appendOption(interfaceType, interfaceTypes);
	        	},		
	        	loadErrorType: function(errorTypes){
	         		var errorType = $('#errorType');
	            	this.appendOption(errorType, errorTypes);
	        	},
	        	loadErrorSource: function(errorSources){
	         		var errorSource = $('#errorSource');
	            	this.appendOption(errorSource, errorSources);
	        	}
	}
}
</script>

<style type="text/css" scoped>
.queryBarError {
	margin: 5px;
	height: 36px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
	display: block;
	color:#428bca;
	font-size: 12px;
	min-width: 1000px;
	font-family: 微软雅黑;
}

.queryBarError ul {
	min-width: 1000px;
	width: 100%;
}

.queryBarError ul li {
	padding: 6px 30px 0px 30px;
	display:inline-block;
	min-width: 200px;
}

.queryBarError ul li label {
	min-width: 60px;
}

.queryBar ul li select {
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	background: #f2eeee;
    	min-width: 180px;
}

@media screen and (max-width: 1640px) {
.queryBarError {
	margin: 5px;
	height: 36px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
	display: block;
	color:#428bca;
	font-size: 12px;
	min-width: 1000px;
	width: 99%;
	font-family: 微软雅黑;
}

.queryBarError ul {
	min-width: 1000px;
	width: 99%;
}

.queryBarError ul li {
	padding: 6px 5px 0px 5px;
	display:inline-block;
	min-width: 180px;
}

.queryBarError ul li label {
	 min-width: 60px;
}

.queryBar ul li select {
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	background: #f2eeee;
    	min-width: 100px;
}
}
</style>