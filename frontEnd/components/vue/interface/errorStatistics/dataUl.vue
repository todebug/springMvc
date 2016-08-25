<template>
	<div class="dataUl" >
		<div class="main-header-show" style="height:100px;">
			<div class="cpicStyle">
			        <ul class="nav navbar navbar-nav" style="height:100px;">
			            <li class="cpicLiError1">
			            	<div class="cpicImgError"></div>
			            </li>
			            <li class="cpicLiError2"align="right">
			                <p>{{info.reportData.cpic.sumTitle.INSIDE_SYSTEM_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.cpic.sumItems.INSIDE_SYSTEM_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="cpicLiError3"align="right">
			                <p>{{info.reportData.cpic.sumTitle.INSURER_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.cpic.sumItems.INSURER_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="cpicLiError4"align="right">
			                <p>{{info.reportData.cpic.sumTitle.UNKNOWN_ERROR | line}}</p>
			                <h3>
				            <span style="border-bottom:0px;color:red;" v-show="info.reportData.cpic.sumItems.UNKNOWN_ERROR[0]!==null">{{info.reportData.cpic.sumItems.UNKNOWN_ERROR[0] | line}}</span>
				            <span style="border-bottom:0px;" v-else>--</span>
			                </h3>
			            </li>
			        </ul>
			    </div>
			    <div class="piccStyle">
			        <ul class="nav navbar navbar-nav" style="height:100px;">
			            <li  class="piccLiError1">
			            	<div class="piccImgError"></div>
			            </li>
			            <li class="piccLiError2"align="right">
			                <p>{{info.reportData.picc.sumTitle.INSIDE_SYSTEM_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.picc.sumItems.INSIDE_SYSTEM_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="piccLiError3"align="right">
			                <p>{{info.reportData.picc.sumTitle.INSURER_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.picc.sumItems.INSURER_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="piccLiError4"align="right">
			                <p>{{info.reportData.picc.sumTitle.UNKNOWN_ERROR | line}}</p>
			                <h3>
				            <span style="border-bottom:0px;color:red;" v-show="info.reportData.picc.sumItems.UNKNOWN_ERROR[0]!==null">{{info.reportData.picc.sumItems.UNKNOWN_ERROR[0] | line}}</span>
				            <span style="border-bottom:0px;"  v-else>--</span>
			                </h3>
			        </ul>
			    </div>
			    <div class="gpicStyle">
			        <ul class="nav navbar navbar-nav" style="height:100px;">
			            <li class="gpicLiError1">
			            	<div class="gpicImgError"></div>
			            </li>
			            <li class="gpicLiError2"align="right">
			                <p>{{info.reportData.gpic.sumTitle.INSIDE_SYSTEM_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.gpic.sumItems.INSIDE_SYSTEM_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="gpicLiError3"align="right">
			                <p>{{info.reportData.gpic.sumTitle.INSURER_ERROR | line}}</p>
			                <h3><span style="border-bottom:0px;">{{info.reportData.gpic.sumItems.INSURER_ERROR[0] | line}}</span></h3>
			            </li>
			            <li class="gpicLiError4"align="right">
			                <p>{{info.reportData.gpic.sumTitle.UNKNOWN_ERROR | line}}</p>
			                <h3>
				            <span style="border-bottom:0px;color:red;" v-show="info.reportData.gpic.sumItems.UNKNOWN_ERROR[0]!==null">{{info.reportData.gpic.sumItems.UNKNOWN_ERROR[0] | line}}</span>
				            <span style="border-bottom:0px;"  v-else>--</span>
			                </h3>
			            </li>
			        </ul>
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
	}
}
</script>

<style type="text/css" scoped>

.dataUl {
	min-width: 1000px;
	margin-left:5px;
	margin-right:5px;
}

.main-header-show {
    	width: 100%;
    	min-width: 1000px;
    	height: 130px;
    	font-family: 微软雅黑;
    	overflow: hidden;
}

.cpicStyle {
    	width:33.33333333%;
    	min-width:330px;
    	float: left;
    	padding: 0 5px 0 0;
    	margin: 0px;
}

.piccStyle {
    	width:33.33333333%;
    	min-width:330px;
    	float: left;
    	padding: 0 5px 0 0;
    	margin: 0px;
}

.gpicStyle {
    	width:33.33333333%;
    	min-width:326px;
    	float: left;
    	padding: 0px 0px;
    	margin: 0px;
}

.main-header-show ul {
    	background: white;	
    	padding: 20px 0px 15px;
    	width: 100%;
    	min-width:326px;
    	height: 130px;
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
.cpicLiError1 {
	width:20%;
}

.cpicLiError2 {
	width:26%;
}

.cpicLiError3 {
	width:26%;
}

.cpicLiError4 {
	width:26%;
}


.piccLiError1 {
	width:20%;
}

.piccLiError2 {
	width:26%;
}

.piccLiError3 {
	width:26%;
}

.piccLiError4 {
	width:26%;
}

.gpicLiError1 {
	width:20%;
}

.gpicLiError2 {
	width:26%;
}

.gpicLiError3 {
	width:26%;
}

.gpicLiError4 {
	width:26%;
}

.cpicImgError {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 75px;
	background: url("../../../lib/image/cpic.png") left no-repeat white;
	background-size: 100px;
}

.piccImgError {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 75px;
	background: url("../../../lib/image/picc.png") left no-repeat white;
	background-size: 100px;
}

.gpicImgError {
	margin-left: -6px;
	margin-top: -10px;
	text-align: center;
	vertical-align: middle;
	height: 75px;
	background: url("../../../lib/image/gpic.png") left no-repeat white;
	background-size: 100px;
}

@media screen and (max-width: 1640px) {
	.main-header-show {
		width: 100%;
	    	min-width: 1000px;
	    	height: 130px;
	    	font-family: 微软雅黑;
	    	font-weight: bold;
	    	overflow: hidden;
	}

	.cpicStyle {
		width:33.33333333%;
		min-width:326px;
		padding: 0 5px 0 0;
    		margin: 0px;
	}

	.piccStyle {
		width:33.33333333%;
		min-width:326px;
		padding: 0 5px 0 0;
    		margin: 0px;
	}

	.gpicStyle {
		width:33.33333333%;
		min-width:326px;
		padding: 0px 0px;
    		margin: 0px;
	}

	.main-header-show ul {
		background: white;
		padding: 20px 0px 15px;
		width: 100%;
		min-width:326px;
		height: 130px;
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
		font-weight: bold;
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
		font-weight: bold;
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
	.cpicLiError1{
		width:20%;
	}

	.cpicLiError2 {
		width:26%;
	}

	.cpicLiError3 {
		width:26%;
	}

	.cpicLiError4 {
		width:26%;
	}

	.piccLiError1 {
		width:20%;
	}

	.piccLiError2 {
		width:26%;
	}

	.piccLiError3 {
		width:26%;
	}

	.piccLiError4 {
		width:26%;
	}

	.gpicLiError1 {
		width:20%;
	}

	.gpicLiError2 {
		width:26%;
	}

	.gpicLiError3 {
		width:26%;
	}

	.gpicLiError4 {
		width:26%;
	}

	.cpicImgError {
		margin-left: -15px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 75px;
		background: url("../../../lib/image/cpic.png") left no-repeat white;
		background-size: 90px;
	}

	.piccImgError {
		margin-left: -7px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 75px;
		background: url("../../../lib/image/picc.png") left no-repeat white;
		background-size: 73px;
	}

	.gpicImgError {
		margin-left: -15px;
		margin-top: -24px;
		text-align: center;
		vertical-align: middle;
		height: 75px;
		background: url("../../../lib/image/gpic.png") left no-repeat white;
		background-size: 90px;
	}
}
</style>