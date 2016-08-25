### 项目介绍
项目基于 node.js、jquery 、vue.js、 vue-router实现.
本项目为SPA(single page web application).
基于node环境,使用vue组件开发,页面切换使用vue-router实现

vue中文教程及API: http://cn.vuejs.org/guide/index.html
vue-router文档说明: http://router.vuejs.org/zh-cn/basic.html
### 软件安装
# Node.js：v4.0+
node下载: https://nodejs.org/en/

### 安装依赖模块
#全局安装 webpack gulp
$ npm install gulp -g 
$ npm install webpack -g 
#在项目frontEnd目录下,执行以下命令,安装项目依赖模块:
$ npm install

### 本地开发环境
#serve app at localhost:8080
#在项目frontEnd目录下,执行以下命令,运行服务
$ npm run dev
#访问地址: 
http://localhost:8080

### 部署&发布 
#在项目frontEnd目录下,执行以下命令,待命令执行完毕,提交svn后,发布Jenkins即可.
gulp

### 目录结构
+application-monitor
    +web
        +frontEnd   #前端开发目录
            +components #项目组件库
                +lib    #项目公共依赖库        
                    +css        #公共css资源, eg: bootstrap,datetimepicker...等
                    +fonts      #字体资源, eg: datetimepicker字体...
                    +image  #全局图片资源 
                    +js         #公共js
                        +dev        #config.js开发地址
                        +prod       #config.js生产地址
                        +locales    #bootstrap-datetimepicker中文包
                        -base.js    #公共配置信息, eg: 保险公司...
                        -bootstrap-datetimepicker.min.js    #时间控件依赖
                        -bootstrap-min-js           #时间控件依赖
                        -common-filter.js           #vue公共过滤器
                        -config.js              #fetch(异步ajax模块)请求配置地址
                        -echartTheme.js             #echart所用主题

                +vue    #vue组件目录
                    +commonVue      #vue公共组件, eg: 加载动画vue组件...
                    +interface      #业务组件, 菜单[保险对接平台]相关业务组件
                        +...            #分模块对应业务组件
                    +quartz     #业务组件, 菜单[批处理平台]相关业务组件
                        +...            #分模块对应业务组件
                    +...            #业务组件,照此规则顺延添加...
            +dist   #vue打包、压缩文件夹
                -router.js      #最终vue压缩js文件,将此文件引入html页面即可
                -router.js.map      #执行压缩命令附属文件
            +node_modules       #项目依赖模块,安装node.js后, 在项目frontEnd目录下,执行$npm i命令后,自动生成
                +...            #对应模块由package.json中定义下载 
            +vue    #项目组件入口
                +view       #vue业务组件入口
                    +interface  #业务组件, 菜单[保险对接平台]入口
                        +...        #分模块对应业务组件
                    +quartz ##业务组件, 菜单[批处理平台]入口
                        +...        #分模块对应业务组件
                    +...        #业务组件,照此规则顺延添加...
                    -detail.vue     #业务组件的父组件,vue-router跳转连接地址对应如此
                -router.js  #vue全局配置文件
                -routers.js     #vue-router配置跳转地址连接
            -gulpfile.js        #gulp 打包任务配置文件
            -index.html         #挂载html页面
            -package.json   #项目依赖配置
            -README.md  #项目说明
            -webpack.config.js  #webpack配置文件

### vue开发规则说明
1. 由于当前vue组件使用vue-router分发请求,故全局只有一个VUE实例.
2. vue存在自己的声明周期,详见教程说明
    created 当前dom节点还未生成
    beforeComplie
    complie
    ready       当前dom节点已生成
    beforeDestroy
    destroyed
    故实际开发时,要善用vue的生命周期执行命令
3. 由于当前项目属于中小型内部项目,故没有使用vuex.
故实际开发时,对于兄弟组件的数据传递,父子组件的数据传递需要遵循以下规则,方面日后开发及维护
    规则一:  父子组件通信
            1⃣️ 如子组件仅需要展示,没有其他复杂逻辑,则使用[prop]方法
            2⃣️ 如子组件含有复杂逻辑处理,则使用[$dispatch](子组件向父组件冒泡)、 [$broadcast](父组件向子组件派发)方法.
    规则二:  兄弟组件通信
            1⃣️ 兄弟组件通信,均使用[$dispatch](子组件向父组件冒泡)、 [$broadcast](父组件向子组件派发)方法.
            2⃣️  对应方法按照vue规则为自定义方法,故此需遵循以下规则:
                子组件向父组件冒泡
                    eg:     this.$dispatch('dispatch-errorStatistics-pageBar-dataList-curPage', curPage);
                    说明:  dispatch---说明为子组件冒泡事件
                               errorStatistics---接收数据的父组件
                               pageBar---说明为子组件
                               dataList---说明为目的兄弟组件
                               curPage---说明为传递数据, 业务意义:"当前页码"
                父组件向子组件派发
                    eg:      $broadcast('broadcast-errorStatistics-pageBar-getPageData', pageData);
                    说明:   broadcast---说明为父组件派发事件
                        errorStatistics---派发数据的父组件
                        pageBar---接收数据的父组件
                        getPageData---接收的数据,业务意义: "页码信息"
                兄弟组件接收
                    eg:      events: {'broadcast-errorStatistics-pageBar-getPageData': function(pageData) {}}
                    说明:   broadcast---说明为父组件派发事件
                        errorStatistics---派发数据的父组件
                        pageBar---接收数据的父组件
                        getPageData---接收的数据,业务意义: "页码信息"
    注: 对于兄弟组件的数据交换,方法定义请严格按照以上规则处理,便于后期人员开发运维时理解.

