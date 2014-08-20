package org.rooms.rook.domain.dao.jdbi;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.skife.jdbi.v2.DBI;

import com.google.inject.Inject;

public class UserDaoJdbiImpl implements UserDao {
	
	final private DataSource dataSource;
	final private DBI dbi;
	final private UserDaoJdbi dao;
	
	@Inject
	public UserDaoJdbiImpl(final DataSource dataSource) {
		this.dataSource = dataSource;
		dbi = new DBI(this.dataSource);
		dao = dbi.open(UserDaoJdbi.class);
	}
	
	
    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
    
    @Override
    public User persist(User user) {
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
        dao.remove(user.getId().get(), user.getName(), user.getEmail(), user.getPwdHash());
    }
}
