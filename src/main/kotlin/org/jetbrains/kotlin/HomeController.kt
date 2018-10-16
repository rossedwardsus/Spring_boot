package org.jetbrains.kotlin.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.stereotype.Controller
import java.util.concurrent.atomic.AtomicLong

@Controller
class HomeController {

    val counter = AtomicLong()

    //get projects list
    //get project
    //get project twitter feed
    //get project twitter account details
    //get project twitter followers over time
    //get project likes count over time
    //get project retweets count over time
    //get projects mention count over time
    //get tags for a project

    //admin add project
    //category
    //social links


    @GetMapping("/111")
    fun blog(): String {
	    //model["title"] = "Blog"
	    return "index"
	}

}