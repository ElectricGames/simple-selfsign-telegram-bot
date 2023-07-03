package edu.telegram.selfsignechotelegrambot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleSelfSignEchoTelegramBotApplication

fun main(args: Array<String>) {
	runApplication<SimpleSelfSignEchoTelegramBotApplication>(*args)
}
