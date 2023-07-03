package edu.telegram.selfsignechotelegrambot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.io.Serializable

@Component
class MessageHandler {
  fun answerMessage(message: Message): BotApiMethod<*> {
    val chatId = message.chatId.toString()
    return when (val inputText = message.text) {
      null -> {
        throw IllegalArgumentException("Input text is null")
      }

      "/start" -> {
        SendMessage(chatId, "Congratulations my friend!")
      }

      else -> {
        SendMessage(chatId, "This is an echo message: $inputText")
      }
    }
  }
}