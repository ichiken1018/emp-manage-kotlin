package com.example.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository
import java.sql.ResultSet

import com.example.domain.Employee

/**
 * employeesテーブルを操作するリポジトリ
 *
 */
@Repository
class EmployeeRepository {

    /**
     * Employeeオブジェクトを生成するRowMapper
     */
    private val EMPLOYEE_ROW_MAPPER = { rs: ResultSet, _: Int ->
        Employee().apply {
            id = rs.getInt("id")
            name = rs.getString("name")
            image = rs.getString("image")
            gender = rs.getString("gender")
            hireDate = rs.getDate("hire_date")
            mailAddress = rs.getString("mail_address")
            zipCode = rs.getString("zip_code")
            address = rs.getString("address")
            telephone = rs.getString("telephone")
            salary = rs.getInt("salary")
            characteristics = rs.getString("characteristics")
            dependentsCount = rs.getInt("dependents_count")
        }
    }

    @Autowired
    private lateinit var template: NamedParameterJdbcTemplate

    /**
     * 従業員一覧を入社日(降順)で取得.
     *
     * @return 全従業員情報(存在しない場合は0件を返す)
     */
    fun findAll(): List<Employee> {
        val sql = StringBuilder()
        sql.append("SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, ")
        sql.append("telephone, salary, characteristics, dependents_count ")
        sql.append("FROM employees ORDER BY hire_date DESC;")
        return template.query(sql.toString(), EMPLOYEE_ROW_MAPPER)
    }

    /**
     * 主キーから従業員を取得する.
     *
     * @param id　検索された従業員ID
     * @return 取得された従業員情報
     * @exception 従業員が存在しない場合例外発生させる
     */
    fun load(id: Int): Employee? {
        val sql = StringBuilder()
        sql.append("SELECT id, name, image, gender, hire_date, mail_address, zip_code, ")
        sql.append("address, telephone, salary, characteristics, dependents_count ")
        sql.append("FROM employees WHERE id=:id;")
        val param: SqlParameterSource = MapSqlParameterSource().addValue("id", id)

        return try {
            template.queryForObject(sql.toString(), param, EMPLOYEE_ROW_MAPPER)
        } catch (e: Exception) {
            println("例外が発生")
            null
        }
    }

    /**
     * 従業員情報を変更する.
     */
    fun update(employee: Employee) {
        val param: SqlParameterSource = BeanPropertySqlParameterSource(employee)
        val sql = StringBuilder()
        sql.append("UPDATE employees SET ")
        sql.append("name=:name, image=:image, gender=:gender, hire_date=:hireDate, ")
        sql.append("mail_address=:mailAddress, zip_code=:zipCode, address=:address, ")
        sql.append("telephone=:telephone, salary=:salary, characteristics=:characteristics, ")
        sql.append("dependents_count=:dependentsCount WHERE id=:id;")
        template.update(sql.toString(), param)
    }
}
