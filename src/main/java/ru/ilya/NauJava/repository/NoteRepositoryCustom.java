package ru.ilya.NauJava.repository;

import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;

import java.util.List;

public interface NoteRepositoryCustom {

    List<Note> findByHeaderAndUser(String header, User user);

}
