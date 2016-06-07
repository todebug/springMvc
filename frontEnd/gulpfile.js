var gulp = require('gulp'),
    uglify = require('gulp-uglify'),
    copy = require('gulp-contrib-copy');
var sourceFileLib = './components/lib/**/*';
var outputPathLib = '../src/main/webapp/resources/components/lib/';
var sourceFileDist = './dist/router.js';
var outputPathLibDist = '../src/main/webapp/resources/dist/';

gulp.task('begin', function() {
    console.log("哈哈哈,开始执行gulp任务了!!!");
});

gulp.task('minify', ['begin'], function() {
    gulp.src('dist/router.js')
        .pipe(uglify())
        .pipe(gulp.dest('dist'))
});

gulp.task('copyFileDist', ['minify'], function() {
    gulp
        .src(sourceFileDist)
        .pipe(copy())
        .pipe(gulp.dest(outputPathLibDist))
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
