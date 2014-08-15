package org.rooms.rook.domain.dao.jdbi;

import java.util.List;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDaoJdbi extends UserDao {
	
	@SqlQuery("SELECT id, name, email, pwd_hash FROM users WHERE id = :id")
	public User findUserById(@Bind("id") int id);
	
	@SqlQuery("SELECT id, name, email, pwd_hash FROM users")
	public List<User> getAllUsers();
	
	public void close();
	
}
