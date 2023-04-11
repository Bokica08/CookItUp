package com.sorsix.cookitup.model

class Customer(userid:Long, firstname:String,lastname:String,username:String,email:String,password:String,
               val phoneNumber:String,val address:String)
    :User(userid,firstname,lastname,username, email, password)