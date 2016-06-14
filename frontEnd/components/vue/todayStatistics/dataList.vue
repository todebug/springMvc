<template>
	<div class="itemList">
		<table>
			<tbody>
				<tr>
					<td>
						{{item.date}}
					</td>
					<td>
						{{item.date}}
					</td>
					<td>
						{{item.date}}
					</td>
					<td>
						{{item.date}}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
'use strict';
var pageData = {
	cur: 0,
	all: 0
}
module.exports= {
	data: function() {
	    //绑定数据
	    return  {pageData}
	},
	ready: function() {

	},
	props:['item'],
	compiled: function () {
		//console.log(this.item);
	},
	methods: {
		dispatchPageData: function(curPage) {//将页数总数传递到父组件中,最终传递至子组件[pageBar.vue]中
	            		//设置点击页数
	            		this.$dispatch('dispatch-dataList-pageBar-PageData', pageData);
	            }
	},
	events: {
		'broadcast-todayStatistics-dataList-curPage': function(curPage) {//获取[pageBar.vue]传递至父组件的数据,当前点击页数
	    		//查询当前页数信息
	    		
	    	}
	}
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