4. 使用模块导入的依赖时,注意全局事件的销毁,此处可以使用vue声明周期的beforeDestroy方法中定义销毁事件,防止使用vue-router全局页面切换时,事件一直存在,浪费性能,影响功能.
5. vue的优势是数据绑定, 但是基于vue-router及SPA(single page web application)的影响,对于每个组件中对应的绑定数据,所以需在每个组件的created方法中定义initQueryData方法,并在initQueryData方法中初始化所有绑定数据,防止vue-router切换时,绑定数据仍存储旧数据,导致数据功能异常.

### vue-router快速上手开发
1. 在routers.js中定义路径 
    eg: 
        '/quartzTask': {                            #定义跳转路径→→→此路径在detail.vue中绑定 
                            component: require('./view/quartz/quartzTask.vue')      #定义跳转页面对应组件
                }
2. 在detail.vue中绑定跳转事件 
    eg: 
        <li>                                    
                                    <a id="quartzTask" @click="switchToggle" class="plus">批处理平台</a> 
                                    <ul id="quartzTaskChild" class="displayClosed">
                                            <li>
                                                <a v-link="{ path: '/quartzTask' }" @click="choose">    #v-link则对应绑定的新增跳转组件页面
                                                    批处理任务查询
                                                </a>
                                            </li>
                                    </ul>
                        </li>
3.定义新增对应业务组件页面
    1⃣️ 在root→web→frontEnd→vue→view→建立新的业务文件夹→new parent vue file作为业务入口组件.
    2⃣️ 在root→web→frontEnd→components→vue→建立与之对应的业务文件夹→new child vue file作为业务子组件.

### vue快速上手开发
1. 定义vue组件文件
    自定义业务名称+后缀名vue
        eg: afile.vue 
