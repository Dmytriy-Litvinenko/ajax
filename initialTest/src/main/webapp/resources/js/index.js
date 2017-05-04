'use strict';
//import * as $ from 'jquery';
//import {User} from './user';
let jQuery = require('jquery');
let User = require('./user');
let jquery = jQuery;
console.log('webpack is working!');
console.log(jquery);
console.log(User);
let b="sdsg";
alert(User.user);
goToIndexJs = () => {
    let s = "Dima";
    let u = new User(s);
    alert("hi, " + s + "" + u);
};
