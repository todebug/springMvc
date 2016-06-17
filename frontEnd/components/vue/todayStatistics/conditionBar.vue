<template>
	<div class="queryBar">
		<ul>
			<li>
				<div>
					<span>
						<label>承保地区</label>
						<select @change="selectProvinces">
							<option value="" selected>全部</option>
							<option v-for="item in provinces.provincesInfo" :item="item"   value="{{item[0]}}">{{item[1]}}</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>交易类型</label>
						<select @change="selectInterfaceTypes">
							<option value="" selected>全部</option>
							<option v-for="items in interfaceTypes.interfaceTypeInfo" :items="items"   value="{{items[0]}}">{{items[1]}}</option>
						</select>
					</span>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
'use strict';
var config = require('../../lib/js/config');
var provincesInfo;
var interfaceTypesInfo;
//省份信息
var provinces={
	provincesInfo: []
}
var interfaceTypes={
	interfaceTypeInfo: []
}
module.exports= {
	data: function () {
		//绑定数据
		return {provinces,provincesInfo,interfaceTypes,interfaceTypesInfo}
	},
	created: function() {
		this.getProvinces();
		this.getInterfaceType();
	},
	methods: {
		getProvinces: function() {
			var _this = this;
			var url = config.host+'statistic/getProvinces';
			var result = fetch(url,{method: 'get',mode: 'cors',});
			result.then(function(response) {
				return response.json();
			}).then(function(j) {
				_this.provinces = Object.assign(_this.provinces, j);
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
				_this.interfaceTypes = Object.assign(_this.interfaceTypes, j);
			}).catch(function(ex) {
				console.log('failed', ex)
			});
		},
		selectProvinces: function(event) {
			this.provincesInfo = event.target.value;
			this.dispatchProvincesData(this.provincesInfo);
		},
		selectInterfaceTypes: function(event) {
			this.interfaceTypesInfo = event.target.value;
			this.dispatchInterfaceTypesData(this.interfaceTypesInfo);
		},
		dispatchProvincesData: function(data) {//将查询条件通知到[todayStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-provinces', data);
		},
		dispatchInterfaceTypesData: function(data) {//将查询条件通知到[todayStatistics.vue]父组件中
			this.$dispatch('dispatch-conditionBar-dataEchart-interfaceTypes', data);
		}
	}
}
</script>

<style type="text/css">
.queryBar {
	margin: 5px;
	height: 36px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
	display: block;
	color:#428bca;
	font-size: 15px;
	min-width: 1000px;
	font-family: 微软雅黑;
}

.queryBar ul {
	min-width: 1000px;
}

.queryBar ul li {
	padding: 6px 50px 0px 50px;
	display:inline-block;
	min-width: 100px;
}

.queryBar ul li label {
	min-width: 40px;
}

.queryBar ul li select {
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	background: #f2eeee;
    	min-width: 50px;
}

</style>