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
								<label  style="color:#bb33c9">{{names.name1}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.cpic[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_calculatePremium[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_submitApplication[0]}}
							</td>
						</tr>
						<tr>
							<td>
								<label style="color:green">{{names.name2}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.cpic[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_calculatePremium[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_submitApplication[1]}}
							</td>
						</tr>
						<tr>
							<td>
								<label>{{names.name3}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.cpic[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_calculatePremium[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.cpic_submitApplication[2]}}
							</td>
						</tr>
						<tr>
							<td>
								<label style="color:orange">{{names.name4}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.cpic[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.cpic_calculatePremium[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.cpic_submitApplication[3]}}<span v-show="names.flag">%</span>
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
								<label style="color:#bb33c9">{{names.name1}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.picc[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_calculatePremium[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_submitApplication[0]}}
							</td>
						</tr>
						<tr>
							<td>
								<label style="color:green">{{names.name2}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.picc[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_calculatePremium[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_submitApplication[1]}}
							</td>
						</tr>
						<tr>
							<td>
								<label>{{names.name3}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.picc[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_calculatePremium[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.picc_submitApplication[2]}}
							</td>
						</tr>
						<tr>
							<td>
								<label style="color:orange">{{names.name4}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.picc[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.picc_calculatePremium[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.picc_submitApplication[3]}}<span v-show="names.flag">%</span>
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
								<label style="color:#bb33c9">{{names.name1}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.gpic[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_calculatePremium[0]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_submitApplication[0]}}
							</td>
						</tr>
						<tr>
							<td>
								<label style="color:green">{{names.name2}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.gpic[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_calculatePremium[1]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_submitApplication[1]}}
							</td>
						</tr>
						<tr>
							<td>
								<label >{{names.name3}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.gpic[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_calculatePremium[2]}}
							</td>
							<td>
								{{info.reportData.sumItems.gpic_submitApplication[2]}}
							</td>
						</tr>
						<tr>
							<td>
								<label  style="color:orange">{{names.name4}}</label>
							</td>
							<td>
								{{info.reportData.sumItems.gpic[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.gpic_calculatePremium[3]}}<span v-show="names.flag">%</span>
							</td>
							<td>
								{{info.reportData.sumItems.gpic_submitApplication[3]}}<span v-show="names.flag">%</span>
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
var names = {
	name1: '',
	name2: '',
	name3: '',
	name4: '',
	flag:{}
};
module.exports= {
	props:['info'],  
	compiled: function () {
		//console.log(this.info);
	},
	data: function () {
	    //绑定数据
	    return  {
			names,checkedNames: 'TOTAL_COUNT'
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
	},
	events: {
	    'broadcast-todayStatistics-dataUl-names': function(setNames) {//获取父组件传递的name数据.实际由[dataEchart.vue]子组件传递
	    	this.names = Object.assign({}, this.names, setNames);
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