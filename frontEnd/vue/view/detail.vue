<template>
<div id="detail">
        <aside class="sideBar">
             <div class="fullScreenTarget">
                <a @click="fullScreen">全屏</a>
            </div> 
            <div class="profile-bar">
                <ul id="switchToggle" >
                        <li>
                                    <a id="interface" @click="switchToggle" class="minus">保险对接平台</a>
                                    <ul id="interfaceChild" class="displayOpen">
                                        <li class="active">
                                                <a v-link="{ path: '/errorStatistics' }" @click="choose">
                                                    错误统计
                                                </a>
                                        </li>
                                        <li>
                                                <a v-link="{ path: '/interfaceLog' }" @click="choose">
                                                    日志查询
                                                </a>
                                        </li>
                                    </ul>
                        </li>
                </ul>
            </div>
        </aside>
        <section class="main" id="main">
            <router-view>
            </router-view>
        </section>
        </div>
  </template>
<head>
<script>
'use strict';
module.exports = {
    ready: function() {
            //初始化事件
            this.cancelFullScreen();
    },
    methods: {
            cancelFullScreen: function() {
                    $( document ).bind('webkitfullscreenchange',function(){
                        $('#main').toggleClass('fullscreen');
                    });
            },
            fullScreen: function() {
                    var elem = document.getElementById("main");
                    if (elem.webkitRequestFullScreen) {
                        elem.webkitRequestFullScreen();
                    }              
            },
            choose: function(event) {
                    $('#switchToggle li ul li').removeClass('active');
                    $(event.target.parentNode).addClass('active');
            },
            displayOpenOrClosed: function(childId) {
                    if($('#'+childId).hasClass('displayClosed')){
                        $('#'+childId).removeClass('displayClosed').addClass('displayOpen');
                    }else{
                        $('#'+childId).removeClass('displayOpen').addClass('displayClosed');
                    }
            },
            showMinusOrPlus: function(id) {
                    if($('#'+id).hasClass('minus')){
                        $('#'+id).removeClass('minus').addClass('plus');
                    }else{
                        $('#'+id).removeClass('plus').addClass('minus');
                    } 
            },
            switchToggle: function(event) {
                    this.showMinusOrPlus(event.target.id);
                    this.displayOpenOrClosed(event.target.nextElementSibling.id);
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

.fullScreenTarget a {
    position: fixed;
    width: 180px;
    text-align: center;
    top: 40px;
    left: 90%;
    vertical-align: middle;
    z-index: 100;
    text-decoration: none;
    cursor: pointer;
    font-family: 微软雅黑;
    font-weight: blod;
    color: #ffffff;
    font-size: 12px;
}

.profile-bar {
    display: block;
    padding: 10px 10px 30px 10px;
    margin-left: 15px;
    position: fixed;
    left: 0;
    width: 180px;
    bottom: 0;
    top: 70px;
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
    line-height: 32px;
}

.profile-bar li a {
    display:block;
    color: #555;
    text-decoration: none;
    font-size: 18px;
    font-weight: bold;
    text-indent: 10px;
    cursor: pointer;
}

.displayOpen .active {
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
    text-indent: 30px;
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

.plus {  
        background: url('../../components/lib/image/plus.png') center no-repeat #f8fbfa;
        background-size: 10px;
        background-position: 120px;
        width: 150px;
}

.minus {
        background: url('../../components/lib/image/minus.png') center no-repeat #f8fbfa;
        background-size: 10px;
        background-position: 120px;
        width: 150px;
}

section.fullscreen    { left: 0px; top:0px;}

</style>
