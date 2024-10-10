package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ilya.NauJava.model.Priority;

public interface PriorityRepository  extends CrudRepository<Priority, Integer> {
}
