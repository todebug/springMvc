<template>
	<div class="queryBar">
		<ul>
			<li>
				<div>
					<span>
						<label>承保地区</label>
						<select @change="insuredSelect">
							<option value="全部"selected>全部</option>
							<option value="人保">人保</option>
							<option value="太保">太保</option>
							<option value="国寿财">国寿财</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>交易类型</label>
						<select @change="itradeSelect">
							<option value="全部"selected>全部</option>
							<option value="人保">人保</option>
							<option value="太保">太保</option>
							<option value="国寿财">国寿财</option>
						</select>
					</span>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
'use strict';
var insuranceSelect,itradeSelect;
var selectData={
            insuranceSelect: '全部',
            itradeSelect: '全部'
}
module.exports= {
	data: function () {
		//绑定数据
		return {selectData}
	},
	methods: {
		insuredSelect: function(event) {
			selectData.insuredSelect = event.target.value;
			this.dispatchData(this);
		},
		itradeSelect: function(event) {
			selectData.insuredSelect = event.target.value;
			this.dispatchData(this);
		},
		dispatchData: function(selectData) {//将查询条件通知到[todayStatistics.vue]父组件中
			data.$dispatch('dispatch-conditionBar-dataEchart-queryData', selectData);
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
	text-align: center;
	vertical-align: middle; 
	font-size: 15px;
	min-width: 1000px;
	font-family: Microsoft Yahei,\\5FAE\8F6F\96C5\9ED1,Tahoma,Arial,Helvetica,STHeiti;
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