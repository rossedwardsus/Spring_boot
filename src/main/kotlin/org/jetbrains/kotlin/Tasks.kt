package org.jetbrains.kotlin.demo

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Value(var id: Long = 0, var quote: String = "")

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(var type : String = "", var value : Value? = null)

@Component
public class ScheduledTasks {
    //private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    //private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    
    @Scheduled(fixedRate = 2000)
    fun scheduleTaskWithFixedRate() {

    	println("task")

    	//RestTemplate restTemplate = new RestTemplate();
        //Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        val quote = RestTemplate().getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)
        println(quote)

    }

    //public void scheduleTaskWithFixedDelay() {}

    //public void scheduleTaskWithInitialDelay() {}

    //public void scheduleTaskWithCronExpression() {}
}
