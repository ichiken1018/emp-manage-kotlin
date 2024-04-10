package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmpManageApplication

fun main(args: Array<String>) {
	runApplication<EmpManageApplication>(*args)
}
