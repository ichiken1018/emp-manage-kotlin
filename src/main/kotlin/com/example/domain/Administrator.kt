package com.example.domain

class Administrator() {
    //管理者ID
    var id:Int?=null
    //管理者名
    var name:String?=null
    //メールアドレス
    var mailAddress:String?= null
    //パスワード
    var password:String?=null

    //コンストラクタ
    constructor(id:Int?,name:String?,mailAddress:String?,password:String?) : this() {
     this.id = id
     this.name = name
     this.mailAddress = mailAddress
     this.password = password
    }

    override fun toString(): String {
        return "Administrator [id=$id, name=$name, mailAddress=$mailAddress, password=$password]"
    }

}