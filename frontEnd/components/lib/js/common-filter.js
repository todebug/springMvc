'use strict';
var trade = [
    ['nonAutoDutyPolicyApply', '非车责任险保单提交'],
    ['tempSaveApplication', '投保单暂存'],
    ['queryActualPrice', '车辆实际价值查询'],
    ['calculatePremium', '保费计算'],
    ['noCarPolicyApply', '非车险保单提交'],
    ['noCarPolicyQuery', '非车险保单查询'],
    ['noCarEpolicyInfoReprint', '非车险电子保单补打'],
    ['queryOrderApplication', '订单状态查询'],
    ['queryPolicyInfoByVin', '按车架号查询保单信息'],
    ['personalPolicyApply', '人意险保单提交'],
    ['personalEpolicyInfoReprint', '人意险电子保单补打'],
    ['queryBizApplication', '商业险投保单查询'],
    ['queryCtpApplication', '交强险投保单查询'],
    ['queryApplicationDetail', '保单详细查询'],
    ['queryVehicleModel', '车型查询'],
    ['queryVehicleModelPF', '平台车型查询'],
    ['nonAutoPersonalPolicyApply', '非车人意险保单提交'],
    ['printDocument', '保单打印'],
    ['vehicleQueryValidation', '承保交管车辆查询校验'],
    ['printPayNotice', '打印缴款通知书'],
    ['vehicleQueryConfirm', '承保交管车辆查询确认'],
    ['submitApplication', '保单提交'],
    ['applyPayNo', '支付号申请'],
    ['printInvoice', '发票打印'],
    ['personalPolicyQuery', '人意险保单查询']

];
var region = [
    ['110000', '北京市'],
    ['120000', '天津市'],
    ['130000', '河北省'],
    ['140000', '山西省'],
    ['150000', '内蒙古自治区'],
    ['210000', '辽宁省'],
    ['220000', '吉林省'],
    ['230000', '黑龙江省'],
    ['310000', '上海市'],
    ['320000', '江苏省'],
    ['330000', '浙江省'],
    ['340000', '安徽省'],
    ['350000', '福建省'],
    ['360000', '江西省'],
    ['370000', '山东省'],
    ['410000', '河南省'],
    ['420000', '湖北省'],
    ['430000', '湖南省'],
    ['440000', '广东省'],
    ['450000', '广西壮族自治区'],
    ['460000', '海南省'],
    ['500000', '重庆市'],
    ['510000', '四川省'],
    ['520000', '贵州省'],
    ['530000', '云南省'],
    ['540000', '西藏自治区'],
    ['610000', '陕西省'],
    ['620000', '甘肃省'],
    ['630000', '青海省'],
    ['640000', '宁夏回族自治区'],
    ['650000', '新疆维吾尔自治区'],
    ['710000', '台湾省'],
    ['810000', '香港特别行政区'],
    ['820000', '澳门特别行政区']
];
module.exports = {
    insurance: function(value) {
        if (value === 'cpic') {
            return '太保财险';
        } else if (value === 'picc') {
            return '人保财险';
        } else if (value === 'gpic') {
            return '国寿财险';
        }
    },
    area: function(value) {
        var district;
        if (value.indexOf('.') !== -1) {
            district = value.substr(0, value.indexOf('.'));
        } else {
            district = value;
        }
        region.forEach(function(e) {
            if (e[0] === district) {
                district = e[1];
            }
        })
        return district;
    },
    tradeType: function(value) {
        var tradeName;
        trade.forEach(function(e) {
            if (e[0] === value) {
                tradeName = e[1];
            }
        })
        return tradeName;
    },
    parseNum: function(value) {
        var f = parseFloat(value);
        if (isNaN(f)) {
            return value;
        }
        var f = Math.round(value * 10) / 10.0;
        var s = f.toString();
        var rs = s.indexOf('.');
        if (rs < 0) {
            rs = s.length;
            s += '.';
        }
        while (s.length <= rs + 1) {
            s += '0';
        }
        return s;

    }
}
