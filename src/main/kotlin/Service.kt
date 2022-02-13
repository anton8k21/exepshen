class Service {
    var chats = mutableListOf<Chat>()

    fun addChat(idInterlocutor: Int, chat: Chat){
        chat.idInterlocutor = idInterlocutor
        chats += chat
    }
    fun deleteChat(idInterlocutor: Int): MutableList<Chat>{
        for (index in chats.indices){
            if (chats[index].idInterlocutor == idInterlocutor){
                chats.removeAt(index)
                return chats
            }
        }
        throw ServiceNotFoundException("Час с данным пользователем не найден")
    }

    fun getChat(): MutableList<Chat>{
        return chats
    }

    fun addMessage(idInterlocutor: Int, messages: Messages){
        for (index in chats.indices){
            if (chats[index].idInterlocutor == idInterlocutor){
                chats[index].messages += messages
            }
        }
    }

    fun deleteMessage(date: Int,idRecipient: Int,idSender: Int, idInterlocutor: Int){
        for (index in chats.indices){
            if (chats[index].idInterlocutor == idInterlocutor){
                for (i in chats[index].messages.indices){
                    if (
                        chats[index].messages[i].date == date &&
                        chats[index].messages[i].idRecipient == idRecipient &&
                        chats[index].messages[i].idSender == idSender
                    ){
                        chats[index].messages.removeAt(i)
                    }
                }
            }
        }
    }
}