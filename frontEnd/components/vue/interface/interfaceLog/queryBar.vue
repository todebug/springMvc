<template>
	<div class="more-options">
		<ul>
			<li>
				<label for="interfaceType">请求类型</label>
	            		<p>
				            <select name="interfaceType" id="interfaceType" @change="selectInterfaceType">
				            	<option value="" selected="selected">全部</option>
				            </select>
			            </p>
			</li>
			<li>
				<label for="policyCompany">保险公司</label>
	            		<p>
				            <select name="policyCompany" id="policyCompany" @change="selectPolicyCompany">
				            	<option value="" selected="selected">全部</option>
				            </select>
			            </p>
			</li>
			<li>
				<label for="province">承保省份</label>
	            		<p>
				            <select name="province" id="province" @change="selectProvince">
				            	<option value="" selected="selected">全部</option>
				            </select>
			            </p>
			</li>
			<li>
				<label for="status">处理状态</label>
				<p>
		            		<select name="status" id="status" @change="selectStatus">
					            <option value="" selected="selected">全部</option>
					            <option value="成功">成功</option>
					            <option value="失败">失败</option>
					</select>
				</p>
			</li>
			<li>
				<label for="plateNo">车牌号</label>
				<p>
	            			<input type="text" id="plateNo" name="plateNo" placeholder="请输入车牌号"@change="inputPlateNo">
				</p>
			</li>
			<li>
				<label for="vin">vin码</label>
				<p>
	            			<input type="text" id="vin" name="vin" placeholder="请输入vin码"@change="inputVin">
				</p>
			</li>
			<li>
				<label for="insured">被保险人</label>
				<p>
	            			<input type="text" id="insured" name="insured" placeholder="请输入被保险人"@change="inputInsured">
				</p>
			</li>
			<li>
				<label for="dealer">经销商</label>
				<p>
	            			<input type="text" id="dealer" name="dealer" placeholder="请输入经销商代码"@change="inputDealer">
				</p>
			</li>
			<li>
				<label for="orderNo">订单号</label>
				<p>
					<input type="text" id="orderNo" name="orderNo" placeholder="请输入订单号" @change="inputOrderNo">
				</p>
			</li>
			<li>
				<label for="requestBeginTime">请求开始时间</label>
				<p>
					<input type="text" id="requestBeginTime" name="requestBeginTime" placeholder="请选择请求开始时间"readonly>
				</p>
			</li>
			<li>
				<label for="requestEndTime">请求结束时间</label>
				<p>
					<input type="text" id="requestEndTime" name="requestEndTime" placeholder="请选择请求结束时间"readonly>
				</p>
			</li>
<!-- 			<li>
				<label for="responseBeginTime">响应开始时间</label>
				<input type="text" id="responseBeginTime" name="responseBeginTime" placeholder="请选择响应开始时间"readonly>
			</li>
			<li>
				<label for="responseEndTime">响应结束时间</label>
				<input type="text" id="responseEndTime" name="responseEndTime" placeholder="请选择响应结束时间"readonly>
			</li> -->
			<li>
				<label for="errorMessage">错误信息</label>
				<p>
	            			<input type="text" id="errorMessage" name="errorMessage" placeholder="支持模糊查询,关键字间用*号隔开"@change="inputErrorMessage">
				</p>
			</li>
			<li>
				<span id="contrastTip" class="tipHide">开始时间不得小于结束时间!</span>
			</li>
		</ul>
	</div>
	<div class="search-btn">
	        <button class="btn btn-info" @click="search">查 询</button>
	</div>
</template>


