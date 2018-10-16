package org.jetbrains.kotlin.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import com.fasterxml.jackson.annotation.JsonCreator
import java.sql.*
import java.util.Properties

/**
 * Representation of a User to create
 * @property username The username of the user
 * @property screenName The screen name of the user
 * @property email The email address of the user
 */
data class NewUser @JsonCreator constructor(
        val username: String,
        val password: String
        //,
        //val email: String
)

data class User(
        val username: String,
        val password: String
        //,
 //       val email: String,
 //       val registered: Instant
)


data class Registration(
        val email: String
 
)

data class Users(
		val users: List<Registration>
)

@RestController
class LoginController {

    val counter = AtomicLong()

    @RequestMapping(value = "/api", method = arrayOf(RequestMethod.POST))
    fun blog(@RequestBody user: NewUser): Users {

    	var conn: Connection? = null
	    var user1 = "postgres" // provide the username
	    var password1 = "" // provide the corresponding password
	    val users = mutableListOf<Registration>()

	    val connectionProps = Properties()
        connectionProps.put("user", user1)
        connectionProps.put("password", password1)
        try {
            Class.forName("org.postgresql.Driver").newInstance()
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        } 

	    var stmt: Statement? = null
        var resultset: ResultSet? = null
        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery("select* from registration;")
            //if (stmt.execute("SHOW DATABASES;")) {
            resultset = stmt.resultSet
            //}
            while (resultset!!.next()) {
                println(resultset.getString("email"))

                users.add(Registration(email=resultset.getString("email")))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }
                resultset = null
            }
            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }
                stmt = null
            }
            if (conn != null) {
                try {
                    conn!!.close()
                } catch (sqlEx: SQLException) {
                }
                conn = null
            }
        }
	   
        /*return User(
                username = user.username,
                password = user.password
                //,
                //email = user.email,
                //registered = Instant.now()
        )*/

        return Users(users=users)
    }

}