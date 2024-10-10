package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ilya.NauJava.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

    void deleteByName(String name);
}
