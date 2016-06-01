<template>
	<div>
		<div class="pcDiv">
			<div class="cpicDiv">
				<div class="cpicSpan"></div>
				<ul class="divUl">
					<li>
						<label>总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>平</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="piccDiv">
				<div class="piccSpan"></div>
				<ul class="divUl">
					<li>
						<label>总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>平</label>
						<span>{{xdy.accessCount}}</span>
					</li>
				</ul>
			</div>
			<div class="gpicDiv">
				<div class="gpicSpan"></div>
				<ul class="divUl">
					<li>
						<label>总</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>大</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<br>
					
					<li>
						<label>小</label>
						<span>{{xdy.accessCount}}</span>
					</li>
					<li>
						<label>平</label>
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

.gpicSpan {
    	height: 80px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/gpic.png") center no-repeat #FDFFE8;
    	background-size: 100px;
}

.piccSpan {
    	height: 80px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/picc.png") center no-repeat #FDFFE8;
    	background-size: 100px;
}

.cpicSpan {
    	height: 80px;
	text-align: center;
	vertical-align: middle; 
	background: url("../../lib/image/cpic.png") center no-repeat #FDFFE8;
    	background-size: 100px;
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
	margin: 5px 5px 0px 5px;
}

.divUl li span {
    display: block;
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
}

.targetDiv a {
	text-decoration: none;
	cursor: pointer;
}
</style>