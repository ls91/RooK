package org.rooms.rook.domain.dao.jdbi;

import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;
import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.skife.jdbi.v2.DBI;

public class UserDaoJdbiImpl implements UserDao {
	
//	private final DataSource dataSource;
	
	@Override
	public User findUserById(int id) {
		JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
		
		DBI dbi = new DBI(ds);
		UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
	
		User user = dao.findUserById(id);
		
		dao.close();
		ds.dispose();
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
		
		DBI dbi = new DBI(ds);
		UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
	
		List<User> users = dao.getAllUsers();
		
		dao.close();
		ds.dispose();
		
		return users;
	}

}
