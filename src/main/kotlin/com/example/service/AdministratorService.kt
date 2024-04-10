package com.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.example.domain.Administrator
import com.example.repository.AdministratorRepository

/**
 * 管理者情報を操作するサービス.
 *
 * @author IchiyoshiKenta
 */
@Service
@Transactional
class AdministratorService {
    @Autowired
    private lateinit var administratorRepository: AdministratorRepository

    /**
     * 管理者情報を挿入する処理.
     *
     * @param administrator 管理者情報
     */
    fun insert(administrator: Administrator) {
        administratorRepository.insert(administrator)
    }

    /**
     * ログイン処理.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return　管理者情報　存在しない場合はnullを返す
     */
    fun login(mailAddress: String, password: String): Administrator? {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password)
    }
}
