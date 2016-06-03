<template>
	<div class="queryBar">
		<ul>
			<li>
				<div>
					<span>
						<label>保险公司</label>
						<select>
							<option value="全部">全部</option>
							<option value="人保">人保</option>
							<option value="太保">太保</option>
							<option value="国寿财">国寿财</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>承保地区</label>
						<select>
							<option value="全部">全部</option>
							<option value="人保">人保</option>
							<option value="太保">太保</option>
							<option value="国寿财">国寿财</option>
						</select>
					</span>
				</div>
			</li>
			<li>
				<div>
					<span>
						<label>交易类型</label>
						<select>
							<option value="全部">全部</option>
							<option value="人保">人保</option>
							<option value="太保">太保</option>
							<option value="国寿财">国寿财</option>
						</select>
					</span>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
'use strict';



</script>

<style type="text/css">

.queryBar {
	margin: 5px;
	height: 36px;
	background: #e2edfb;
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
	display: block;
	text-align: center;
	vertical-align: middle; 
	font-size: 15px;
	font-family: Microsoft Yahei,\\5FAE\8F6F\96C5\9ED1,Tahoma,Arial,Helvetica,STHeiti;
}

.queryBar ul li {
	padding: 6px 100px 0px 100px;
	display:inline-block;
}

.queryBar ul li select {
	border: 1px solid #d6d6d6;
    	border-radius: 5px;
    	background: #f2eeee;
}

</style>