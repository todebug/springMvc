<template>
<div>
        <aside>
            <div class="profile-picture">
            </div>
            <div class="profile-bar">
                <ul>
                    <li  id="firstPage"  class="active" @click="choose($event)">
                        <a v-link="{ path: '/index' }">
                                首页
                            </a>
                    </li>
                    <li id="userPage"  @click="choose($event)">
                        <a v-link="{ path: '/user' }">
                                用户
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
module.exports = {
    methods: {
        'choose': function(event) {
            var id = event.target.parentNode.id;
            if (id ==='firstPage') {
                if ($('#userPage').attr('class') ==='active') {
                    $('#userPage').removeClass('active');
                    $('#firstPage').addClass('active');
                }
            } else if (id ==='userPage') {
                if ($('#firstPage').attr('class') ==='active') {
                    $('#firstPage').removeClass('active');
                    $('#userPage').addClass('active');
                }
            }
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
        background: url("../../components/lib/image/man.jpg") center no-repeat white;
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
    
    .profile-bar li a {
        color: #555;
        padding-left: 25px;
        text-decoration: none;
        font-size: 16px;
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
