package ru.ilya.NauJava.service;

import ru.ilya.NauJava.model.Note;

public interface NoteService {
    void createNote(Long id, String header, String content);

    Note findById(Long id);

    void deleteById(Long id);

    void updateNote(Long id, String newTitle, String newContent);
}
