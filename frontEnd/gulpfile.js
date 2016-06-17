var gulp = require('gulp'),
    shell = require('gulp-shell'),
    uglify = require('gulp-uglify'),
    cleanCss = require('gulp-clean-css'),
    copy = require('gulp-contrib-copy');
var sourceFileLib = './components/lib/**/*';
var outputPathLib = '../src/main/webapp/components/lib/';
var sourceFileDist = './dist/router.js';
var outputPathDist = '../src/main/webapp/dist/';
var sourceFileHtml = './index.html';
var outputPathHtml = '../src/main/webapp/';
var devConfig = './components/lib/js/dev/config.js';
var prodConfig = './components/lib/js/prod/config.js';
var hostConfg = './components/lib/js/';

gulp.task('begin', function() {
    console.log("哈哈哈,开始执行gulp任务了!!!");
});

//先设置生产配置地址
gulp.task('setProdConfig', ['begin'], function() {
    gulp
        .src(prodConfig)
        .pipe(copy())
        .pipe(gulp.dest(hostConfg))
});

//vue打包处理
gulp.task('webpack', ['setProdConfig'], shell.task([
    'webpack -p'
]));

//压缩vueJS  实际貌似没有webpack -p压缩力度大,故先注释,待测试
// gulp.task('minify', ['webpack'], function() {
//     gulp
//         .src('dist/router.js')
//         .pipe(uglify())
//         .pipe(gulp.dest('dist'))
// });


//copy文件 index.html
gulp.task('copyFileHtml', ['webpack'], function() {
    gulp
        .src(sourceFileHtml)
        .pipe(copy())
        .pipe(gulp.dest(outputPathHtml))
});

//copy文件夹 dist
gulp.task('copyFileDist', ['copyFileHtml'], function() {
    gulp
        .src(sourceFileDist)
        .pipe(copy())
        .pipe(gulp.dest(outputPathDist))
});

//copy文件夹 lib
gulp.task('copyFileLib', ['copyFileDist'], function() {
    gulp
        .src(sourceFileLib)
        .pipe(copy())
        .pipe(gulp.dest(outputPathLib))
});

//最后将生产地址设置成开发配置地址
gulp.task('setDevConfig', ['copyFileLib'], function() {
    gulp
        .src(devConfig)
        .pipe(copy())
        .pipe(gulp.dest(hostConfg))
});

gulp.task('default', ['setDevConfig'], function() {
    // Your default task
    console.log("哈哈哈,结束执行gulp任务了!!!");
});
