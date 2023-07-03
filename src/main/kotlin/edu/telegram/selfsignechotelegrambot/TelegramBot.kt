package edu.telegram.selfsignechotelegrambot

import org.apache.logging.log4j.LogManager.getLogger
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.starter.SpringWebhookBot

class TelegramBot(
  val tgBotToken: String,
  val tgBotName: String,
  private val messageHandler: MessageHandler,
  webhook: SetWebhook
) : SpringWebhookBot(webhook) {
  override fun getBotToken() = tgBotToken
  override fun getBotUsername() = tgBotName
  override fun getBotPath() = ""
  override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
    val chatId = update.message.chatId.toString()

    return try {
      messageHandler.answerMessage(update.message)
    } catch (e: IllegalArgumentException) {
      LOG.error(e.message, e)
      SendMessage(chatId, "Only TEXT!!!")
    } catch (e: Exception) {
      LOG.error(e.message, e)
      SendMessage(chatId, "Something WRONG!")
    }
  }

  companion object {
    private val LOG = getLogger(this::class.java)
  }
}