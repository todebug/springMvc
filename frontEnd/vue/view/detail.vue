<template>
<div id="detail">
        <aside class="sideBar">
            <div class="profile-picture">
            </div>
            <div class="profile-bar">
                <ul id="switchToggle" >
                        <li>
                                <a @click="switchToggle">趋势分析</a>
                                <ul id="analyTrendChild" class="displayClosed">
                                        <li>
                                            <a v-link="{ path: '/index' }">
                                                    数据分析
                                                </a>
                                        </li>
                                        <li>
                                            <a v-link="{ path: '/user' }">
                                                    用户分析
                                                </a>
                                        </li>
                                        </li>
                                </ul>
                        <li>
                                <a @click="switchToggle">数据统计</a>
                                <ul id="analyDataChild">
                                        <li class="active">
                                            <a v-link="{ path: '/todayStatistics' }" @click="choose">
                                                    今日统计
                                                </a>
                                        </li>
                                </ul>
                        </li>
                </ul>
            </div>
        </aside>
        <section class="main">
            <router-view>
            </router-view>
        </section>
        </div>
  </template>
<head>
<script>
'use strict';
module.exports = {
    ready: function(){
        //初始化事件
        this.switchToggle();
        this.choose();
    },
    methods: {
        choose: function() {
            $('#switchToggle li ul li').click(function(){
                $('#switchToggle li ul li').removeClass('active');
                $(this).addClass('active');
            })
        },
        switchToggle: function() {
                 //$('#switchToggle li a').click(function(event){
                 console.log(this);
                // console.log(this.$$);
                 console.log(this.$el);
                 console.log(this.$options);
                var nextElementSibling= this.nextElementSibling;
                $('#analyTrendChild').toggle();
                if(nextElementSibling!=='null'){
                    //console.log($('#'+nextElementSibling.id));
                    //var id = $('#'+nextElementSibling.id).selector;
                    //console.log(id);
                    //console.log($(id).val());
                   //$('#'+nextElementSibling.id).toggle();
                }
            //})
        }
    }
}
</script>

<style type="text/css">

aside {
    background: white;
    position: fixed;
    left: 0;
    width: 200px;
    bottom: 0;
    top: 60px;
    overflow: auto;
    margin-top: 5px;
    margin-left: 5px;
    margin-right: 5px;
    border: 1px solid #d6d6d6;
    border-radius: 5px;
}

.profile-picture {
    background: white;
    position: fixed;
    left: 0;
    width: 180px;
    text-align: center;
    bottom: 0;
    top: 70px;
    height: 200px;
    background: url('../../components/lib/image/man.jpg') center no-repeat white;
    background-position: 50 50;
    background-size: 75px;
    border: 0;
    margin-left: 10px;
    vertical-align: middle;
}

.profile-bar {
    display: block;
    padding: 50px 10px 30px 10px;
    margin-left: 15px;
    position: fixed;
    left: 0;
    width: 180px;
    bottom: 0;
    top: 200px;
    background: #f8fbfa;
    border-radius: 5px;
}

.profile-bar li{
    display:block;
    color: #555;
    padding-left: 5px;
    text-decoration: none;
    font-size: 16px;
    width:180px;
}

.profile-bar li a {
    display:block;
    color: #555;
    text-decoration: none;
    font-size: 18px;
    width:180px;
    font-weight: bold;
    text-indent: 30px;
    cursor: pointer;
}

.active {
    color: #bfb2b2;
    background-color: #bfb2b2;
    border-radius: 5px;
}

.profile-bar li a:hover {
    color: #5f5570;
    font-weight: bold;
    text-decoration: none;
}

.profile-bar>li>a:hover,
.profile-bar>li>a:focus {
    text-decoration: none;
    background-color: #eee;
}

.profile-bar ul li ul li {
    display:block;
    color: #555;
    text-decoration: none;
    font-size: 14px;
    width:150px;
    border-bottom: 1px #e5e5e5 solid;
}

.profile-bar ul li ul li a {
    text-indent: 42px;
    display:block;
    color: #757d90;
    text-decoration: none;
    font-size: 14px;
    width:150px;
    cursor: pointer;

}

section {
    background: #fafafa;
    position: absolute;
    bottom: 0;
    top: 60px;
    left: 200px;
    right: 0;
    overflow: auto;
}

.main {
    margin-top: 6px;
    margin-left: 10px;
    margin-right: 10px;
    border: 1px solid #d6d6d6;
    border-radius: 5px;
    background: #faf8f8;
}

.displayOpen {
    display: block;
}

.displayClosed {
    display: none;
}

</style>
