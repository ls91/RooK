package org.rooms.rook.domain;

public class User {

    private int _id;

    private String _name;

    private String _email;

    private String _pwdHash;

    public User(final int id, final String name, final String email,
            final String pwdHash) {
        _id = id;
        _name = name;
        _email = email;
        _pwdHash = pwdHash;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getEmail() {
        return _email;
    }

    public String getPwdHash() {
        return _pwdHash;
    }
}
