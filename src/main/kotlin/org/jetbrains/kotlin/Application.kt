package org.jetbrains.kotlin.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class Application
//class BatchKotlinExampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)

    println("started!!!!!")
}