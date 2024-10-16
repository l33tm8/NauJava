package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilya.NauJava.model.User;


@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Integer> {

}
