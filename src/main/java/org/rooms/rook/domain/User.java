package org.rooms.rook.domain;

import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.*;

@XmlRootElement
public class User {

    private final Optional<Long> id;
    private final String name;
    private final String email;
    private final String passwordHash;
    
    public User(final String name, final String email, final String passwordHash) {
        this.id = Optional.empty();
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
        this.passwordHash = checkNotNull(passwordHash);
    }

    public User(final long id, final String name, final String email, final String passwordHash) {
        this.id = Optional.of(id);
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
        this.passwordHash = checkNotNull(passwordHash);
    }

    public Optional<Long> getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    
    public boolean isPersisted() {
        return id.isPresent();
    }
    
    public User setId(long id) {
        if (isPersisted()) {
            // If the user has already been assigned an id from the database, return the same instance
            return this;
        }
        else {
            return new User(id, name, email, passwordHash);
        }
    }
    
    public User setName(String name) {
        if (isPersisted()) {
            return new User(id.get(), name, email, passwordHash);
        }
        else {
            return new User(name, email, passwordHash);
        }
    }
    
    public User setEmail(String email) {
        if (isPersisted()) {
            return new User(id.get(), name, email, passwordHash);
        }
        else {
            return new User(name, email, passwordHash);
        }
    }
    
    public User setPasswordHash(String passwordHash) {
        if (isPersisted()) {
            return new User(id.get(), name, email, passwordHash);
        }
        else {
            return new User(name, email, passwordHash);
        }
    }
    
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id.toString())
                .add("name", name)
                .add("email", email)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        
        User otherUser = (User) other;
        return Objects.equals(this.getId(), otherUser.getId());
    }
}
