package com.example.form

/**
 * ログイン時に使用するフォーム
 *
 * @author IchiyoshiKenta
 */
class LoginForm {
    /** メールアドレス */
    var mailAddress: String? = null

    /** パスワード */
    var password: String? = null

    override fun toString(): String {
        return "LoginForm [mailAddress=$mailAddress, password=$password]"
    }
}
