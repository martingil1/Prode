package com.example.prode.repositories;

import com.example.prode.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDto extends CrudRepository<User, Long> {

    Boolean existsByNameUser(String nameUser);

    User getUserByNameUser(String nameUser);

}
