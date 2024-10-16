package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByHeaderAndUser(String header, User user);

    List<Note> findByTagsName(String tagName);
}
