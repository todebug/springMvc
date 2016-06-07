var gulp = require('gulp'),
    uglify = require('gulp-uglify'),
    cleanCss = require('gulp-clean-css'),
    copy = require('gulp-contrib-copy');
var sourceFileLib = './components/lib/**/*';
var outputPathLib = '../src/main/webapp/resources/components/lib/';
var sourceFileDist = './dist/router.js';
var outputPathLibDist = '../src/main/webapp/resources/dist/';
var sourceFileHtml = './index.html';
var outputPathHtml = '../src/main/webapp/resources/';

gulp.task('begin', function() {
    console.log("哈哈哈,开始执行gulp任务了!!!");
});

gulp.task('minify', ['begin'], function() {
    gulp.src('dist/router.js')
        .pipe(uglify())
        .pipe(gulp.dest('dist'))
});

gulp.task('copyFileHtml', ['minify'], function() {
    gulp
        .src(sourceFileHtml)
        .pipe(copy())
        .pipe(gulp.dest(outputPathHtml))
});

gulp.task('copyFileDist', ['copyFileHtml'], function() {
    gulp
        .src(sourceFileLib)
        .pipe(copy())
        .pipe(gulp.dest(outputPathLib))
});

gulp.task('copyFileLib', ['copyFileDist'], function() {
    gulp
        .src(sourceFileLib)
        .pipe(copy())
        .pipe(gulp.dest(outputPathLib))
});

gulp.task('default', ['copyFileLib'], function() {
    // Your default task
    console.log("哈哈哈,结束执行gulp任务了!!!");
});
