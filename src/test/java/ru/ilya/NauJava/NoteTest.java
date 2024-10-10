package ru.ilya.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;
import ru.ilya.NauJava.repository.NoteRepository;
import ru.ilya.NauJava.repository.NoteRepositoryCustom;
import ru.ilya.NauJava.repository.NoteRepositoryImpl;
import ru.ilya.NauJava.repository.UserRepository;

import java.util.UUID;

@SpringBootTest
public class NoteTest {
    private final NoteRepository noteRepository;
    private final NoteRepositoryCustom noteRepositoryCustom;
    private final UserRepository userRepository;

    @Autowired
    public NoteTest(NoteRepository noteRepository,
                    NoteRepositoryImpl noteRepositoryCustom,
                    UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.noteRepositoryCustom = noteRepositoryCustom;
        this.userRepository = userRepository;
    }

    @Test
    void testFindByHeaderAndUser() {
        String header = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        userRepository.save(user);
        Note note = new Note();
        note.setHeader(header);
        note.setUser(user);
        noteRepository.save(note);

        Note foundNote = noteRepository.findByHeaderAndUser(header, user).getFirst();
        Assertions.assertNotNull(foundNote);
        Assertions.assertEquals(note.getHeader(), foundNote.getHeader());
        Assertions.assertEquals(note.getUser().getId(), foundNote.getUser().getId());
    }

    @Test
    void testCustomFindByHeaderAndUser() {
        String header = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        userRepository.save(user);
        Note note = new Note();
        note.setHeader(header);
        note.setUser(user);
        noteRepository.save(note);
        Note foundNote = noteRepositoryCustom.findByHeaderAndUser(header, user).getFirst();

        Assertions.assertNotNull(foundNote);
        Assertions.assertEquals(note.getHeader(), foundNote.getHeader());
        Assertions.assertEquals(note.getUser().getId(), foundNote.getUser().getId());
    }

}
