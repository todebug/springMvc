<template>
	<div id="todayStatistics">
		<head-Bar></head-Bar>
		<data-Table :xdy="xdy"></data-Table> 
		<data-Echart></data-Echart>
		<condition-Bar></condition-Bar>
		<data-List></data-List>
	</div>
</template>

<script>
module.exports= {
	data: function () {
	    return { 
	    	xdy: {
	    	    accessCount: '434',
		    accessTime: '00:03:09',
		    averageAccessPage: '1.19',
		    conversionCount: '34',
		    conversionRate: '31%',
		    orderConversionRate: '55%'}
		     }
	  },
	components: {
	headBar: require ('../../components/vue/todayStatistics/headBar.vue'),
	dataTable: require ('../../components/vue/todayStatistics/dataTable.vue'),
	dataEchart: require ('../../components/vue/todayStatistics/dataEchart.vue'),
	conditionBar: require ('../../components/vue/todayStatistics/conditionBar.vue'),
	dataList: require ('../../components/vue/todayStatistics/dataList.vue')
	}
	
}
</script>