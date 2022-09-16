package kr.nanoit.db;

import kr.nanoit.db.impl.PostgreSqlDbcp;
import kr.nanoit.db.impl.UserServicePostgreSQLImpl;
import kr.nanoit.db.impl.UserServiceTestImpl;
import kr.nanoit.object.entity.UserEntity;

public interface UserService {
    static UserService createTest() {
        return new UserServiceTestImpl();
    }

    static UserService createPostgreSQL(PostgreSqlDbcp dbcp) {
        return new UserServicePostgreSQLImpl(dbcp);
    }

    UserEntity save(UserEntity userEntity);

    UserEntity findById(long userId);

    boolean deleteById(long userId);

    UserEntity update(UserEntity userEntity);

    boolean containsById(long id);
}
