<template>
	<div class="dataUl">
		<div class="main-header-show">
			<div class="cpicStyle">
			        <ul class="nav navbar navbar-nav">
			            <li class="cpicLi1">
			            	<div class="cpicImg"></div>
			            </li>
			            <li class="cpicLi2"align="right">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[0]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.cpic_submitApplication[0]}}</span></h4>
			            </li>
			            <li class="cpicLi3"align="right">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span>{{info.reportData.sumItems.cpic_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li class="cpicLi4"align="right">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[7]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.cpic_submitApplication[7]}}</span></h4>
			            </li>
			            <li class="cpicLi5"align="right">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.cpic_submitApplication[6]|parseNum}}</span></h4>
			            </li>
			        </ul>
			    </div>
			    <div class="piccStyle">
			        <ul class="nav navbar navbar-nav">
			            <li  class="piccLi1">
			            	<div class="piccImg"></div>
			            </li>
			            <li class="piccLi2"align="right">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[0]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.picc_submitApplication[0]}}</span></h4>
			            </li>
			            <li class="piccLi3"align="right">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span>{{info.reportData.sumItems.picc_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li class="piccLi4"align="right">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[7]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.picc_submitApplication[7]}}</span></h4></li>
			            <li class="piccLi5"align="right">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.picc_submitApplication[6]|parseNum}}</span></h4>
			            </li>
			        </ul>
			    </div>
			    <div class="gpicStyle">
			        <ul class="nav navbar navbar-nav">
			            <li class="gpicLi1">
			            	<div class="gpicImg"></div>
			            </li>
			            <li class="gpicLi2"align="right">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[0]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.gpic_submitApplication[0]}}</span></h4>
			            </li>
			            <li class="gpicLi3"align="right">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span>{{info.reportData.sumItems.gpic_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li class="gpicLi4"align="right">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[7]}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.gpic_submitApplication[7]}}</span></h4>
			            </li>
			            <li class="gpicLi5"align="right">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span>{{info.reportData.sumItems.gpic_submitApplication[6]|parseNum}}</span></h4>
			            </li>
			        </ul>
			    </div>
		</div>
		<div class="targetDiv">
			<a @click="showTarget">指标</a>
			<div class="targetParam" id="targetParam" style="display:none">
				<table>
					<tr>
						<td>
							<input type="radio" @click="targetCountChecked" id="TOTAL_COUNT" value="TOTAL_COUNT" v-model="tradeCount">
							<label for="TOTAL_COUNT">总交易次数</label>
						</td>
						<td>
							<input type="radio" @click="targetCountChecked" id="SUCCESS_COUNT" value="SUCCESS_COUNT" v-model="tradeCount">
							<label for="SUCCESS_COUNT">成功数</label>
						</td>
						<td>
							<input type="radio" @click="targetCountChecked" id="FAILURE_COUNT" value="FAILURE_COUNT" v-model="tradeCount">
							<label for="FAILURE_COUNT">失败数</label>
						</td>
						<td>
							<input type="radio" @click="targetCountChecked" id="SUCCESS_RATE" value="SUCCESS_RATE" v-model="tradeCount">
							<label for="SUCCESS_RATE">交易成功率</label>
						</td>
					</tr>
					<tr>	
						<td>
							<input type="radio" @click="targetTimeChecked" id="MAX_DURATION" value="MAX_DURATION" v-model="tradeTime">
							<label for="MAX_DURATION">最大时长</label>
						</td>
						<td>
							<input type="radio" @click="targetTimeChecked" id="MIN_DURATION" value="MIN_DURATION" v-model="tradeTime">
							<label for="MIN_DURATION">最小时长</label>
						</td>
						<td>
							<input type="radio" @click="targetTimeChecked" id="AVERAGE_DURATION" value="AVERAGE_DURATION" v-model="tradeTime">
							<label for="AVERAGE_DURATION">平均时长</label>
						</td>
						<td>
							<input type="radio" @click="targetTimeChecked" id="NINETY_DURATION" value="NINETY_DURATION" v-model="tradeTime">
							<label for="NINETY_DURATION">90%时长</label>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</template>

