package org.rooms.rook.domain;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.*;

public class User {

    private final Optional<Long> id;
    private final String name;
    private final String email;
    private final String pwdHash;
    
    public User(final String name, final String email, final String pwdHash) {
        this.id = Optional.empty();
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
        this.pwdHash = checkNotNull(pwdHash);
    }

    public User(final long id, final String name, final String email, final String pwdHash) {
        this.id = Optional.of(id);
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
        this.pwdHash = checkNotNull(pwdHash);
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

    public String getPwdHash() {
        return pwdHash;
    }
    
    public boolean isPersisted() {
        return id.isPresent();
    }
    
    @Override
    public String toString() {
        String id = (isPersisted() ? this.id.get().toString() : "Not persisted");
        return toStringHelper(this)
                .add("id", id)
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
