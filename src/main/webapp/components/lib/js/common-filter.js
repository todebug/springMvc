'use strict';
var moment=require('moment');
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

    },
    line: function(value) {
        if(null===value || value === '' || value === undefined || value === null || value === 'null'){
            return '--';
        }else{
            return value;
        }
    },
    statusLine: function(value) {
        if(null===value || value === '' || value === undefined || value === null || value === 'null'){
            return '正常';
        }else{
            return value;
        }
    },
    date: function(timestamp) {
        var result;
        timestamp = parseInt(timestamp);
        if (!timestamp){
            result = '--';
        }else{
            result = moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
        }
        return result;
    },
}
