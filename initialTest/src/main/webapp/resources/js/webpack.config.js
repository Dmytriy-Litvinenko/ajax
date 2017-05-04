/*const path = require('path');
const webpack = require('webpack');
*/
//const NODE_ENV = process.env.NODE_ENV||'development';
const webpack =require('webpack');
module.exports={
    entry: './index.js',
    output: {
        //path:'./dist',
        filename: 'bundle.js',
        library: 'index'
    },
   // watch: true,//NODE_ENV=='development',
    devtool: 'source-map',
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel-loader',
                query: {
                    presets: ['es2015', 'stage-0']
                }
            }
        ]
    }/*,
    plugins:[
        new webpack.EnvironmentPlugin('NODE_ENV')
    ]*/

    //
    /*module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loaders: ['babel-preset-es2015', 'babel-preset-stage-0']
            }
        ]
    },
    module: {
        loaders: [
            { test: /\.js$/, exclude: /node_modules/, loader: "babel-loader" }
        ]
    }*/
};
/*module.exports = {
    entry: 'main.js',
    output: {
        filename: 'bundle.js'
    }
};*/
//resolve: {extensions: ['', '.js', '.jsx', 'index.js', 'index.jsx', '.json', 'index.json']},
//var path = require('path');
/*//{ test: /\.js$/, loader: "babel" }
module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist')
    }
};
*/
/*
context: __dirname + "/app",
    entry: "./entry",
    output: {
    path: __dirname + "/dist",
        filename: "bundle.js"
}*///'use strict';