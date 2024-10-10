package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ilya.NauJava.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}
