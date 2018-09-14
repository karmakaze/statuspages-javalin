package me.statuspages.db

import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.sql.SQLException

class TodoMapper : ResultSetMapper<Todo> {

    @Throws(SQLException::class)
    override
    fun map(i: Int, rs: ResultSet, statementContext: StatementContext): Todo {
        return Todo(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                completed = rs.getBoolean("completed"),
                order = rs.getInt("item_order")
        )
    }
}
