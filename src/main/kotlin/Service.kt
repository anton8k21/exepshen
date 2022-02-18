
class Service {
    var chats = mutableListOf<Chat>()

    fun addChat(idInterlocutor: Int, chat: Chat) {
        chat.idInterlocutor = idInterlocutor
        chats += chat
    }

    fun deleteChat(idInterlocutor: Int): MutableList<Chat> {
        val result = chats.removeIf { it.idInterlocutor == idInterlocutor }
        if (!result) {
            throw ServiceNotFoundException("Чат с данным пользователем не найден")
        }
        return chats
    }
    fun getMessages(idInterlocutor: Int): MutableList<Messages> {
        val result = chats.find { it.idInterlocutor == idInterlocutor }
        if (result != null) {
            result.messages.forEach { it.statusMessages = false }
            return result.messages
        }else
            throw ServiceNotFoundException("Час с данным пользователем пустой")
    }

    fun getChat(): MutableList<Chat> {
        return chats
    }

    fun addMessage(idInterlocutor: Int, messages: Messages, chat: Chat) {
        val result = chats.find { it.idInterlocutor == idInterlocutor }
        if (result != null) {
            result.messages += messages
        } else
            chat.messages += messages
            addChat(idInterlocutor,chat)
    }

    fun deleteMessage(idInterlocutor: Int, date: Int): List<Messages> {
        val result = chats.find { it.idInterlocutor == idInterlocutor }
        if (result != null) {
            if (result.messages.size != 1) {
                val resultDelete = result.messages.removeIf { it.date == date }
                if (!resultDelete) {
                    throw ServiceNotFoundException("Сообщение которое вы хотите удалить не найдено")
                }
                return result.messages
            } else {
                if (result.messages.size == 1) {
                    val resultDelete = result.messages.removeIf() { it.date == date }
                    if (!resultDelete) {
                        throw ServiceNotFoundException("Сообщение которое вы хотите удалить не найдено")
                    } else {
                        deleteChat(idInterlocutor)
                        return result.messages
                    }
                }
            }
            return result.messages
        } else
            throw ServiceNotFoundException("Чат не найден")
    }

    fun editMessage(idInterlocutor: Int, date: Int, text: String) {
        val result = chats.find { it.idInterlocutor == idInterlocutor }
        if (result != null) {
            val result2 = result.messages.find { it.date == date }
            if (result2 != null) {
                result2.text = text
            } else
                throw ServiceNotFoundException("Сообщение которое вы хотите изменить не найдено")
        } else
            throw ServiceNotFoundException("Чат не найден")
    }
}


