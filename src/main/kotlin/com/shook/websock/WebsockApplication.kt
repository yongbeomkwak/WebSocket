package com.shook.websock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebsockApplication

fun main(args: Array<String>) {
	runApplication<WebsockApplication>(*args)
}
