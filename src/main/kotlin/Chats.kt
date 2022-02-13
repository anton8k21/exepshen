data class Chat(
    var idInterlocutor: Int
    ) {
    var messages = mutableListOf<Messages>()

}