<template>
	<div class="page-bar">
	            <ul>	
	            	            <li>
	            	            		<a v-on:click="btnClick(1)">首页</a>
	            	            </li>
		            <li v-if="showFirst">
		            		<a v-on:click="cur--" @click="query">上一页</a>
		            </li>
		            <li v-for="index in indexs"  v-bind:class="{ 'active': cur === index}">
		                	<a v-on:click="btnClick(index)">{{ index }}</a>
		            </li>
		            <li v-if="showLast">
		            		<a v-on:click="cur++" @click="query">下一页</a>
		            </li>
		             <li>
		             		<a>共<i>{{all}}</i>页</a>
		             	</li>
		              <li>
	            	            		<a v-on:click="btnClick(all)">尾页</a>
	            	            </li>
	            </ul>
        </div>
</template>

<script>
'use strict';
module.exports= {
	data: function() {
	    //绑定数据
	    return  {
	    	all: 1, //总页数
        		cur: 1,//当前页码
        		}
	},
	ready: function() {

	},
	computed: {
	            indexs: function() {
		              var left = 1
		              var right = this.all
		              var ar = [] 
		              if(this.all>= 11){
			                if(this.cur > 5 && this.cur < this.all-4){
				                        left = this.cur - 5
				                        right = this.cur + 4
			                }else{
				                    if(this.cur<=5){
				                        left = 1
				                        right = 10
				                    }else{
				                        right = this.all
				                        left = this.all -9
				                    }
			                }
		             }
		            while (left <= right){
			                ar.push(left)
			                left ++
		            }   
		            		return ar
	           },
	           showLast: function() {
		                if(this.cur === this.all){
		                    return false
		                }
		                if(this.all<=5){
		                	return false
		                }
		                return true
	           },
	           showFirst: function() {
		                if(this.cur === 1){
		                    return false
		                }
		                if(this.cur<=5){
		                	return false
		                }
	               	return true
	           }
	},
	 methods: {
	 	query: function() {
	 		this.dispatchPageData(this.cur);
	 	},
	            btnClick: function(data) {//页码点击事件
		                if(data !== this.cur){
		                    this.cur = data 
		                    this.dispatchPageData(this.cur);
		                }
	            },
	            dispatchPageData: function(curPage) {//将当前点击页数传递到父组件中
	            		//设置点击页数
	            		this.$dispatch('dispatch-pageBar-dataList-curPage', curPage);
	            }
        	},
        	events: {
	    'broadcast-todayStatistics-pageBar-getPageData': function(pageData) {//从父组件中获取页数信息
	      	// 设置当前页数及总页数
	      	this.cur = pageData.cur;
	      	this.all = pageData.all;
	    }
	}
}
</script>

<style type="text/css">

.page-bar {
	font-size: 12px;
	font-family: 微软雅黑;
	width: 100%;	
	min-width: 500px;
	height: 40px;
	display:block;
	text-align: center;
	vertical-align: middle;
	padding-top: 5px;
	margin: 0 auto;
	border-radius: 5px;
}

ul,li {
    	margin: 0px;
    	padding: 0px;
}

li {
    	list-style: none
}

.page-bar ul {
    	display: inline-block;
}

.page-bar ul li {
    	display: inline-block;
    	float: left;
}

.page-bar li:first-child>a {
   	margin-left: 0px
}

.page-bar a {
    	border: 1px solid #ddd;
    	text-decoration: none;
    	position: relative;
    	float: left;
    	padding: 6px 12px;
    	margin-left: -1px;
    	line-height: 1.42857143;
    	color: #337ab7;
    	cursor: pointer
}

.page-bar a:hover {
    	background-color: #eee;
}

.page-bar .active a {
    	color: #fff;
    	cursor: default;
    	background-color: #337ab7;
    	border-color: #337ab7;
}

.page-bar i {
    	font-style:normal;
    	color: #d44950;
    	font-family: 微软雅黑;
    	margin: 0px 4px;
    	font-size: 12px;
}

</style>

