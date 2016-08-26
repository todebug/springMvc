<template>
	<div class="floatBar" v-bind:class="{ 'displayDivHide': isHide}">
		<div class="floatbar-header">
			<h3 class="floatbar-title">错误信息明细</h3>
			<button @click="closeFloatDiv" class="floatbar-close">关闭</button>
		</div>
		<div class="floatbar-body" v-show="info.iisErrorLogList.length>0">
			<table class="dataList">
				<thead>
					<tr>
						<td align="left">Id</td>
						<td>保险公司</td>
						<td>统计时间</td>
						<td align="left">交易类型</td>
						<td align="left">承保地区</td>
						<td align="left">错误来源</td>
						<td align="left">错误类型</td>
						<td align="left">错误描述</td>
						<td align="left">错误信息</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="item in info.iisErrorLogList" :item="item" id="_TR{{item.id}}">
						<td align="left" >
							<a href="javascript:void(0);" style="text-decoration: none; cursor: pointer;" @click="getLogDetail">{{item.id}}</a>
						</td>
						<td>
							{{item.insurer | insurance}}
						</td>
						<td>
							{{item.period | date}}
						</td>
						<td align="left">
							{{item.interfaceType | tradeType | line}}
						</td>
						<td align="left">
							{{item.province | area | line}}
						</td>
						<td align="left">
							{{item.errorSource | errorSource}}
						</td>
						<td align="left">
							{{item.errorType| errorType}}
						</td>
						<td align="left" style="width:200;">
							<p class="ellipsis-p" title="{{item.errorDesc}}" style="width:200;">
								{{item.errorDesc | line}}
							</p>
						</td>
						<td align="left" style="width:200;">
							<p class="ellipsis-p" title="{{item.errorMessage}}" style="width:200;">
								{{item.errorMessage | line}}
							</p>
						</td>
					</tr>
					<tr id="showTr" style='display:none'>
						<td colspan="10">
							<show-Tr :show="show"></show-Tr>
						</td>
					</tr>
				</tbody>
			</table>
			<page-bar-sub></page-bar-sub>
		</div>
		<div class="floatbar-body" v-show="info.iisErrorLogList.length===0">
			暂无接口交互数据!
		</div>
		<div class="floatbar-footer"></div>	
	</div>
</template>

