package edu.telegram.selfsignechotelegrambot

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.logging.log4j.LogManager.getLogger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class WebHookController(val telegramBot: TelegramBot) {
  @PostMapping("/callback")
  fun onUpdateReceived(@RequestBody update: Update): BotApiMethod<*> {
    LOG.info("Received update:\n ${mapper.writeValueAsString(update)}")
    val answer = telegramBot.onWebhookUpdateReceived(update)
    LOG.info("Received webhook:\n ${mapper.writeValueAsString(answer)}")
    return answer
  }

  companion object {
    private val LOG = getLogger(this::class.java)
    val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
  }
}