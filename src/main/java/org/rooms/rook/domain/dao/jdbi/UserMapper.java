package org.rooms.rook.domain.dao.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.rooms.rook.domain.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMapper implements ResultSetMapper<User> {

    public User map(final int index, final ResultSet r,
            final StatementContext ctx) throws SQLException {
        return new User(r.getInt("ID"), r.getString("NAME"),
                r.getString("EMAIL"), r.getString("PWD_HASH"));
    }
}
