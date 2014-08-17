package org.rooms.rook.domain;

import java.util.Objects;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.*;

public class User {

    private final int id;
    private final String name;
    private final String email;
    private final String pwdHash;

    public User(final int id, final String name, final String email, final String pwdHash) {
        this.id = id;
        this.name = checkNotNull(name);
        this.email = checkNotNull(email);
        this.pwdHash = checkNotNull(pwdHash);
    }

    public int getId() {
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
    
    @Override
    public String toString() {
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