<script>
'use strict';
module.exports= {
	props:['info'],  
	compiled: function () {
		//console.log(this.info);
	},
	data: function () {
	    //绑定数据
	    return  {
			tradeCount: 'TOTAL_COUNT',tradeTime: 'MAX_DURATION'
		}
	},
	ready: function() {
		this.showDiv();
	},
	methods: {
		showDiv: function() {
			$('.targetDiv').hover(function() {
				$('#targetParam').toggle();
			});
		},
		showTarget: function() {
			$('#targetParam').toggle();
		},
		targetCountChecked: function(event) {
			this.getCheckedCount(this,event.target.value);
		},
		targetTimeChecked: function(event) {
			this.getCheckedTime(this,event.target.value);
		},
		getCheckedCount: function(data,indicator) {//将指标信息传递到父组件[todayStatistics.vue]中
			data.$dispatch('dispatch-dataUl-dataEchart-tradeCount', indicator);
		},
		getCheckedTime: function(data,indicator) {//将指标信息传递到父组件[todayStatistics.vue]中
			data.$dispatch('dispatch-dataUl-dataEchart-tradeTime', indicator);
		}
	}
}
</script>

<style type="text/css">
.dataUl {
	min-width: 1000px;
	width: 100%;
}

.targetDiv {
	display: block;
	height: 36px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	margin-top: 5px;
    	margin-left: 5px;
    	margin-right: 5px;
}

.targetDiv a {
	display: inline-block;
	margin-top: 4px;
	margin-left: 5px;
	width: 30px;
    	height: auto;
	cursor: pointer;
	background: #f9fcfb;
	font-weight: bold;
	font-size: 15px;
	border-radius: 5px;
	text-decoration: none;
	font-family: Microsoft Yahei,\\5FAE\8F6F\96C5\9ED1,Tahoma,Arial,Helvetica,STHeiti;
}

.targetParam {
	padding-left: 10px;
	padding-top: 5px;
	z-index: 20;
	display: inline-block;
	font-size: 14px;
	height: 65px;
	width: 400px;
	position: absolute;
	background: #e2edfb;
	left: 18px;
	top: 230px;
	border-radius: 5px;
	border: 1px solid #d6d6d6;
}

.main-header-show {
    	width: 100%;
    	min-width: 1000px;
    	height: 150px;
    	font-family: 微软雅黑;
    	overflow: hidden;
}

@media (min-width: 768px)
.navbar-nav {
    	float: left;
    	margin: 0;
}

@media (min-width: 768px)
.navbar {
    	border-radius: 4px;
}

.navbar {
    	position: relative;
	z-index: 1000;
    	min-height: 50px;
}

.nav {
    	list-style: none;
}

.cpicStyle {
    	width:32.6%;
    	min-width:326px;
    	display:inline-block;
    	padding: 0px 0px;
    	margin: 0px 2px;
    	background: white;
}

.piccStyle {
    	width:32.6%;
    	min-width:326px;
    	display:inline-block;
    	padding: 0px 0px;
    	margin: 0px 2px;
    	background: white;	
}

.gpicStyle {
    	width:32.6%;
    	min-width:326px;
    	display:inline-block;
    	padding: 0px 0px;
    	margin: 0px 2px;
    	background: white;	
}

.main-header-show ul {
    	padding: 20px 0px 15px;
    	width: 100%;
    	min-width:326px;
    	height: 150px;
    	border: 1px solid #d6d6d6;
    	overflow: hidden;
}

.main-header-show ul > li {
    	padding: 0px 5px 0px 6px;
    	border-right: 1px solid #d6d6d6;
}

.main-header-show ul > li:first-child, .main-header-show ul > li:last-child {
    	border: none;
}

.main-header-show ul > li p {
    	color: #999;
}

.main-header-show ul > li h3 {
    	margin-top: 10px;
    	font-size: 24px;
}

.main-header-show ul > li h3 span {
    	border-bottom: 1px dashed #d6d6d6;
    	display: inline-block;
    	padding-bottom: 5px;
}

.main-header-show ul > li h4 {
    	margin: 0px;
    	padding: 0px;
    	font-size: 24px;
}

.main-header-show ul > li span.primary {
    	color: #006f90
}

.main-header-show ul > li span.success {
    	color: #239423
}

.main-header-show ul > li span.warning {
    	color: #df8a11
}

.main-header-show ul > li span.danger {
    	color: #d9534f
}

.cpicStyle li{
	padding:5px 0px 5px 0px;
}

