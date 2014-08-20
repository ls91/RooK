package org.rooms.rook.domain.dao.jdbi;

import java.util.List;

import org.rooms.rook.domain.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDaoJdbi {

    @SqlQuery("SELECT id, name, email, pwd_hash FROM users WHERE id = :id")
    public User findById(@Bind("id") int id);

    @SqlQuery("SELECT id, name, email, pwd_hash FROM users")
    public List<User> getAll();
    
    @SqlUpdate("INSERT INTO users (name, email, pwd_hash) VALUES (:name, :email, :pwdHash)")
    @GetGeneratedKeys
    public long persist(@Bind("name") String name, @Bind("email") String email, @Bind("pwdHash") String pwdHash);
    
    @SqlUpdate("UPDATE users SET name = :name, email = :email, pwd_hash = :pwdHash WHERE id = :id")
    @GetGeneratedKeys
    public long persist(@Bind("id") long id, @Bind("name") String name, @Bind("email") String email, @Bind("pwdHash") String pwdHash);
    
    @SqlUpdate("DELETE FROM users WHERE id = :id AND name = :name AND email = :email AND pwd_hash = :pwdHash")
    public void remove(@Bind("id") long id, @Bind("name") String name, @Bind("email") String email, @Bind("pwdHash") String pwdHash);

    public void close();

}