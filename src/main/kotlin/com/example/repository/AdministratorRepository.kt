package com.example.repository

import com.example.domain.Administrator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class AdministratorRepository() {
    /**
     * Administratorオブジェクトを生成するRowMapper
     */
    private val ADMINISTRATOR_ROW_MAPPER = { rs: ResultSet, _: Int ->
        Administrator().apply {
            id = rs.getInt("id")
            name = rs.getString("name")
            mailAddress = rs.getString("mail_address")
            password = rs.getString("password")
        }
    }

    @Autowired
    private lateinit var template: NamedParameterJdbcTemplate

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    fun insert(administrator: Administrator) {
        val param: SqlParameterSource = BeanPropertySqlParameterSource(administrator)

        val insertSql = StringBuilder()
        insertSql.append("INSERT INTO administrators(name, mail_address, password) ")
        insertSql.append("VALUES(:name, :mailAddress, :password);")
        template.update(insertSql.toString(), param)
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return 取得された管理者情報(存在しない場合はnull)
     */
    fun findByMailAddressAndPassword(mailAddress: String, password: String): Administrator? {
        val sql = StringBuilder()
        sql.append("SELECT id, name, mail_address, password ")
        sql.append("FROM administrators ")
        sql.append("WHERE mail_address=:mailAddress AND password=:password;")
        val param: SqlParameterSource = MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password)
        val administratorList = template.query(sql.toString(), param, ADMINISTRATOR_ROW_MAPPER)

        return if(administratorList.isEmpty()){
            null
        }else{
            administratorList[0]
        }
    }
}