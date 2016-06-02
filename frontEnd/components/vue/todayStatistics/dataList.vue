<template>
	<div class="itemList">
		<table>
			<tbody>
				<tr>
					<td>
						{{item.date}}
					</td>
					<td>
						{{item.timeRate}}
					</td>
					<td>
						{{item.tradeCount}}
					</td>
					<td>
						{{item.tradeCount}}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
'use strict';

module.exports= {
	props:['item']
}
</script>

<style type="text/css">

.itemList {
	width: 99%;
	min-width: 1000px;
	display:block;
	text-align: center;
	vertical-align: middle;
    	border-radius: 5px;
    	margin-top: 6px;
    	margin-left: 5px;
    	margin-right: 5px;
}

.itemList table {
	margin-right: 10px;
    	padding-right: 10px;
	width: 100%;
	display:block;
	text-align: center;
	vertical-align: middle;
}

.itemList table tbody tr {
	height: 30px;
	width: 100%;
	text-align: center;
	vertical-align: middle;
	padding: 10px 0px 0px 0px;
	border-bottom: 1px #e5e5e5 solid;
}

.itemList table tbody tr td {
	min-width: 250px;
	text-align: center;
	vertical-align: middle;
    	border-radius: 5px;
}

.itemList table tr:nth-child(odd)  {
   	 background: #EEE;
}

</style>