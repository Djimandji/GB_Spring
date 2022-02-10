package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query ("select distinct u" +
            " from User u left join fetch u.roles " +
            "where u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);
}
