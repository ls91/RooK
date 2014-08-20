package org.rooms.rook.domain.dao.jdbi;

import java.util.List;
import java.util.Optional;

import org.h2.jdbcx.JdbcConnectionPool;
import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.skife.jdbi.v2.DBI;

public class UserDaoJdbiImpl implements UserDao {
	
    @Override
    public Optional<User> findById(int id) {
        JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
        DBI dbi = new DBI(ds);
        UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public List<User> getAll() {
        JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
        DBI dbi = new DBI(ds);
        UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
        return dao.getAll();
    }
    
    @Override
    public User persist(User user) {
        JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");

        DBI dbi = new DBI(ds);
        UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
        
        long userId;
        if (user.isPersisted()) {
        	userId = dao.persist(user.getId().get(), user.getName(), user.getEmail(), user.getPwdHash());
        } else {
        	userId = dao.persist(user.getName(), user.getEmail(), user.getPwdHash());
        }
        
        return new User((userId == 0) ? user.getId().get() : userId, user.getName(), user.getEmail(), user.getPwdHash());
    }
    
    @Override
    public void remove(User user) {
        JdbcConnectionPool ds = JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
        DBI dbi = new DBI(ds);
        UserDaoJdbi dao = dbi.open(UserDaoJdbi.class);
        dao.remove(user.getId().get(), user.getName(), user.getEmail(), user.getPwdHash());
    }
}
