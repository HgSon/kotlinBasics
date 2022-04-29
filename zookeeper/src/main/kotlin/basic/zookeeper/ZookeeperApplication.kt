package basic.zookeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZookeeperApplication

fun main(args: Array<String>) {
	runApplication<ZookeeperApplication>(*args)
}
