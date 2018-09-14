package me.statuspages.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(TodoMapper.class)
public interface TodoDAO {
    @SqlQuery("INSERT INTO todos (title,completed,item_order) VALUES (:title, false, COALESCE(:item_order,0)) RETURNING *")
    Todo createTodo(@Bind("title") String title, @Bind("item_order") Integer item_order);

    @SqlQuery("SELECT * FROM todos")
    List<Todo> findAll();

    @SqlQuery("SELECT * FROM todos WHERE id = :id LIMIT 1")
    Todo findById(@Bind("id") Integer id);

    @SqlUpdate("DELETE FROM todos")
    void deleteAll();

    @SqlQuery("UPDATE todos" +
              " SET title=COALESCE(:title,title), completed=COALESCE(:completed,completed), item_order=COALESCE(:item_order,item_order)" +
              " WHERE id = :id" +
              " RETURNING *")
    Todo updateTodo(@Bind("id") Integer id, @Bind("title") String title, @Bind("completed") Boolean completed, @Bind("item_order") Integer item_order);

    @SqlUpdate("DELETE FROM todos WHERE id = :id")
    void deleteById(@Bind("id") Integer id);
}
