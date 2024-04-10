package com.example.form

/**
 * 管理者情報登録時に使用するフォーム
 */
class InsertAdministratorForm {
    /** 名前 */
    var name: String? = null
    /** メールアドレス */
    var mailAddress: String? = null
    /** パスワード */
    var password: String? = null

    override fun toString(): String {
        return "InsertAdministratorForm [name=$name, mailAddress=$mailAddress, password=$password]"
    }
}
