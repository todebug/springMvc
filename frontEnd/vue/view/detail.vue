<template>
<div>
        <aside>
            <div class="profile-picture">
            </div>
            <div class="profile-bar">
                <ul id="chooseStyle">
                    <li class="active" >
                        <a v-link="{ path: '/index' }" @click="choose">
                                首页
                            </a>
                    </li>
                    <li>
                        <a v-link="{ path: '/user' }" @click="choose">
                                用户
                            </a>
                    </li>
                    <li>
                        <a v-link="{ path: '/todayStatistics' }" @click="choose">
                                今日统计
                            </a>
                    </li>
                </ul>
            </div>
        </aside>
        <section>
            <router-view>
            </router-view>
        </section>
        </div>
  </template>
<head>
<script>
'use strict';
module.exports = {
    methods: {
        'choose': function() {
            $('#chooseStyle li').click(function(){
                $('#chooseStyle li').removeClass('active');
                $(this).addClass('active');
            })
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
    }
    
    .profile-picture {
        background: white;
        position: fixed;
        left: 0;
        width: 200px;
        text-align: center;
        bottom: 0;
        top: 60px;
        height: 200px;
        background: url('../../components/lib/image/man.jpg') center no-repeat white;
        background-position: 50 50;
        background-size: 80px;
        border: 0;
        vertical-align: middle;
    }
    
    .profile-bar {
        display: block;
        padding: 50px 10px 30px 10px;
        background: white;
        position: fixed;
        left: 0;
        width: 200px;
        bottom: 0;
        top: 200px;
    }
    
    .profile-bar li{
        display:block;
        color: #555;
        padding-left: 25px;
        text-decoration: none;
        font-size: 16px;
        text-align: -webkit-match-parent;
        width:180px;
    }

    .profile-bar li a {
        display:block;
        color: #555;
        padding-left: 25px;
        text-decoration: none;
        font-size: 16px;
        width:180px;
    }

    .active {
        color: #f1f1f1;
        background-color: #41a1db;
    }
    
    .profile-bar li a:hover {
        color: #337ab7;
        padding-left: 20px;
        font-weight: bold;
        text-decoration: none;
    }
    
    .profile-bar>li>a:hover,
    .profile-bar>li>a:focus {
        text-decoration: none;
        background-color: #eee;
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
</style>
