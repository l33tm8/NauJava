package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;

import java.util.List;

@RepositoryRestResource(path = "notes")
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByHeaderAndUser(String header, User user);

    List<Note> findByTagsName(String tagName);
}
