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
					<input type="radio" @click="targetChecked" id="tradeCount" value="tradeCount" v-model="checkedNames">
					<label for="tradeCount">总交易次数</label>
					<input type="radio" @click="targetChecked" id="tradeSuccessCount" value="tradeSuccessCount" v-model="checkedNames">
					<label for="tradeSuccessCount">成功数</label>
					<input type="radio" @click="targetChecked" id="tradeFailCount" value="tradeFailCount" v-model="checkedNames">
					<label for="tradeFailCount">失败数</label>
					<input type="radio" @click="targetChecked" id="tradeCountRate" value="tradeCountRate" v-model="checkedNames">
					<label for="tradeCountRate">交易成功率</label>
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
	    	checkedNames: 'tradeCount'
	    }
	},
	methods: {
		showTarget: function() {
			$('#targetParam').toggle();
		},
		targetChecked: function(event) {
			this.getCheckedNames(this);
		},
		getCheckedNames: function(data) {
			data.$dispatch('data-Ul-checkedNames', this.checkedNames);
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