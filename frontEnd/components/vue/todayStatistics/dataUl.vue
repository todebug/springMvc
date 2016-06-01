<template>
	<div>
		<div class="pcDiv">
			<div class="cpicDiv">
				<span class="spanDiv">太保</span>
				<ul class="divUl">
					<li>
						<label>总交易数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>总失败数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>最大时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>最小时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="piccDiv">
				<span class="spanDiv">人保</span>
				<ul class="divUl">
					<li>
						<label>总交易数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>总失败数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>最大时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>最小时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="gpicDiv">
				<span class="spanDiv">国寿财</span>
				<ul class="divUl">
					<li>
						<label>总交易数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>总失败数</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>最大时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>最小时效性</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="targetDiv">
			<a @click="showTarget">指标</a>
			<div id="targetParam" style="display:none">
				<input type="checkbox" id="tradeTime" value="交易次数" v-model="checkedNames">
				<label for="tradeTime">交易次数</label>
				<input type="checkbox" id="duration" value="时长" v-model="checkedNames">
				<label for="duration">时长</label>
				<br>
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

.pcDiv {
	display: table;
	width: 100%;
	min-width: 300px;
}

.spanDiv {
	font-weight: bold;
	font-size: 18px;
	text-align: center;
	vertical-align: middle; 
}

.cpicDiv {
	background: #FDFFE8;
	display: table-cell;
	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	border-right: 1px solid #d6d6d6;
}

.piccDiv {
	background: #FDFFE8;
	display: table-cell;
	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	border-right: 1px solid #d6d6d6;
}

.gpicDiv {
	background: #FDFFE8;
	display: table-cell;
	width: 30%;
	min-width: 100px;
	text-align: center;
	vertical-align: middle; 
	border-right: 1px solid #d6d6d6;
}

.divUl {
	list-style-type:none;
}

.divUl li {
	display:inline-block;
	margin: 10px 10px 0px 10px;
}

.divUl li span {
    display: block;
}


.divUl li label{
	font-size: 10px;
	font-weight: normal;
}

.targetDiv {
	display: block;
}

.targetDiv a {
	text-decoration: none;
	cursor: pointer;
}
</style>