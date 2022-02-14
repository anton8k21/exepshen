import javafx.geometry.Pos

fun main(args: Array<String>) {
    var service = Service()
    val chat0 = Chat(32)
    val chat1 = Chat(65)
    val chat2 = Chat(86)

    service.addChat(12,chat0)
    service.addChat(54,chat1)
    service.addChat(15,chat2)



}