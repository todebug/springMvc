<template>
	<div class="dataUl">
		<div class="main-header-show">
			<div class="col-lg-4 cpicStyle">
			        <ul class="nav navbar navbar-nav">
			            <li style="margin-right:22px;"><img style="margin-left: -15px;margin-top: 10px;" src="../../lib/image/cpic.png" alt=""></li>
			            <li style="width:101px;">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[0]}}</span></h3>
			                <h4><span class="primary">{{info.reportData.sumItems.cpic_submitApplication[0]}}</span></h4>
			            </li>
			            <li style="width:88px">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span class="primary">{{info.reportData.sumItems.cpic_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li style="width:78px">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[7]}}</span></h3>
			                <h4><span class="primary">{{info.reportData.sumItems.cpic_submitApplication[7]}}</span></h4>
			            </li>
			            <li style="width:85px">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.cpic_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span class="primary">{{info.reportData.sumItems.cpic_submitApplication[6]|parseNum}}</span></h4>
			            </li>
			        </ul>
			    </div>
			    <div class="col-lg-4 piccStyle">
			        <ul class="nav navbar navbar-nav">
			            <li style="margin-right:26px;"><img style="margin-left: -10px;margin-top: 10px;" src="../../lib/image/picc.png" alt=""></li>
			            <li style="width:101px">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[0]}}</span></h3>
			                <h4><span class="warning">{{info.reportData.sumItems.picc_submitApplication[0]}}</span></h4>
			            </li>
			            <li style="width:88px">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span class="warning">{{info.reportData.sumItems.picc_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li style="width:78px">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[7]}}</span></h3>
			                <h4><span class="warning">{{info.reportData.sumItems.picc_submitApplication[7]}}</span></h4></li>
			            <li style="width:81px">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.picc_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span class="warning">{{info.reportData.sumItems.picc_submitApplication[6]|parseNum}}</span></h4>
			            </li>
			        </ul>
			    </div>
			    <div class="col-lg-4 gpicStyle">
			        <ul class="nav navbar navbar-nav">
			            <li style="margin-right:18px;"><img style="margin-left: -24px;margin-top: 10px;"src="../../lib/image/gpic.png" alt=""></li>
			            <li style="width:101px">
			                <p>交易数(笔)</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[0]}}</span></h3>
			                <h4><span class="success">{{info.reportData.sumItems.gpic_submitApplication[0]}}</span></h4>
			            </li>
			            <li style="width:88px">
			                <p>成功率</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[3]|parseNum}}%</span></h3>
			                <h4><span class="success">{{info.reportData.sumItems.gpic_submitApplication[3]|parseNum}}%</span></h4>
			            </li>
			            <li style="width:78px">
			                <p>90时长(秒) </p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[7]}}</span></h3>
			                <h4><span class="success">{{info.reportData.sumItems.gpic_submitApplication[7]}}</span></h4>
			            </li>
			            <li style="width:89px">
			                <p>平均时长(秒)</p>
			                <h3><span>{{info.reportData.sumItems.gpic_calculatePremium[6]|parseNum}}</span></h3>
			                <h4><span class="success">{{info.reportData.sumItems.gpic_submitApplication[6]|parseNum}}</span></h4>
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

.insTrade {
	margin-left: 21px;
	min-width: 500px;
}

.indTrade {
	margin-left: 60px;
	min-width: 500px;
}

.tradeType {
	margin-left: 60px;
	min-width: 500px;
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

.fontStyle {
	background: #e0ecf2;
    	border-radius: 100%;
    	font-style: normal;
    	line-height: 1pc;
    	width: 2pc;
    	margin-right: 4px;
}

.main-header-show {
    width: 100%;
    min-width: 1000px;
    height: 150px;
    font-family: 微软雅黑;
    overflow: hidden;
}

.main-header-show .col-lg-4 {
    padding: 0px 5px;
    background: white;
}

.main-header-show ul {
    padding: 20px 0px 15px;
    width: 100%;
    min-width:330px;
    height: 150px;
    border: 1px solid #d6d6d6;
    overflow: hidden;
}

.main-header-show ul > li {
    width: 20%;
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
</style>