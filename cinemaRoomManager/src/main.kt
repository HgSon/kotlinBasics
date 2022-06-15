package cinema

fun main() {
    val manager = Manager()
    manager.createScheme()
    while (!manager.exitProgram()) {
        manager.selectMenu()
    }
}
