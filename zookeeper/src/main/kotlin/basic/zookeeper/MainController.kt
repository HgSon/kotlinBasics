package basic.zookeeper

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping("")
    fun searchAnimals():String {
        // view
        return "hello"
    }
}