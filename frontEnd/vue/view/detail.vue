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