package org.rooms.rook.domain.dao;

import java.util.List;
import java.util.Optional;

import org.rooms.rook.domain.User;

public interface UserDao {

    public Optional<User> findById(int id);
    
    public List<User> getAll();
    
    public User persist(User user);
    
    public void remove(User user);
}