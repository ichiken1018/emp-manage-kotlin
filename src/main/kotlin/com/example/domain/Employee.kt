package com.example.domain

import java.util.*

class Employee {
    /** ID */
    var id: Int? = null
    /** 名前 */
    var name: String? = null
    /** 画像 */
    var image: String? = null
    /** 性別 */
    var gender: String? = null
    /** 入社日 */
    var hireDate: Date? = null
    /** メールアドレス */
    var mailAddress: String? = null
    /** 郵便番号 */
    var zipCode: String? = null
    /** 住所 */
    var address: String? = null
    /** 電話番号 */
    var telephone: String? = null
    /** 給料 */
    var salary: Int? = null
    /** 特性 */
    var characteristics: String? = null
    /** 扶養人数 */
    var dependentsCount: Int? = null

    /**
     * 引数なしのコンストラクタ.
     */
    constructor()

    /**
     * 引数ありのコンストラクタ
     *
     * @param id              ID
     * @param name            従業員名
     * @param image           画像
     * @param gender          性別
     * @param hireDate        入社日
     * @param mailAddress     メールアドレス
     * @param zipCode         郵便番号
     * @param address         住所
     * @param telephone       電話番号
     * @param salary          給料
     * @param characteristics 特性
     * @param dependentsCount 扶養人数
     */
    constructor(
        id: Int?,
        name: String?,
        image: String?,
        gender: String?,
        hireDate: Date?,
        mailAddress: String?,
        zipCode: String?,
        address: String?,
        telephone: String?,
        salary: Int?,
        characteristics: String?,
        dependentsCount: Int?
    ) {
        this.id = id
        this.name = name
        this.image = image
        this.gender = gender
        this.hireDate = hireDate
        this.mailAddress = mailAddress
        this.zipCode = zipCode
        this.address = address
        this.telephone = telephone
        this.salary = salary
        this.characteristics = characteristics
        this.dependentsCount = dependentsCount
    }

    override fun toString(): String {
        return "Employee [id=$id, name=$name, image=$image, gender=$gender, hireDate=$hireDate, mailAddress=$mailAddress, zipCode=$zipCode, address=$address, telephone=$telephone, salary=$salary, characteristics=$characteristics, dependentsCount=$dependentsCount]"
    }
}