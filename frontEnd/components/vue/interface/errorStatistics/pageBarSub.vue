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
	created: function() {
                    	this.initQueryData();
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
		initQueryData: function() {
                            	this.all = 1;
                            	this.cur = 1;
                        },
	 	query: function() {
	 		this.dispatchPageData(this.cur);
	 	},
	            btnClick: function(data) {//页码点击事件
		            if(data !== this.cur){
		             	this.closeTr();
		                    	this.cur = data; 
		                    	this.dispatchPageData(this.cur);
		            }
	            },
	            closeTr: function() {//解决点击页码后,新增tr未关闭问题
	            	this.$dispatch('dispatch--pageBarSub-floatBar-closeTr', true);
	            },
	            dispatchPageData: function(curPage) {//将当前点击页数传递到父组件中
	            	//设置点击页数
	            	this.$dispatch('dispatch-pageBarSub-floatBar-curPage', curPage);
	            }
        	},
        	events: {
	    	'broadcast-floatBar-pageBarSub-getPageData': function(pageData) {//从父组件中获取页数信息
		      	// 设置当前页数及总页数
		      	this.cur = pageData.cur;
		      	this.all = pageData.all;
	    	}
	}
}
</script>

<style type="text/css">



</style>

