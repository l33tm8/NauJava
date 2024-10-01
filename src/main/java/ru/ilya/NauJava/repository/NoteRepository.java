package ru.ilya.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilya.NauJava.model.Note;

import java.util.List;

@Component
public class NoteRepository implements CrudRepository<Note, Long> {
    private final List<Note> noteContainer;

    @Autowired
    public NoteRepository(List<Note> noteContainer) {
        this.noteContainer = noteContainer;
    }

    @Override
    public void create(Note entity) {
        noteContainer.add(entity);
    }

    @Override
    public Note read(Long id) {
        return noteContainer
                .stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Note note) {
        for (int i = 0; i < noteContainer.size(); i++) {
            if (note.getId().equals(noteContainer.get(i).getId())) {
                noteContainer.set(i, note);
            }
        }
    }

    @Override
    public void delete(Long id) {
        noteContainer.removeIf(note -> note.getId().equals(id));
    }
}