<script>
'use strict';
require('imports?$=jquery!../../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../../lib/js/bootstrap-datetimepicker.min.js');
require('imports?$=jquery!../../../lib/js/locales/bootstrap-datetimepicker.zh-CN.js');
var base = require('../../../lib/js/base');
var moment=require('moment');
var option={
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
	minView: 'month',
	autoclose: true,
        	todayBtn: true,
        	zIndex: 2147483650
};
var options={
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
	minView: 'month',
	autoclose: true,
        	todayBtn: true,
        	clearBtn: true,
        	zIndex: 2147483650
};
var today=moment().format('YYYY-MM-DD');
var tomorrow= moment().add(1, 'days').format('YYYY-MM-DD');
var queryCondition={
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
	    	return  {queryCondition}
	},
	created: function() {
		this.initQueryData();
	},
	ready: function() {
		this.loadInsurer();
		this.datePicker();
		this.setRequsetTime();
	},
	methods: {
		initQueryData: function() {
			var today=moment().format('YYYY-MM-DD');
			var tomorrow= moment().add(1, 'days').format('YYYY-MM-DD');
			this.queryCondition.interfaceType = '';
			this.queryCondition.plateNo = '';
			this.queryCondition.vin = '';
			this.queryCondition.insured = '';
			this.queryCondition.insurer = '';
			this.queryCondition.province = '';
			this.queryCondition.orderNo = '';
			this.queryCondition.status = '';
			this.queryCondition.dealer = '';
			this.queryCondition.requestBeginTime=today;
			this.queryCondition.requestEndTime=tomorrow;
			this.queryCondition.responseBeginTime='';
			this.queryCondition.responseEndTime='';
			this.queryCondition.errorMessage='';
			this.queryCondition.page=1;
			this.queryCondition.size=20;
		},
		search: function() {
			this.dispatchData(this.queryCondition);
		},
		setRequsetTime: function() {
			var today=moment().format('YYYY-MM-DD');
			var tomorrow= moment().add(1, 'days').format('YYYY-MM-DD');
			$('#requestBeginTime').val(today);
		            $('#requestEndTime').val(tomorrow);
		},
		inputErrorMessage: function() {
			this.queryCondition.errorMessage = $('#errorMessage').val();
			//this.dispatchData(this.queryCondition);
		},
		inputPlateNo: function() {
			this.queryCondition.plateNo = $('#plateNo').val();
			//this.dispatchData(this.queryCondition);
		},
		inputVin: function() {
			this.queryCondition.vin = $('#vin').val();
			//this.dispatchData(this.queryCondition);
		},
		inputInsured: function() {
			this.queryCondition.insured = $('#insured').val();
			//this.dispatchData(this.queryCondition);
		},
		inputDealer: function() {
			this.queryCondition.dealer = $('#dealer').val();
			//this.dispatchData(this.queryCondition);
		},
		inputOrderNo: function() {
			this.queryCondition.orderNo = $('#orderNo').val();
			//this.dispatchData(this.queryCondition);
		},
		selectStatus: function(event) {
			this.queryCondition.status = event.target.value;
			//this.dispatchData(this.queryCondition);
		},
		selectInterfaceType: function(event) {
			this.queryCondition.interfaceType = event.target.value;
			//this.dispatchData(this.queryCondition);
		},
		selectProvince: function(event) {
			this.queryCondition.province = event.target.value;
			//this.dispatchData(this.queryCondition);
		},
		selectPolicyCompany: function(event) {
			this.queryCondition.insurer = event.target.value;
			//this.dispatchData(this.queryCondition);
		},
		dispatchData: function(queryCondition) {
			this.$dispatch('dispatch-queryBar-interfaceLog-queryData', queryCondition);
		},
		getDateValue: function() {
			this.queryCondition.requestBeginTime = $('#requestBeginTime').val();
			this.queryCondition.requestEndTime = $('#requestEndTime').val();
			this.queryCondition.responseBeginTime = $('#responseBeginTime').val();
			this.queryCondition.responseEndTime = $('#responseEndTime').val();
		},
		datePicker: function() {
			var _this = this;
			$('#requestBeginTime').datetimepicker(option).on('show', function(ev){
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#requestEndTime').datetimepicker(option).on('show', function(ev){
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#responseBeginTime').datetimepicker(options).on('show', function(ev){
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#responseEndTime').datetimepicker(options).on('show', function(ev){
	                    		$('#contrastTip').removeClass('tipShow').addClass('tipHide');
			});
			$('#requestBeginTime').datetimepicker(option).on('changeDate', function(ev){
				if($('#requestBeginTime').val()!==''&&$('#requestEndTime').val()!==''){
					if($('#requestBeginTime').val()>$('#requestEndTime').val()){
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.getDateValue();
						//_this.dispatchData(_this.queryCondition);	
					}
				}
			});	
			$('#requestEndTime').datetimepicker(option).on('changeDate', function(ev){
				if($('#requestBeginTime').val()!==''&&$('#requestEndTime').val()!==''){
					if($('#requestBeginTime').val()>$('#requestEndTime').val()){
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.getDateValue();
						//_this.dispatchData(_this.queryCondition);	
					}
				}
			});
			$('#responseBeginTime').datetimepicker(options).on('changeDate', function(ev){
				if($('#responseBeginTime').val()!==''&&$('#responseEndTime').val()!==''){
					if($('#responseBeginTime').val()>$('#responseEndTime').val()){
						$('#responseBeginTime').val('');
						$('#responseEndTime').val('');
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.getDateValue();
						//_this.dispatchData(_this.queryCondition);	
					}
				}
			});	
			$('#responseEndTime').datetimepicker(options).on('changeDate', function(ev){
				if($('#responseBeginTime').val()!==''&&$('#responseEndTime').val()!==''){
					if($('#responseBeginTime').val()>$('#responseEndTime').val()){
						$('#responseBeginTime').val('');
						$('#responseEndTime').val('');
						//提示信息
						$('#contrastTip').removeClass('tipHide').addClass('tipShow');
					}else{
						_this.getDateValue();
						//_this.dispatchData(_this.queryCondition);	
					}
				}
			});
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
	        	}
	},
	events: {
		'broadcast-interfaceLog-queryBar-provincesFilter': function(provincesFilter) {
			this.loadProvince(provincesFilter);
		},
		'broadcast-interfaceLog-queryBar-interfaceTypeFilter': function(interfaceTypeFilter) {
			this.loadInterfaceType(interfaceTypeFilter);
		},
	}
}
</script>

