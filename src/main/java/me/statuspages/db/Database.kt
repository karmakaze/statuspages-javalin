package me.statuspages.db

import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi

import javax.sql.DataSource
import java.net.URI
import java.net.URISyntaxException
import java.sql.SQLException
import java.util.Optional

class Database constructor(url: String, username: String, password: String) {
    private val dataSource: DataSource
    public val jdbi: Jdbi

    init {
        val ds = HikariDataSource()
        ds.jdbcUrl = url
        ds.username = username
        ds.password = password

        this.dataSource = ds
        this.jdbi = Jdbi.create(this.dataSource)
        jdbi.installPlugins()
    }

    companion object {

        @Throws(URISyntaxException::class)
        fun fromEnvVar(): Database {
            val dbUrl = Optional.ofNullable(System.getenv("DATABASE_URL"))
                    .orElse("postgresql://postgres:postgres@127.0.0.1:5432/statuspages-service")

            val dbUri = URI(dbUrl)

            val username = dbUri.userInfo.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            val password = dbUri.userInfo.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val jdbcUrl = "jdbc:postgresql://" + dbUri.host + ':'.toString() + dbUri.port + dbUri.path

            return Database(jdbcUrl, username, password)
        }

        fun forIntegrationTesting(): Database {
            val pgHost = Optional.ofNullable(System.getenv("PG_HOST")).orElse("192.168.99.100")

            return Database(
                    "jdbc:postgresql://$pgHost:5432/app_test",
                    "postgres",
                    "pass"
            )
        }

        // based on https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#using-the-database_url-in-plain-jdbc
        @Throws(URISyntaxException::class, SQLException::class)
        private fun getDataSource(): DataSource {
            val dbUri = URI(System.getenv("DATABASE_URL"))

            val username = dbUri.userInfo.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            val password = dbUri.userInfo.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val dbUrl = "jdbc:postgresql://" + dbUri.host + ':'.toString() + dbUri.port + dbUri.path

            val ds = HikariDataSource()
            ds.jdbcUrl = dbUrl
            ds.username = username
            ds.password = password
            return ds
        }
    }
}
