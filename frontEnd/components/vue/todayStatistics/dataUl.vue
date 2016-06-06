<template>
	<div class="dataUl">
		<div class="pcDiv">
			<div class="cpicDiv">
				<div class="cpicSpan"></div>
				<ul class="divUl">
					<li>
						<label style="color:#bb33c9">总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:green">大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:orange">平</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="piccDiv">
				<div class="piccSpan"></div>
				<ul class="divUl">
					<li>
						<label style="color:#bb33c9">总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:green">大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:orange">平</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="gpicDiv">
				<div class="gpicSpan"></div>
				<ul class="divUl">
					<li>
						<label style="color:#bb33c9">总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:green">大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label style="color:orange">平</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="targetDiv">
			<a @click="showTarget">指标</a>
			<div id="targetParam" style="display:none">
				<div class="insTrade">
				<input type="checkbox" id="insTradeCount" value="保险公司总交易次数" v-model="checkedNames">
				<label for="insTradeCount">保险公司总交易次数</label>
				<input type="checkbox" id="insTradeSuccessCount" value="保险公司成功数" v-model="checkedNames">
				<label for="insTradeSuccessCount">保险公司成功数</label>
				<input type="checkbox" id="insTradeFailCount" value="保险公司失败数" v-model="checkedNames">
				<label for="insTradeFailCount">保险公司失败数</label>
				<input type="checkbox" id="insTradeCountRate" value="保险公司失败数" v-model="checkedNames">
				<label for="insTradeCountRate">保险公司交易正确率</label>
				</div>
				<br>
				<div class="indTrade">
				<input type="checkbox" id="indTradeCount" value="承保地区总交易数" v-model="checkedNames">
				<label for="indTradeCount">承保地区总交易数</label>
				<input type="checkbox" id="indTradeSuccessCount" value="承保地区成功数" v-model="checkedNames">
				<label for="indTradeSuccessCount">承保地区成功数</label>
				<input type="checkbox" id="indTradeFailCount" value="承保地区失败数" v-model="checkedNames">
				<label for="indTradeFailCount">承保地区失败数</label>	
				<input type="checkbox" id="indTradeFailCountRate" value="承保地区交易正确率" v-model="checkedNames">	
				<label for="indTradeCountRate">承保地区交易正确率</label>
				</div>
				<br>
				<div class="tradeType">
				<input type="checkbox" id="tradeTypeCount" value="交易类型总交易数" v-model="checkedNames">
				<label for="tradeTypeCount">交易类型总交易数</label>
				<input type="checkbox" id="tradeTypeSuccessCount" value="交易类型成功数" v-model="checkedNames">
				<label for="tradeTypeSuccessCount">交易类型成功数</label>
				<input type="checkbox" id="tradeTypeFailCount" value="交易类型失败数" v-model="checkedNames">
				<label for="tradeTypeFailCount">交易类型失败数</label>
				<input type="checkbox" id="tradeTypeCountRate" value="交易类型正确率" v-model="checkedNames">
				<label for="tradeTypeCountRate">交易类型正确率</label>
				</div>
				<span>Checked names: {{ checkedNames | json }}</span>
			</div>
		</div>
	</div>
</template>

<script>
'use strict';
module.exports= {
	props:['xdy'],
	data: function () {
	    return { 
	    	checkedNames: []
	    }
	},
	methods: {
		showTarget: function() {
			$('#targetParam').toggle();
		}
	}
	// compiled: function () {
	// console.log(this.item);
	// }
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
	margin-top: 25px;
	margin-left: 5px;
    	height: 70px;
    	width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/gpic.png") left no-repeat white;
    	background-size: 100px;
}

.piccSpan {
	margin-top: 25px;
	margin-left: 5px;
    	height: 70px;
    	width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/picc.png") left no-repeat white;
    	background-size: 100px;
}

.cpicSpan {
	margin-top: 25px;
	margin-left: 5px;
    	height: 70px;
    	width: 100px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/cpic.png") left no-repeat white;
    	background-size: 100px;
}

.cpicDiv {
	background: white;
	display: inline-flex;
	width: 32%;
	min-width: 250px;
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
	min-width: 250px;
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
	min-width: 250px;
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
	width: 80px;
	list-style-type:none;
	margin-bottom: 5px;
}

.divUl li {
	display:inline-block;
	margin: 5px 5px 0px 5px;
}

.divUl li span {
    	display: inline-block;
    	font-size: 9pt;
}


.divUl li label{
	background: #e0ecf2;
	color: #3685ad;
	border-radius: 50%;
	font-style: normal;
	line-height: 1pc;
	width: 1pc;
	display: inline-block;
	text-align: center;
	margin-right: 4px;
	font-size: 9pt;
}

.targetDiv {
	display: block;
	height: auto;
	/* height: 80px; */
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

.targetDiv div {
	display: inline;
	font-size: 10px;
	min-width: 1000px;
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
</style>