package com.example.service

import com.example.domain.Employee
import com.example.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 従業員情報を操作するサービス
 *
 * @author IchiyoshiKenta
 */
@Service
@Transactional
class EmployeeService {
    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    /**
     * 従業員情報を全件取得する.
     *
     * @return 従業員情報一覧
     */
    fun showList(): List<Employee> {
        return employeeRepository.findAll()
    }

    /**
     * 従業員情報を取得する.
     *
     * @param id ID
     * @return 従業員情報
     */
    fun showDetail(id: Int): Employee? {
        return employeeRepository.load(id)
    }

    fun update(employee: Employee) {
        employeeRepository.update(employee)
    }
}
