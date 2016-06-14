<template>
	<div class="dataUl">
		<div class="pcDiv">
			<div class="cpicDiv">
				<div class="cpicSpan"></div>
				<table class="divUl">
					<thead>
						<tr>
							<td></td>
							<td>&nbsp;&nbsp{{info.reportData.sumTitle.cpic}}&nbsp;&nbsp</td>
							<td>{{info.reportData.sumTitle.cpic_calculatePremium}}</td>
							<td>{{info.reportData.sumTitle.cpic_submitApplication}}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label  v-show="info.show" style="color:#bb33c9">总数</label>
								<label  v-else style="color:#bb33c9">最大时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic[0]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic[4]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_calculatePremium[0]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_calculatePremium[4]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_submitApplication[0]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_submitApplication[4]}}</div>
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:green">成功数</label>
								<label  v-else style="color:green">最小时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic[1]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic[5]}}</div>
							</td>
							<td>	
								<div v-show="info.show">{{info.reportData.sumItems.cpic_calculatePremium[1]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_calculatePremium[5]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_submitApplication[1]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_submitApplication[5]}}</div>	
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" >失败数</label>
								<label  v-else >平均时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic[2]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic[6]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_calculatePremium[2]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_calculatePremium[6]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_submitApplication[2]}}</div>
								<div v-else>{{info.reportData.sumItems.cpic_submitApplication[6]}}</div>
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:orange">正确率</label>
								<label  v-else style="color:orange">90%时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.cpic[7]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_calculatePremium[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.cpic_calculatePremium[7]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.cpic_submitApplication[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.cpic_submitApplication[7]}}</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="piccDiv">
				<div class="piccSpan"></div>
				<table class="divUl">
					<thead>
						<tr>
							<td></td>
							<td>&nbsp;&nbsp{{info.reportData.sumTitle.picc}}&nbsp;&nbsp</td>
							<td>{{info.reportData.sumTitle.picc_calculatePremium}}</td>
							<td>{{info.reportData.sumTitle.picc_submitApplication}}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label  v-show="info.show" style="color:#bb33c9">总数</label>
								<label  v-else style="color:#bb33c9">最大时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc[0]}}</div>
								<div v-else>{{info.reportData.sumItems.picc[4]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_calculatePremium[0]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_calculatePremium[4]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_submitApplication[0]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_submitApplication[4]}}</div>	
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:green">成功数</label>
								<label  v-else style="color:green">最小时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc[1]}}</div>
								<div v-else>{{info.reportData.sumItems.picc[5]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_calculatePremium[1]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_calculatePremium[5]}}</div>
								
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_submitApplication[1]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_submitApplication[5]}}</div>
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" >失败数</label>
								<label  v-else >平均时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc[2]}}</div>
								<div v-else>{{info.reportData.sumItems.picc[6]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_calculatePremium[2]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_calculatePremium[6]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_submitApplication[2]}}</div>
								<div v-else>{{info.reportData.sumItems.picc_submitApplication[6]}}</div>
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:orange">正确率</label>
								<label  v-else style="color:orange">90%时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.picc[7]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_calculatePremium[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.picc_calculatePremium[7]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.picc_submitApplication[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.picc_submitApplication[7]}}</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="gpicDiv">
				<div class="gpicSpan"></div>
				<table class="divUl">
					<thead>
						<tr>
							<td></td>
							<td>&nbsp;&nbsp{{info.reportData.sumTitle.gpic}}&nbsp;&nbsp</td>
							<td>{{info.reportData.sumTitle.gpic_calculatePremium}}</td>
							<td>{{info.reportData.sumTitle.gpic_submitApplication}}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label  v-show="info.show" style="color:#bb33c9">总数</label>
								<label  v-else style="color:#bb33c9">最大时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic[0]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic[4]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_calculatePremium[0]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_calculatePremium[4]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_submitApplication[0]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_submitApplication[4]}}</div>	
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:green">成功数</label>
								<label  v-else style="color:green">最小时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic[1]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic[5]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_calculatePremium[1]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_calculatePremium[5]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_submitApplication[1]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_submitApplication[5]}}</div>	
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" >失败数</label>
								<label  v-else >平均时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic[2]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic[6]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_calculatePremium[2]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_calculatePremium[6]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_submitApplication[2]}}</div>
								<div v-else>{{info.reportData.sumItems.gpic_submitApplication[6]}}</div>
							</td>
						</tr>
						<tr>
							<td>
								<label  v-show="info.show" style="color:orange">正确率</label>
								<label  v-else style="color:orange">90%时长</label>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.gpic[7]}}</div>	
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_calculatePremium[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.gpic_calculatePremium[7]}}</div>
							</td>
							<td>
								<div v-show="info.show">{{info.reportData.sumItems.gpic_submitApplication[3]}}%</div>
								<div v-else>{{info.reportData.sumItems.gpic_submitApplication[7]}}</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="targetDiv">
			<a @click="showTarget">指标</a>
			<div class="targetParam" id="targetParam" style="display:none">
					<input type="radio" @click="targetChecked" id="TOTAL_COUNT" value="TOTAL_COUNT" v-model="checkedNames">
					<label for="TOTAL_COUNT">总交易次数</label>
					<input type="radio" @click="targetChecked" id="SUCCESS_COUNT" value="SUCCESS_COUNT" v-model="checkedNames">
					<label for="SUCCESS_COUNT">成功数</label>
					<input type="radio" @click="targetChecked" id="FAILURE_COUNT" value="FAILURE_COUNT" v-model="checkedNames">
					<label for="FAILURE_COUNT">失败数</label>
					<input type="radio" @click="targetChecked" id="SUCCESS_RATE" value="SUCCESS_RATE" v-model="checkedNames">
					<label for="SUCCESS_RATE">交易成功率</label>
					<br>
					<input type="radio" @click="targetChecked" id="MAX_DURATION" value="MAX_DURATION" v-model="checkedNames">
					<label for="MAX_DURATION">最大时长</label>
					<input type="radio" @click="targetChecked" id="MIN_DURATION" value="MIN_DURATION" v-model="checkedNames">
					<label for="MIN_DURATION">最小时长</label>
					<input type="radio" @click="targetChecked" id="AVERAGE_DURATION" value="AVERAGE_DURATION" v-model="checkedNames">
					<label for="AVERAGE_DURATION">平均时长</label>
					<input type="radio" @click="targetChecked" id="NINETY_DURATION" value="NINETY_DURATION" v-model="checkedNames">
					<label for="NINETY_DURATION">90%时长</label>
				<!-- <span>Checked names: {{ checkedNames | json }}</span> -->
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
			checkedNames: 'TOTAL_COUNT'
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
		targetChecked: function(event) {
			this.getCheckedNames(this,event.target.value);
		},
		getCheckedNames: function(data,indicator) {//将指标信息传递到父组件[todayStatistics.vue]中
			data.$dispatch('dispatch-dataUl-dataEchart-indicator', indicator);
		}
	}
}
</script>

