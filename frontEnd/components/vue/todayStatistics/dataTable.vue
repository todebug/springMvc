<template>
	<table>
		<tr>
			<td class="uptbodytd">
				<span>太保</span>
				<br>
				<tbody class="texttbody">
					<tr>
						<td>总交易数</td>
						<td>总失败数</td>
						<td>最大时效性</td>
						<td>最小时效性</td>
					</tr>
					<tr>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
					</tr>
				</tbody>
			</td>
			<td class="uptbodytd">
				<span>人保</span>
				<br>
				<tbody class="texttbody">
					<tr class="texttbodytr">
						<td>总交易数</td>
						<td>总失败数</td>
						<td>最大时效性</td>
						<td>最小时效性</td>
					</tr>
					<tr class="texttbodytr">
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
					</tr>
				</tbody>
			</td>
			<td class="uptbodytd">
				<span>国寿财</span>
				<br>
				<tbody class="texttbody">
					<tr class="texttbodytr">
						<td>总交易数</td>
						<td>总失败数</td>
						<td>最大时效性</td>
						<td>最小时效性</td>
					</tr>
					<tr class="texttbodytr">
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
						<td>{{xdy.accessCount}}</td>
					</tr>
				</tbody>
			</td>
		</tr>
	</table>
</template>

<script>
'use strict';
module.exports= {
	props:['xdy']
	// compiled: function () {
	// console.log(this.item);
	// }
}
</script>

<style type="text/css">

.uptbodytd {
	text-align:center;
	vertical-align:middle;
	width:400px;
	order-right: 1px solid #e4e4e4;
	border-top: 1px solid white;
	padding: 8px 30px;
	line-height: 1.2;
	display:inline;
}

.texttbody {
	text-align:center;
	vertical-align:middle;
	display: block;
	width:400px;
}

.texttbodytr {
	text-align:center;
	vertical-align:middle;
	width:100px;
}


</style>