'use strict';
var webpack = require('webpack');
var vue = require('vue-loader');
module.exports = {
	entry: {
		router: './vue/router.js'
	},
	output: {
		path: './dist',
		publicPath: '/dist/',
		filename: '[name].js'
	},
	module: {
		loaders: [{
			test: /\.vue$/,
			loader: vue.withLoaders({
				// apply babel transform to all javascript
				// inside *.vue files.
				js: 'babel?optional[]=runtime&loose=all'
			})
		}]
	},
	plugins: [
		new webpack.ProvidePlugin({
			'fetch': 'imports?this=>global!exports?global.fetch!whatwg-fetch',
			$: 'jquery',
			jQuery: 'jquery'
		})
	]
}

if (process.env.NODE_ENV === 'production') {
	module.exports.plugins = [
		new webpack.DefinePlugin({
			'process.env': {
				NODE_ENV: '"production"'
			}
		}),
		new webpack.optimize.UglifyJsPlugin({
			compress: {
				warnings: false
			}
		}),
		new webpack.optimize.OccurenceOrderPlugin()
	]
} else {
	module.exports.devtool = '#source-map'
}