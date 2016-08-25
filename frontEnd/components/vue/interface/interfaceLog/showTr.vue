<template>
	<div class="search-hidden-infor">
		<div class="search-tab-title">
		            <ul class="clearfix">
		                <li class="active"><a href="#detail-information" data-toggle="tab"><span>详细信息</span></a></li>
		                <li><a href="#res-information" data-toggle="tab"><span>请求信息</span></a></li>
		                <li><a href="#req-information" data-toggle="tab"><span>响应信息</span></a></li>
		            </ul>
		            <i class="search-close"></i>
		</div>
	            <div class="tab-content">
		            <div id="detail-information" class="tab-pane fade in active">
		                <div class="search-tab-icon">
		                    <div class="search-icon insurance-policy-table">
		                        <table>
		                            <tr>
		                                <th>请求业务号</th>
		                                <td>{{show.requestNo | line}}</td>
		                                <th>返回业务号</th>
		                                <td>{{show.responseNo | line}}</td>
		                            </tr>
		                            <tr>
		                                <th>平台查询码</th>
		                                <td>{{show.queryNo | line}}</td>
		                                <th>平台确认码</th>
		                                <td>{{show.confirmNo | line}} </td>
		                            </tr>
		                             <tr>
		                                <th>响应时间</th>
		                                <td>{{show.responseTime | date}}</td>
		                                <th>经销商</th>
		                                <td>{{show.dealerName | line}}</td>
		                            </tr>
		                        </table>
		                    </div>
		                </div>
		            </div>
		            <div id="res-information" class="tab-pane fade">
		                <div class="search-tab-icon">
		                    <div class="search-icon insurance-policy-table">
		                        <table>
		                            <tr>
		                                <th>请求报文</th>
                                                        <td style="padding: 0 0;">
                                                            <textarea style="width:100%;height:400px;border:0px;" readonly>{{{show.req.content1}}}</textarea>
                                                        </td>
		                            </tr>
		                        </table>
		                    </div>
		                </div>
		            </div>
		            <div id="req-information" class="tab-pane fade">
		                <div class="search-tab-icon">
		                    <div class="search-icon insurance-policy-table">
		                        <table>
		                            <tr>
		                                <th>响应报文</th>
		                                <td style="padding: 0 0;">
                                                            <textarea style="width:100%;height:400px;border:0px;" readonly>{{{show.res.content1}}}</textarea>
                                                        </td>
		                            </tr>
		                        </table>
		                    </div>
		                </div>
		            </div>
	            </div>
            </div>
</template>

<script>
'use strict';
module.exports= {
	props: ['show'],
	compiled: function () {
		//console.log(this.show);
	}
}
</script>

<style type="text/css" >
.search-hidden-infor {
    margin-left: -10px;
}

.search-tab-title {
    position: relative;
    padding-left: 50px;
    border-bottom: 1px solid #d6d6d6;
    background: #f2f2f2;
}

.search-tab-title li span {
    padding: 0 20px;
    color: #666;
    font-size: 12px;
    font-family: 微软雅黑;
    float: left;
    cursor: pointer;
}

.search-tab-title li.active span {
    border-bottom: 1px solid #41a1db;
    color: #41a1db;
}

.search-close i {
    background: url(../../../lib/image/y-icon.png) no-repeat white;
    border: none;
}

.search-tab-title i.search-close {
    width: 30px;
    height: 30px;
    background-position: -3px -180px;
    position: absolute;
    right: 20px;
    top: 15px;
    cursor: pointer;
    -webkit-transition: -webkit-transform 1s ease-out;
    -moz-transition: -moz-transform 1s ease-out;
    -o-transition: -o-transform 1s ease-out;
    -ms-transition: -ms-transform 1s ease-out;
    transition: transform 1s ease-out;
}

.search-tab-title i.search-close:hover {
    -webkit-transform: rotateZ(360deg);
    -moz-transform: rotateZ(360deg);
    -o-transform: rotateZ(360deg);
    -ms-transform: rotateZ(360deg);
    transform: rotateZ(360deg);
}

.search-tab-icon {
    background: #f2f2f2;
    max-height: 680px;
    overflow-y: scroll;
    padding: 10px;
}

.search-tab-icon-text {
    color: #111;
    padding: 0 0 20px 20px;
    line-height: 25px;
    font-weight: bold;
    font-size: 12px;
    font-family: 微软雅黑;
}

.search-tab-icon-text-padding {
    color: #111;
    padding: 0 0 0 20px;
    line-height: 25px;
    font-weight: bold;
    font-size: 12px;
    font-family: 微软雅黑;
}

.search-icon {
    border: 1px solid #d6d6d6;
    background: #fff;
    padding: 10px;
    font-size: 12px;
    font-family: 微软雅黑;
    margin-bottom: 10px;
}

.search-icon table {
    width: 100%;
}

.search-icon table th {
    background: #fff;
    color: #111;
    font-weight: normal;
    width: 140px;
    text-align: right;
    padding-right: 10px;
    font-size: 12px;
    font-family: 微软雅黑;
}

.search-icon table th,
.search-icon table td {
    border-bottom: 0;
    line-height: 20px;
}

.search-icon table td {
    text-align: left;
    color: #333;
}

.search-icon-text {
    margin-top: 30px;
    margin-bottom: 15px;
    background: #fff;
}

.search-icon-text table th,
.search-icon-text table td {
    text-align: center;
    line-height: 20px;
}

.search-icon-text table td.text-right {
    text-align: right;
}

.insurance-policy-table td,
.persons-information-table td {
    min-width: 100px;
}
</style>