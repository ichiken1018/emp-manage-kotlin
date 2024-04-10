package com.example.controller

import com.example.domain.Administrator
import com.example.form.InsertAdministratorForm
import com.example.form.LoginForm
import com.example.service.AdministratorService
import jakarta.servlet.http.HttpSession
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * 管理者情報を操作するコントローラ.
 *
 * @author IchiyoshiKenta
 */
@Controller
@RequestMapping("/")
class AdministratorController {

    @Autowired
    private lateinit var administratorService: AdministratorService

    /**
     * 管理者情報登録画面に遷移する.
     *
     * @param form 管理者情報登録フォーム
     * @return 管理者登録画面に遷移
     */
    @GetMapping("/toInsert")
    fun toInsert(form: InsertAdministratorForm): String {
        return "insert"
    }

    /**
     * 管理者情報を登録する処理
     *
     * @param form 管理者情報登録フォーム
     * @return ログイン画面にリダイレクト
     */
    @PostMapping("/insert")
    fun insert(form: InsertAdministratorForm): String {
        val administrator = Administrator()
        BeanUtils.copyProperties(form, administrator)
        administratorService.insert(administrator)
        return "redirect:/"
    }

    /**
     * ログイン画面を出力
     *
     * @param form ログインフォーム
     * @return ログイン画面に遷移
     */
    @GetMapping("/")
    fun toLogin(form: LoginForm): String {
        return "login"
    }

    @Autowired
    private lateinit var session: HttpSession

    /**
     * ログイン操作.
     *
     * @param form  ログインフォーム
     * @param model エラー情報格納したモデル
     * @return ログイン後の従業員一覧画面に遷移
     */
    @PostMapping("/login")
    fun login(form: LoginForm, model: Model): String {
        val loginResult = administratorService.login(form.mailAddress.toString(), form.password.toString())
        if (loginResult == null) {
            model.addAttribute("errMessage", "メールアドレスまたはパスワードが不正です。")
            return toLogin(form)
        }
        session.setAttribute("administratorName", loginResult.name)
        return "redirect:/employee/showList"
    }

    /**
     * ログアウト操作
     *
     * @param form ログインフォーム
     * @return ログイン画面に遷移
     */
    @GetMapping("/logout")
    fun logout(form: LoginForm): String {
        session.invalidate()
        return "redirect:/"
    }
}
