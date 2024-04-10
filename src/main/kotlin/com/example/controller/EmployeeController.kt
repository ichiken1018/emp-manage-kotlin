package com.example.controller

import com.example.form.UpdateEmployeeForm
import com.example.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * 従業員情報を操作するコントローラ
 *
 * @author Ichiyoshikenta
 */
@Controller
@RequestMapping("/employee")
class EmployeeController {
    @Autowired
    private lateinit var employeeService: EmployeeService

    /**
     * 従業員一覧画面を出力する.
     *
     * @param model 従業員情報リストを格納したモデル
     * @return 従業員一覧画面に遷移
     */
    @GetMapping("/showList")
    fun showList(model: Model): String {
        val employeeList = employeeService.showList()
        model.addAttribute("employeeList", employeeList)
        return "list"
    }

    /**
     * 従業員詳細画面を出力する.
     *
     * @param id    リクエストパラメータで送られてくる従業員ID
     * @param model 従業員詳細情報を格納したモデル
     * @param form  従業員情報更新時に使用するフォーム
     * @return 従業員詳細画面に遷移
     */
    @GetMapping("/showDetail")
    fun showDetail(@RequestParam id: String, model: Model, form: UpdateEmployeeForm): String {
        val employee = employeeService.showDetail(id.toInt())
        model.addAttribute("employee", employee)
        return "detail"
    }

    /**
     * 従業員詳細を更新する.
     *
     * @param form 従業員情報更新時に使用するフォーム
     * @return 従業員一覧画面にリダイレクト
     */
    @PostMapping("/update")
    fun update(form: UpdateEmployeeForm): String {
        val employee = employeeService.showDetail(form.id!!.toInt())
        employee?.dependentsCount = form.dependentsCount?.toInt()
        employeeService.update(employee!!)
        return "redirect:/employee/showList"
    }
}
