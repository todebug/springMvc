<template>
<div class="col-sm-9 col-sm-offset-3  col-md-offset-2 main">
	<div>
		<a >实时</a>
		<a >今天</a>
		<a >昨天</a>
		<a >最近7天</a>
		<a >最近30天</a>
		<a >时间区间</a>
	</div>
	<div>
		<div class='datePicker input-group date' id='datetimepicker1' readonly="readonly">
	                            <input type='text' class="form-control" />
	                            <span class="input-group-addon">
	                            	<span class="glyphicon glyphicon-calendar"></span>
	                            </span>
                        	</div>
                        	<div class='datePicker input-group date' id='datetimepicker2' readonly="readonly">
	                            <input type='text' class="form-control" />
	                            <span class="input-group-addon">
	                            	<span class="glyphicon glyphicon-calendar"></span>
	                            </span>
                        	</div>
	</div>
	<div>
		<a >按时</a>
		<a >按日</a>
		<a >按周</a>
		<a >按月</a>
	</div>
	
</div>


</template>

<script>
'use strict';
require('imports?$=jquery!../../lib/js/bootstrap.min.js');
require('imports?$=jquery!../../lib/js/bootstrap-datetimepicker.min.js');
var moment=require('moment');
var today=moment().format('YYYY-MM-DD');
var options={
	locale: 'zh-cn',
	format: 'yyyy-mm-dd hh:ii',
	defaultDate: today
};
module.exports= {
	ready:function(){
		$('#datetimepicker1').datetimepicker(options);
		$('#datetimepicker2').datetimepicker(options);
	}
}
</script>

<style type="text/css">
.datePicker {
	width:200px;
}
</style>