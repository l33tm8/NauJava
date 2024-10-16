package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilya.NauJava.model.Tag;


@RepositoryRestResource(path = "tags")
public interface TagRepository extends CrudRepository<Tag, Long> {

    void deleteByName(String name);
}