.cpicStyle ul li p {
	font-size:5pt;
}

.piccStyle ul li p {
	font-size:5pt;
}

.gpicStyle ul li p {
	font-size:5pt;
}

.cpicLi1 {
	width:19%;
}

.cpicLi2 {
	width:26%;
}

.cpicLi3 {
	width:20%;
}

.cpicLi4 {
	width:15%;
}

.cpicLi5 {
	width:18%;
}

.piccLi1 {
	width:21%;
}

.piccLi2 {
	width:24%;
}

.piccLi3 {
	width:19%;
}

.piccLi4 {
	width:15%;
}

.piccLi5 {
	width:18%;
}

.gpicLi1 {
	width:19%;
}

.gpicLi2 {
	width:26%;
}

.gpicLi3 {
	width:20%;
}

.gpicLi4 {
	width:15%;
}

.gpicLi5 {
	width:18%;
}

.cpicImg {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 120px;
	background: url("../../lib/image/cpic.png") left no-repeat white;
	background-size: 100px;
}

.piccImg {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 120px;
	background: url("../../lib/image/picc.png") left no-repeat white;
	background-size: 100px;
}

.gpicImg {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 120px;
	background: url("../../lib/image/gpic.png") left no-repeat white;
	background-size: 100px;
}

@media screen and (max-width: 1640px) {
    	.main-header-show {
		    width: 100%;
		    min-width: 1000px;
		    height: 150px;
		    font-family: 微软雅黑;
		    overflow: hidden;
	}

	.cpicStyle {
		width:32.6%;
		min-width:326px;
		display:inline-block;
		padding: 0px 0px;
		margin: 0px 2px;
		background: white;
	}

	.piccStyle {
		width:32.6%;
		min-width:326px;
		display:inline-block;
		padding: 0px 0px;
		margin: 0px 2px;
		background: white;
	}

	.gpicStyle {
		width:32.6%;
		min-width:326px;
		display:inline-block;
		padding: 0px 0px;
		margin: 0px 2px;
		background: white;
	}

	.main-header-show ul {
		padding: 20px 0px 15px;
		width: 100%;
		min-width:326px;
		height: 150px;
		border: 1px solid #d6d6d6;
		overflow: hidden;
	}

	.main-header-show ul > li {
		padding: 18px 0px 0px 3px;
		border-right: 1px solid #d6d6d6;
	}

	.main-header-show ul > li h3 {
		margin-top: 10px;
		font-size: 12px;
	}

	.main-header-show ul > li h3 span {
		border-bottom: 1px dashed #d6d6d6;
		display: inline-block;
		padding-bottom: 5px;
	}

	.main-header-show ul > li h4 {
		margin: 0px;
		padding: 0px;
		font-size: 12px;
	}

	.cpicStyle li{
		padding:5px 0px 5px 0px;
	}

	.cpicStyle ul li p {
		font-size:5pt;
	}

	.piccStyle ul li p {
		font-size:5pt;
	}

	.gpicStyle ul li p {
		font-size:5pt;
	}

	.cpicLi1{
		width:19%;
	}

	.cpicLi2 {
		width:20%;
	}

	.cpicLi3 {
		width:15%;
	}

	.cpicLi4 {
		width:21%;
	}

	.cpicLi5 {
		width:23%;
	}

	.piccLi1 {
		width:19%;
	}

	.piccLi2 {
		width:20%;
	}

	.piccLi3 {
		width:15%;
	}

	.piccLi4 {
		width:21%;
	}

	.piccLi5 {
		width:23%;
	}

	.gpicLi1 {
		width:19%;
	}

	.gpicLi2 {
		width:20%;
	}

	.gpicLi3 {
		width:15%;
	}

	.gpicLi4 {
		width:21%;
	}

	.gpicLi5 {
		width:23%;
	}

	.cpicImg {
		margin-left: -15px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 120px;
		background: url("../../lib/image/cpic.png") left no-repeat white;
		background-size: 90px;
	}

	.piccImg {
		margin-left: -7px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 120px;
		background: url("../../lib/image/picc.png") left no-repeat white;
		background-size: 73px;
	}

	.gpicImg {
		margin-left: -15px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 120px;
		background: url("../../lib/image/gpic.png") left no-repeat white;
		background-size: 90px;
	}
}
</style>