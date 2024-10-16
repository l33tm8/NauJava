package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilya.NauJava.model.Priority;

@RepositoryRestResource(path = "priorities")
public interface PriorityRepository  extends CrudRepository<Priority, Integer> {
}