<style type="text/css">
.dataUl {
	min-width: 1000px;
}

.pcDiv {
	height: 123px;
	display: table;
	width: 100%;
	min-width: 1000px;
	padding: 5px 5px 5px 5px;
}

.gpicSpan {
	margin-top: 12px;
	margin-left: 5px;
	margin-right: -20px;
    	height: 70px;
    	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/gpic.png") left no-repeat white;
    	background-size: 100px;
}

.piccSpan {
	margin-top: 12px;
	margin-left: 5px;
	margin-right: -20px;
    	height: 70px;
    	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/picc.png") left no-repeat white;
    	background-size: 100px;
}

.cpicSpan {
	margin-top: 12px;
	margin-left: 5px;
	margin-right: -20px;
    	height: 70px;
    	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/cpic.png") left no-repeat white;
    	background-size: 100px;
}

.cpicDiv {
	background: white;
	display: inline-flex;
	width: 32%;
	min-width: 300px;
	text-align: center;
	vertical-align: middle; 
	border: 1px solid #d6d6d6;
	border-radius: 5px;
	box-shadow: 1px 1px 0.5px #888888;
	margin-right: 8px;
}

.piccDiv {
	background: white;
	display: inline-flex;
	width: 32%;
	min-width: 300px;
	text-align: center;
	vertical-align: middle; 
	border-right: 1px solid #d6d6d6;
	border-top: 1px solid #d6d6d6;
	border-bottom: 1px solid #d6d6d6;
	border-radius: 5px;
	box-shadow: 1px 1px 0.5px #888888;
	margin-right: 5px;
	margin-left: 5px;
}

.gpicDiv {
	background: white;
	display: inline-flex;
	width: 32%;
	min-width: 300px;
	text-align: center;
	vertical-align: middle; 
	border-right: 1px solid #d6d6d6;
	border-top: 1px solid #d6d6d6;
	border-bottom: 1px solid #d6d6d6;
	border-radius: 5px;
	box-shadow: 1px 1px 0.5px #888888;
	margin-left: 5px;
}

.divUl {
	width: 70%;
	min-width: 200px;
	list-style-type:none;
	margin-bottom: 5px;
	text-align: center;
	vertical-align: middle;
}

.divUl thead {
	font-weight: bold;
	width: 100%;
	min-width: 190px;
	text-align: center;
	vertical-align: middle;
}

.divUl thead tr {
	font-weight: bold;
	text-align: center;
	width: 100%;
	min-width: 190px;
	vertical-align: middle;
}

.divUl thead tr td {
	font-weight: bold;
	font-size: 12px;
	text-align: center;
	vertical-align: middle;
}

.divUl tbody {
	width: 100%;
	min-width: 190px;
	text-align: center;
	vertical-align: middle;
}

.divUl tbody tr {
	width: 100%;
	min-width: 190px;
	text-align: center;
	vertical-align: middle;
}

.divUl tbody tr td {
	font-size: 12px;
	text-align: center;
	vertical-align: middle;
}

.targetDiv {
	display: block;
	height: auto;
	/* height: 30px; */
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
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
	font-size: 14px;
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
	font-size: 10px;
	height: 60px;
	width: 300px;
	position: absolute;
	background: #e2edfb;
	left: 15px;
	top: 190px;
	border-radius: 5px;
	border: 1px solid #d6d6d6;
}

</style>