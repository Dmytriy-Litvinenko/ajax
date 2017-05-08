'use strict';
import jQuery from "jquery";
import Dispatcher from "./dispatcher";
window.$ = window.jQuery = jQuery;
$(document).ready(() => {
    let dispatcher = new Dispatcher();
    dispatcher.listen();
});