<style type="text/css" scoped>

.more-options {
    	padding: 20px 10px 0px 30px;
    	overflow: hidden;
    	width: 100%;
    	min-width:1000px;
}

.more-options ul{
    	width: 100%;
    	min-width:1000px;
}

.more-options p , .more-options label{
    	margin-bottom:0;
}

.more-options li p {
    	height: 25px;
    	line-height: 36px;
    	float: left;
    	width: 70%;
    	min-width: 200px;
}

.more-options  li select {
    	border: 1px solid #e7e7e7;
    	width: 80%;
    	min-width: 200px;
    	height: 36px;
    	color: #666;
    	background: #fbfbfb;
}

.more-options li label {
	width: 10%;
    	min-width: 60px;
    	text-align: right;
    	display: block;
    	float: left;
    	font-size: 12px;
    	font-family: 微软雅黑;
    	height: 36px;
    	padding-right: 10px;
    	line-height: 36px;
    	color: #666;
}

.more-options li{
    	width: 33.333333333%;
    	min-width: 270px;
    	float: left;
    	overflow: hidden;
    	margin-bottom: 15px;
    	font-size: 12px;
    	font-family: 微软雅黑;
    	color: #666;
}

.more-options li input {
    	background: #fbfbfb;
    	line-height: 36px;
    	height: 36px;
    	border: 1px solid #e7e7e7;
    	font-size: 12px;
    	font-family: 微软雅黑;
    	color: #666;
    	padding: 0 8px;
    	width:80%;
    	min-width: 200px;
   	border-radius: 4px;
}

.btn-info {
	margin:0 auto;
	width:100px;
	display:block;
}

.search-btn {
	margin-bottom: 10px;
}
</style>