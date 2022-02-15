data class Chat(
    var idInterlocutor: Int = 0
    ) {
    var messages = mutableListOf<Messages>()

}