package ru.ilya.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void createNote(Long id, String header, String content) {
        Note note = new Note();
        note.setId(id);
        note.setHeader(header);
        note.setContent(content);
        noteRepository.create(note);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public void updateNote(Long id, String newHeader, String newContent) {
        Note note = new Note();
        note.setId(id);
        note.setHeader(newHeader);
        note.setContent(newContent);
        noteRepository.update(note);
    }
}
