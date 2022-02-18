import org.junit.Test

import org.junit.Assert.*

class ServiceTest {

    private val chat1 = Chat(mutableListOf())
    private val chat2 = Chat(mutableListOf())
    private val chat3 = Chat(mutableListOf())
    private val chat4 = Chat(mutableListOf())

    private val messages1 = Messages(text = "Привет", date = 12, statusMessages = false)
    private val messages2 = Messages(text = "Как дела?", date = 32, statusMessages = false)
    private val messages3 = Messages(text = "Привет, выйдешь сегодня", date = 45, statusMessages = false)
    private val messages4 = Messages(text = "Хорошо", date = 23, statusMessages = true)


    @Test
    fun addChat() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)

        assertEquals(service.chats[0], chat1)
        assertEquals(service.chats[0].idInterlocutor, 13)
    }

    @Test
    fun deleteChat() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.deleteChat(idInterlocutor = 13)

        assertEquals(service.chats.size, 2)
    }

    @Test(expected = ServiceNotFoundException :: class)
    fun deleteChatTestFoundException() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.deleteChat(idInterlocutor = 5)
    }

    @Test
    fun getMessages() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages4, chat = chat4)

        val result = service.getMessages(idInterlocutor = 3)
        assertEquals(result, service.chats[2].messages)


    }
    @Test(expected = ServiceNotFoundException :: class)
    fun getMessagesTestFoundException() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages4, chat = chat4)

        service.getMessages(idInterlocutor = 5)
    }

    @Test
    fun getChat(){
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)

        assertEquals(service.chats.size, 3)
        assertEquals(service.getChat(),service.chats)
    }

    @Test
    fun addMessage() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)

        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        assertEquals(chat1,service.chats[0])
        assertEquals(service.chats[0].messages[0], messages1)

        service.addMessage(idInterlocutor = 10, messages = messages1, chat = chat4)
        assertEquals(service.chats[3],chat4)
        assertEquals(service.chats[3].messages[0],messages1)
    }

    @Test
    fun deleteMessage() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages4, chat = chat4)

        service.deleteMessage(idInterlocutor = 3, date = messages3.date)
        assertEquals(service.chats[2].messages.size, 1)
        service.deleteMessage(idInterlocutor = 13, date = messages1.date)
        val result = service.chats.find { it.idInterlocutor == 13}
        assertEquals(result,null )
    }

    @Test(expected = ServiceNotFoundException :: class)
    fun deleteMessageTestFoundException() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages4, chat = chat4)

        service.deleteMessage(idInterlocutor = 5, date = messages3.date)
        service.deleteMessage(idInterlocutor = 13, date = messages3.date)

    }

    @Test
    fun editMessage() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)

        service.editMessage(idInterlocutor = 13, date = messages1.date, text = "Здравствуйте")
        assertEquals(service.chats[0].messages[0].text, "Здравствуйте")

    }

    @Test(expected = ServiceNotFoundException :: class)
    fun editMessageTestFoundException() {
        val service = Service()
        service.addChat(idInterlocutor = 13,chat1)
        service.addChat(idInterlocutor = 11,chat2)
        service.addChat(idInterlocutor = 3,chat3)
        service.addMessage(idInterlocutor = 13, messages = messages1, chat = chat4)
        service.addMessage(idInterlocutor = 11, messages = messages2, chat = chat4)
        service.addMessage(idInterlocutor = 3, messages = messages3, chat = chat4)

        service.editMessage(idInterlocutor = 13, date = messages4.date, text = "Здравствуйте")
        service.editMessage(idInterlocutor = 5, date = messages1.date, text = "Здравствуйте")


    }
}