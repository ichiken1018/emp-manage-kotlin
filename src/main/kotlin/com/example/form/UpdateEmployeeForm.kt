package com.example.form

/**
 * 従業員情報を更新するフォーム.
 *
 * @author IchiyoshiKenta
 */
class UpdateEmployeeForm {
    /** id */
    var id: String? = null
    /** 扶養人数 */
    var dependentsCount: String? = null

    override fun toString(): String {
        return "UpdateEmployeeForm [id=$id, dependentsCount=$dependentsCount]"
    }
}