2. vue 组件内容
    注: 当前项目为es5标准,故不可使用es6定义规范内容
    vue页面分为三部分:
        1⃣️ <template>...</template>部分
                其中内容为 html页面片段,
                注: vue推崇使用一个<div>作为<template>的唯一子元素.
                推荐写法为: 
                    <template><div>...</div></template>
        2⃣️ css样式部分
            <style type="text/css">...</style>
            注: 当前项目为SPA(single page web application).故开发时避免样式重复定义.
        3⃣️ vueJs部分(通过js结构说明)
        ###父组件部分begin
        <template>
            <div>
                <input @click="domOperate">                 #@click→vue定义绑定事件,对应下方[method]中对应方法
                <child :info="bindVarObject1"></child>              #vue引用子组件显示,于下方[components]中引入所对应, 注意[:info]写法,此为vue---prop数据传递,将绑定数据由父组件传递至子组件中
                <child-Vue></child-Vue>                     #vue引用子组件显示,注意[-]用法,在template中不推荐使用驼峰结构,需要使用连字符拆分,否则给予警告.
            </div>
        </template>
        <script>
        'use strict';                                       #使用严格模式,ES5规范
        var config = require('../../../lib/js/config');                     #如当前页面需要请求fetch函数异步请求数据,需引入该模块,该模块配置了请求地址.
        var bindVarObject1 = [];                                #vue内部对数据进行绑定,对于绑定的数据,vue自动根据规则更新dom节点.注意如下data:functiong中定义写法
        var bindVarObject2 = {obj1:{},obj2: ''};                        
        var unBindVarObject = {};                               #当前组件中不需要进行数据双向绑定的对象
        module.exports= {                                   
            data: function () {                             #vue绑定数据写法
                    //绑定数据
                    return  {bindVarObject1,bindVarObject2}             #此中定义的为绑定数据对象
            },
            created: function() {                               #需了解[vue生命周期],当前执行方法需脱离dom节点,否则功能会出现异常.
                this.initQueryData();                           #自定义规则方法→初始化当前组件绑定的对象,防止vue-router切换时,导致系统业务异常
            },
            ready: function() {                             #需了解[vue生命周期],当前执行方法时,dom节点已经生成,可以处理一些依托dom节点的事件
                this.fetch();
            },
            computed: {                                 #需了解vue[计算属性],可以对于绑定的数据进行复杂的逻辑处理

            },  
            beforeDestroy: function() {                         #需了解[vue生命周期],对于一些引入的依赖模块,由于当前项目所致,具有全局属性(只有一个html),故可以在此执行一些销毁方法,避免业务逻辑功能冲突
                                
                    },
            methods: {                                  #vue方法定义写法
                initQueryData: function() {                     
                    this.bindVarObject1 = {};                   #对于vue绑定的数据,需要使用[this]进行识别
                    this.bindVarObject2 = {obj1:{},obj2: ''};
                },
                domOperate: function(event) {                   #与<template>中input中定义@click事件对应,注:event为可选参数,如定义,可以获得原生事件.
                    this.bindVarObject2.obj1 = event.target.id;
                    this.bindVarObject2.obj2 = event.target.value;
                },
                fetch: function(queryData) {                        #此处为fetch(异步ajax模块)样例应用 
                    var _this = this;                       #此处需要处理this,方便下文使用外部数据及方法
                    var url = config.host+'batchJob/getBatchJobList';       #定义请求地址
                    var result = fetch(url,{
                        method: 'post',                     #请求方式
                        mode: 'cors',                       #跨域请求
                        headers: {                      #请求类型
                            'Accept': 'application/json',
                                'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({              #请求数据
                            'job': queryData.job,
                            'page': queryData.page,
                            'size': queryData.size
                        })
                    });
                    result.then(function(response) {                #promise函数处理
                        return response.json();
                    }).then(function(j) {
                        //console.log(JSON.parse(JSON.stringify(j)));
                        _this.bindVarObject1=[];                #获取外部绑定数据
                                $.each(j.bindVarObject1, function(i,val){           #向绑定数据执行赋值处理    
                                    _this.bindVarObject1.push(val);
                                });
                    }).catch(function(ex) {
                        console.log('failed', ex)
                    });
                },
                broadcastPageData: function() {                 #定义父组件通过$broadcast方法向子组件传递数据,注意自定义方法命名规范
                            this.$broadcast('broadcast-parent-child-passData', passData);
                        },
            },
            events: {                                   #父子组件通信定义写法
                'dispatch-child-parent-passData': function(passData) {      #父组件接收子组件传递数据,(注意:上文定义方法命名规范,便于开发后期维护)
                    doSomeThing...
                },
            },
            components: {                                   #与<template>中引入组件对应,定义vue组件文件位置
                child: require ('./child.vue'),
                childVue: require ('./childVue.vue'),                   #注意此处使用驼峰写法,而非使用"-"
            }
        }
        ###父组件部分end
        ###子组件部分begin
        <template>
            <div>
                <table>
                    <thead><tr><td></td></tr></thead>
                    <tbody>
                        <tr  v-for="item in info" :items="item">            #{v-for]vue循环写法,[item in info]→将父组件传递数据info命名item,[:item="item"]将items进行遍历,
                            <td>{{items.obj1}}</td>             #遍历其中一个对象的属性→使用→{{}}
                        </tr>
                    </tbody>
                </table>
            </div>
        </template>
        <script>
        'use strict';
        module.exports= {
            props:['info'],                                         #使用vue提供的prop,定义父组件传递到子组件的数据内容
            compiled: function () {                                 #可以使用此方法将内容打印到控制台,查看数据进行调试
                //console.log(this.info);
            },
            methods: {                              
                dispatchPageData: function(curPage) {                   #自定义方法
                            this.$dispatch('dispatch-child-parent-passData', passData); #使用vue提供$dispatch方法向父组件传递数据,注意自定义方法命名规范
                        }
            }
            events: {                                   
                'broadcast-parent-child-passData': function(passData) {         #接收由父组件向子组件传递数据
                    doSomeThing...
                },
            },
        }
        </script>
        ###子组件部分end

### 相关模块依赖说明
    本项目已在配置文件中全局定义[fetch]及[jquery].
    故在组件中
        如使用fetch函数,则直接使用→fetch
        如使用jquery,则直接使用→$
        如使用时间处理函数,则使用→var moment=require('moment');引入
        如使用定时,则使用→var schedule = require('node-schedule');引入
        ...
    注: 对于package中定义的依赖模块,若使用,则直接使用require('模块名称')即可使用,
        对于自定义js,则需要声明对应路径名称,后缀名js可省略.
        eg: 
            var echarts = require('echarts');       #package中已定义,node_modules中已安装
            var config = require('../../../lib/js/config'); #自定义config.js模块引用


### License
[MIT]
