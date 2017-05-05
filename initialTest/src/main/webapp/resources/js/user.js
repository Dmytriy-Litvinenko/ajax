/*function User(name) {
    this.name=name;
}

User.prototype.getName= function(){
    return this.name;
};
exports.user = User;*/
/*"use strict";
export default class User{
    constructor(name){
        this.name=name;
    }
}*/
'use strict';

class User {
    constructor(){
        this.name='Dima';
    }

    setName(name) {
        this.name = name;
    }
    getName(){
        return this.name;
    }
}

module.exports = User;