<script>
'use strict';
var config = require('../../../lib/js/config');
var isHide=true;
var info = {
	iisErrorLogList: [],
	total: ''
}
var show={
	res: {},
	req: {},
	interfaceLogData: {}
}
var showFlag = {
	flag: true,
	id: -1
};
var provincesFilter = [];
var interfaceTypeFilter = [];
var errorTypeFilter = [];
var errorSourceFilter = [];
var queryData = {
	insurer: '',
	period: '',
	periodType: '',
	interfaceType: '',
	province: '',
	errorSource: '',
	errorType: '',
	errorDesc: '',
	periodBegin: null,
	periodEnd: null,
	page: 1
};
var pageDataSub = {
	cur: 0,
	all: 0
}
module.exports= {
	data: function() {
	    	return  {isHide,info,show,showFlag,provincesFilter,interfaceTypeFilter,errorTypeFilter,errorSourceFilter,queryData,pageDataSub}
	},            
	created: function() {
                    	this.initQueryData();
            },
      	methods: {
                        initQueryData: function() {
                   		this.isHide=true;
                   		this.info.iisErrorLogList = [];
                   		this.info.total = '';
                   		this.queryData.insurer = '';
			this.queryData.period = '';
			this.queryData.periodType = '';
			this.queryData.interfaceType = '';
			this.queryData.province = '';
			this.queryData.errorSource = '';
			this.queryData.errorType = '';
			this.queryData.errorDesc = '';
			this.queryData.periodBegin = null;
			this.queryData.periodEnd = null;
			this.queryData.page = 1;
                   		this.show.res = {};
			this.show.req = {};
			this.show.interfaceLogData = {};
			this.showFlag.flag=true;
			this.showFlag.id=-1;
			this.pageDataSub.cur=0;
                        	this.pageDataSub.all=0;
                        	this.provincesFilter = [];
			this.interfaceTypeFilter = [];
			this.errorTypeFilter = [];
			this.errorSourceFilter = [];
                        },
                        closeFloatDiv: function() {
                        	$('.trld').remove();
                        	this.isHide=true;
                        },
                        getPageData: function(total, page) {
                        	this.pageDataSub.cur=page;
                        	this.pageDataSub.all=total;
                        	this.$broadcast('broadcast-floatBar-pageBarSub-getPageData', this.pageDataSub);
                        },
                        getIisErrorLogList: function(queryData) {
                        	var _this = this;
	            	var url = config.host+'statistic/addFollowUpInfo';
			// var result = fetch(url,{
			// 	method: 'post',
			// 	mode: 'cors',	
			// 	headers: {
			// 		'Accept': 'application/json',
			// 		'Content-Type': 'application/json'
			// 	},
			// 	body: JSON.stringify({
		 // 		    	'insurer': queryData.insurer,
		 // 		    	'period': queryData.period,
		 // 		    	'periodType': queryData.periodType,
		 // 		    	'interfaceType': queryData.interfaceType,
		 // 		    	'province': queryData.province,
		 // 		    	'errorSource': queryData.errorSource,
		 // 		    	'errorType': queryData.errorType,
 		//    		    	'errorDesc': queryData.errorDesc,
 		//    		    	'periodBegin': queryData.periodBegin,
 		//    		    	'periodEnd': queryData.periodEnd,
 		//    		    	'page': queryData.page, 
		 // 		    	'size': 20
			// 	})
			// });
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
				var j = {"total":1,"iisErrorLogList":[{"id":640419,"period":1469525400000,"insurer":"picc","province":"530000","interfaceType":"calculatePremium","errorSource":"INSURER_ERROR","errorType":"INSURER_ERROR","errorDesc":"销售费用分户信息无满足条件的数据","createTime":1469525579000,"errorMessage":"交易订单号为：1469525418804的保费计算失败，定价返回的错误信息：D0101:销售费用分户信息无满足条件的数据，请联系车险部确认。对应视图为53000000DZA，2版本。"}]};
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.info = Object.assign(_this.info, j);
				_this.getPageData(_this.info.total, queryData.page);
				_this.$nextTick(function() {
			  		_this.isHide=false;
			  	});
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
                        },
                        getLogDetail: function(event) {
			var logId = event.target.innerHTML;
			if(showFlag.flag===false && showFlag.id===logId){
				showFlag.flag = true;
				$('.trld').remove();
				return;
			}
			var _this = this;
			$('.trld').remove();		
			this.handleInfoShowData(logId);
			this.getLogDetailInfo(logId);
		  	showFlag.flag = false;	
		  	showFlag.id = logId;
		},
		handleInfoShowData: function(logId) {
			var _this = this;
			if(this.info.iisErrorLogList !==null && this.info.iisErrorLogList.length != 0){
				$.each(this.info.iisErrorLogList, function(i,val){    
				      if(val.id===Number(logId)){
				      	_this.show = Object.assign(_this.show, val);
				      }
				}); 
			}
		},
		getLogDetailInfo: function(logId) {
			var _this = this;
	            	var url = config.host+'statistic/addFollowUpInfo';
			// var result = fetch(url,{
			// 	method: 'post',
			// 	mode: 'cors',
			// 	headers: {
			// 		'Accept': 'application/json',
			// 		'Content-Type': 'application/json'
			// 	},
			// 	body: JSON.stringify({
			// 	 	'id': Number(logId),
			// 	 	'errorFLag': true
			// 	})
			// });
			// result.then(function(response) {
			// 	return response.json();
			// }).then(function(j) {
				var j = {"res":{"messageId":null,"logid":640419,"serialNo":null,"messageType":null,"content1":"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n  <soapenv:Header>\n    <pub:responsehead xmlns:pub=\"http://pub.webservice.cmp.com\">\n      <pub:request_type>Q01</pub:request_type>\n      <pub:uuid>1469525418804OMzDNfTr</pub:uuid>\n      <pub:sender>0531</pub:sender>\n      <pub:server_version>00000000</pub:server_version>\n      <pub:response_code>0</pub:response_code>\n      <pub:error_message>定价返回的错误信息：D0101:销售费用分户信息无满足条件的数据，请联系车险部确认。对应视图为53000000DZA，2版本。</pub:error_message>\n      <pub:timestamp/>\n    </pub:responsehead>\n  </soapenv:Header>\n  <soap:Body xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n    <pan:TEMPSTORAGERTN xmlns:pan=\"http://pan.prpall.webservice.cmp.com\">\n      <pan:BIZ_ENTITY/>\n    </pan:TEMPSTORAGERTN>\n  </soap:Body>\n</soapenv:Envelope>\n","content2":null,"content3":null,"content4":null,"createTime":null},"interfaceLogData":{"id":640419,"interfaceType":"calculatePremium","orderNo":"1469525418804","plateNo":"null","vin":"LSGAR55L7FH001076","insured":"张三","applicationNo":"null","requestNo":"null","responseNo":"null","queryNo":"null","confirmNo":"null","insurer":"picc","province":"530000","requestTime":1469525419000,"responseTime":1469525423000,"status":"失败","errorMessage":"交易订单号为：1469525418804的保费计算失败，定价返回的错误信息：D0101:销售费用分户信息无满足条件的数据，请联系车险部确认。对应视图为53000000DZA，2版本。","dealerCode":"VW530000","dealerName":"云南测试经销商"},"req":{"messageId":null,"logid":640419,"serialNo":null,"messageType":null,"content1":"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">  \n  <soap:Header xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">  \n    <nshead:requesthead xmlns:nshead=\"http://pub.webservice.cmp.com\">  \n      <nshead:request_type>Q01</nshead:request_type>  \n      <nshead:uuid>1469525418804OMzDNfTr</nshead:uuid>  \n      <nshead:server_version>00000000</nshead:server_version>  \n      <nshead:sender>0531</nshead:sender>  \n      <nshead:user>0531</nshead:user>  \n      <nshead:password>0531</nshead:password>  \n      <nshead:areacode>yntest</nshead:areacode>  \n      <nshead:ChnlNo>saic</nshead:ChnlNo>  \n      <nshead:flowintime>2016-07-26 17:30:19</nshead:flowintime> \n    </nshead:requesthead> \n  </soap:Header>  \n  <soapenv:Body> \n    <pan:TEMPSTORAGEREQ xmlns:pan=\"http://pan.prpall.webservice.cmp.com\">  \n      <pan:BIZ_ENTITY> \n        <CarQuoteGenReq> \n          <BIZORIGIN>3</BIZORIGIN>  \n          <CHANNELCODE>53003H100297</CHANNELCODE>  \n          <COMCODE>53000000</COMCODE>  \n          <LASTBIZPOLICYNO/>  \n          <LASTCIPOLICYNO/>  \n          <LICENSENO>暂未上牌</LICENSENO>  \n          <LICENSETYPE>02</LICENSETYPE>  \n          <LICENSECOLORCODE>01</LICENSECOLORCODE>  \n          <CARKINDCODE>A01</CARKINDCODE>  \n          <ENGINENO>LS815485</ENGINENO>  \n          <FRAMENO>LSGAR55L7FH001076</FRAMENO>  \n          <VINNO>LSGAR55L7FH001076</VINNO>  \n          <ENROLLDATE>2016-06-05</ENROLLDATE>  \n          <CLAUSETYPE>F42</CLAUSETYPE>  \n          <USENATURECODE>211</USENATURECODE>  \n          <RUNAREACODE>11</RUNAREACODE>  \n          <MODELCODE>BKAAID0011</MODELCODE>  \n          <BRANDNAME>别克SGM7301GS轿车</BRANDNAME>  \n          <MAKEDATE/>  \n          <COUNTRYNATURE>B</COUNTRYNATURE>  \n          <CURRENCY>CNY</CURRENCY>  \n          <CHGOWNERFLAG>0</CHGOWNERFLAG>  \n          <CHGOWNERDATE/>  \n          <LOANVEHICLEFLAG>0</LOANVEHICLEFLAG>  \n          <OPERATEDATE>2016-07-26</OPERATEDATE>  \n          <CARINSUREDRELATION>1</CARINSUREDRELATION>  \n          <VEHICLE_BRAND/>  \n          <LICENSEFLAG>0</LICENSEFLAG>  \n          <STARTDATEBI>2016-07-27</STARTDATEBI>  \n          <STARTHOURBI>00</STARTHOURBI>  \n          <ENDDATEBI>2017-07-26</ENDDATEBI>  \n          <ENDHOURBI>24</ENDHOURBI>  \n          <STARTDATECI>2016-07-27</STARTDATECI>  \n          <STARTHOURCI>00</STARTHOURCI>  \n          <ENDDATECI>2017-07-26</ENDDATECI>  \n          <ENDHOURCI>24</ENDHOURCI>  \n          <RENEWALFLAG>03</RENEWALFLAG>  \n          <AGENTCODE>53003H100297</AGENTCODE>  \n          <NODAMAGEYEARS/>  \n          <LASTDAMAGEDBI>0</LASTDAMAGEDBI>  \n          <THISDAMAGEDBI>0</THISDAMAGEDBI>  \n          <NODAMYEARSBI>0</NODAMYEARSBI>  \n          <LASTDAMAGED>0</LASTDAMAGED>  \n          <RATERLOATFLAG>0</RATERLOATFLAG>  \n          <NODAMYEARSCI>0</NODAMYEARSCI>  \n          <LASTDAMAGEDCI>0</LASTDAMAGEDCI>  \n          <THISDAMAGEDCI>0</THISDAMAGEDCI>  \n          <REMARK1/>  \n          <REMARK2/>  \n          <NODAMAGEYEARSBI>0</NODAMAGEYEARSBI>  \n          <GROUPCODE/>  \n          <UNITTYPE/>  \n          <VEHICLETYPEDESCRIPTION/>  \n          <TAXPAYERTYPE/>  \n          <DRIVLICENSECODE/>  \n          <FUEL_TYPE/>  \n          <CERTIFICATE_TYPE/>  \n          <CERTIFICATE_NO/>  \n          <RENEWAL_TYPE>0</RENEWAL_TYPE>  \n          <AGENTTYPE/>  \n          <EXT2/>  \n          <EXT3/>  \n          <PROJECTCODE/>  \n          <PROFIT_OPERATEMODE/>  \n          <MONOPOLYCODE>5301001702024</MONOPOLYCODE>  \n          <MONOPOLYFLAG>1</MONOPOLYFLAG>  \n          <MONOPOLYNAME>云南中致远汽车销售有限公司</MONOPOLYNAME>  \n          <RESOURCES>0531</RESOURCES>  \n          <handler1code_uni/>  \n          <handlercode_uni/>  \n          <QUERYAREA>530000</QUERYAREA>  \n          <CarQuoteCarShipTax> \n            <TAXTYPE>1</TAXTYPE>  \n            <TAXABATETYPE/>  \n            <TAXABATEREASON/>  \n            <CALCULATEMODE>C2</CALCULATEMODE>  \n            <TAXPAYERIDENTNO>110221198401013131</TAXPAYERIDENTNO>  \n            <TAXPAYERNUMBER/>  \n            <TAXPAYERCODE>1</TAXPAYERCODE>  \n            <TAXPAYERNAME>张三</TAXPAYERNAME>  \n            <TAXPAYERNATURE>3</TAXPAYERNATURE>  \n            <TAXCOMCODE/>  \n            <TAXCOMNAME/>  \n            <DUTYPAIDPROOFNO/>  \n            <TAXPRINTPROOFNO/>  \n            <TAXUNITAMOUNT/>  \n            <PREPAYTAXYEAR/>  \n            <PREPOLICYENDDATE/>  \n            <CURRENCY>CNY</CURRENCY>  \n            <TAXUNIT/>  \n            <THISPAYTAX/>  \n            <PREPAYTAX/>  \n            <DELAYPAYTAX/>  \n            <SUMPAYTAX/>  \n            <TAXPAYERNO/>  \n            <PAYSTARTDATE>2016-06-05</PAYSTARTDATE>  \n            <PAYENDDATE>2016-12-31</PAYENDDATE> \n          </CarQuoteCarShipTax>  \n          <CarQuoteCarDeviceList/>  \n          <CarQuoteItemKindBIList> \n            <CarQuoteItemKindBI> \n              <KINDCODE>050930</KINDCODE>  \n              <KINDNAME>不计免赔险（车损险）</KINDNAME>  \n              <ITEMKINDNO>1</ITEMKINDNO>  \n              <AMOUNT/>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>2000000</FLAG>  \n              <CALCULATEFLAG>N</CALCULATEFLAG> \n            </CarQuoteItemKindBI>  \n            <CarQuoteItemKindBI> \n              <KINDCODE>050501</KINDCODE>  \n              <KINDNAME>盗抢险</KINDNAME>  \n              <ITEMKINDNO>2</ITEMKINDNO>  \n              <AMOUNT/>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>1001000</FLAG>  \n              <CALCULATEFLAG>N</CALCULATEFLAG> \n            </CarQuoteItemKindBI>  \n            <CarQuoteItemKindBI> \n              <KINDCODE>050202</KINDCODE>  \n              <KINDNAME>机动车损失保险</KINDNAME>  \n              <ITEMKINDNO>3</ITEMKINDNO>  \n              <AMOUNT/>  \n              <DEDUCTIBLE>0</DEDUCTIBLE>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>1001000</FLAG>  \n              <CALCULATEFLAG>Y</CALCULATEFLAG> \n            </CarQuoteItemKindBI>  \n            <CarQuoteItemKindBI> \n              <KINDCODE>050931</KINDCODE>  \n              <KINDNAME>不计免赔险（三者险）</KINDNAME>  \n              <ITEMKINDNO>4</ITEMKINDNO>  \n              <AMOUNT/>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>2000000</FLAG>  \n              <CALCULATEFLAG>N</CALCULATEFLAG> \n            </CarQuoteItemKindBI>  \n            <CarQuoteItemKindBI> \n              <KINDCODE>050932</KINDCODE>  \n              <KINDNAME>不计免赔险（盗抢险）</KINDNAME>  \n              <ITEMKINDNO>5</ITEMKINDNO>  \n              <AMOUNT/>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>2000000</FLAG>  \n              <CALCULATEFLAG>N</CALCULATEFLAG> \n            </CarQuoteItemKindBI>  \n            <CarQuoteItemKindBI> \n              <KINDCODE>050602</KINDCODE>  \n              <KINDNAME>第三者责任保险</KINDNAME>  \n              <ITEMKINDNO>6</ITEMKINDNO>  \n              <AMOUNT>500000.00</AMOUNT>  \n              <UNITAMOUNT/>  \n              <QUANTITY/>  \n              <FLAG>1001000</FLAG>  \n              <CALCULATEFLAG>Y</CALCULATEFLAG> \n            </CarQuoteItemKindBI> \n          </CarQuoteItemKindBIList>  \n          <CarQuoteBasePartBIReq> \n            <QUERYSEUQENCENO/>  \n            <ANSWER/>  \n            <ISRENEWALFLAG>0</ISRENEWALFLAG> \n          </CarQuoteBasePartBIReq>  \n          <CarQuoteItemKindCIList> \n            <CarQuoteItemKindCI> \n              <KINDNAME>机动车强制责任保险</KINDNAME>  \n              <ITEMKINDNO>1</ITEMKINDNO>  \n              <KINDCODE>050100</KINDCODE>  \n              <AMOUNT>122000</AMOUNT>  \n              <UNITAMOUNT>122000</UNITAMOUNT>  \n              <CALCULATEFLAG>Y</CALCULATEFLAG> \n            </CarQuoteItemKindCI> \n          </CarQuoteItemKindCIList>  \n          <CarQuoteBasePartCIReq> \n            <QUERYSEUQENCENO/>  \n            <ANSWER/>  \n            <ISRENEWALFLAG>0</ISRENEWALFLAG> \n          </CarQuoteBasePartCIReq>  \n          <CarQuoteInsuredIndivList> \n            <CarQuoteInsuredIndiv> \n              <SERIALNO>1</SERIALNO>  \n              <INSUREDFLAG>1110100</INSUREDFLAG>  \n              <INSUREDCODE>1</INSUREDCODE>  \n              <INSUREDNAME>张三</INSUREDNAME>  \n              <INSUREDADDRESS>上海市静安区长寿路1111号30楼</INSUREDADDRESS>  \n              <INSUREDNATURE>1</INSUREDNATURE>  \n              <IDENTIFYTYPE>01</IDENTIFYTYPE>  \n              <IDENTIFYNUMBER>110221198401013131</IDENTIFYNUMBER>  \n              <MOBILE>13800138000</MOBILE>  \n              <AUDITSTATUS>2</AUDITSTATUS>  \n              <VERSIONNO>1</VERSIONNO>  \n              <SEX/>  \n              <COUNTRYCODE>CHN</COUNTRYCODE>  \n              <RESIDENT>A</RESIDENT> \n            </CarQuoteInsuredIndiv> \n          </CarQuoteInsuredIndivList> \n        </CarQuoteGenReq> \n      </pan:BIZ_ENTITY> \n    </pan:TEMPSTORAGEREQ> \n  </soapenv:Body> \n</soapenv:Envelope>\n","content2":null,"content3":null,"content4":null,"createTime":null}};
				//console.log(JSON.parse(JSON.stringify(j)));
				_this.show = Object.assign(_this.show, j);
				_this.$nextTick(function() {
			  		$('#_TR'+logId).after($('#showTr').clone().show().addClass('trld'));
			  	});
			// }).catch(function(ex) {
			// 	console.log('failed', ex)
			// });
		} 
            },
	events: {
		'broadcast-dataList-floatBar-dataEchart-querydata': function(queryPieData) {
			this.queryData = Object.assign(this.queryData, queryPieData);
			this.getIisErrorLogList(this.queryData);
		},
		'dispatch--pageBarSub-floatBar-closeTr': function(closeTr) {
			$('.trld').remove();
		},
		'dispatch-pageBarSub-floatBar-curPage': function(curPage) {
			this.queryData.page = curPage;
			this.getIisErrorLogList(this.queryData);
		},
		'broadcast-dataList-floatBar-queryId': function(query,periodType,page) {
			var data = query.split('|||');
			this.queryData.periodType = periodType;
			this.queryData.insurer = data[0];
			this.queryData.period = data[1];
			this.queryData.interfaceType = data[2];
			this.queryData.province = data[3];
			this.queryData.errorSource = data[4];
			this.queryData.errorType = data[5];
			this.queryData.errorDesc = data[7];
			this.queryData.periodBegin = null;
			this.queryData.periodEnd = null;
			this.queryData.page = page;
			this.getIisErrorLogList(this.queryData);
		},
		'broadcast-dataList-floatBar-filterData': function(provinces,interfaceType,errorType,errorSource) {
			var _this = this;
			if(_this.provincesFilter.length===0){
				provinces.forEach(function(e) {
					_this.provincesFilter.push(e);
				});
			}
			if(_this.interfaceTypeFilter.length===0){
				interfaceType.forEach(function(e) {
					_this.interfaceTypeFilter.push(e);
				});
			}
			if(_this.errorTypeFilter.length===0){
				errorType.forEach(function(e) {
					_this.errorTypeFilter.push(e);
				});
			}
			if(_this.errorSourceFilter.length===0){
				errorSource.forEach(function(e) {
					_this.errorSourceFilter.push(e);
				});
			}
		}
	},
	filters: {
                  	area: function(value) {
		        value = $.trim(value);
		        var district = null;
		        if (value.indexOf('.') !== -1) {
		            district = value.substr(0, value.indexOf('.'));
		        } else {
		            district = value;
		        }
		        this.provincesFilter.forEach(function(e) {
		            if (e.name === district) {
		                district = e.value;
		            }
		        })
		        if(district === null){
		            district = value;
		        }
		        return district;
	    	},
	    	tradeType: function(value) {
		        value = $.trim(value);
		        var tradeName = null;
		        this.interfaceTypeFilter.forEach(function(e) {
		            if (e.name === value) {
		                tradeName = e.value;
		            }
		        })
		        if(tradeName === null){
		            tradeName =value;
		        }
		        return tradeName;
	    	},
	    	errorType: function(value) {
		        value = $.trim(value);
		        var errorType = null;
		        this.errorTypeFilter.forEach(function(e) {
		            if ($.trim(e.name)  === value) {//车型错误去空格...
		                errorType = e.value;
		            }
		        })
		        if(errorType === null){
		            errorType = value;
		        }
		        return errorType;
    		},
    		errorSource: function(value) {
		        value = $.trim(value);
		        var errorSource = null;
		        this.errorSourceFilter.forEach(function(e) {
		            if (e.name  === value) {
		                errorSource = e.value;
		            }
		        })
		        if(errorSource === null){
		            errorSource = value;
		        }
		        return errorSource;
    		}
       	 },
	components: {
		showTr: require ('./showTr.vue'),
		pageBarSub: require ('./pageBarSub.vue'),
	}
}
</script>

<style type="text/css">
.floatBar {
	position: fixed;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	z-index: 10000;
	background: #fff;
	border: 1px #ccc solid;
	overflow: auto;
}
.floatbar-header {
	height: 40px;
	width: 100%;
	padding: 0 20px;
	border-bottom: 1px #ccc solid;
	background: #f7f7f7;
	margin-bottom: 10px;
}
.floatbar-header .floatbar-title {
	float: left;
	line-height: 40px;
	font-size: 16px;
	margin: 0;
}

.floatbar-header  .floatbar-close{
	float: right;
	padding: 2px 10px;
	border: 1px #ccc solid;
	margin-top: 6px;
	background: #fff;
	border-radius: 3px;
}

.displayDivHide {
	display: none;
}

</style>