package ru.ilya.NauJava;

import jakarta.transaction.TransactionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.Tag;
import ru.ilya.NauJava.repository.NoteRepository;
import ru.ilya.NauJava.repository.TagRepository;
import ru.ilya.NauJava.service.TagService;

import java.util.*;

@SpringBootTest
public class TagTest {
    private final TagRepository tagRepository;
    private final TagService tagService;
    private final NoteRepository noteRepository;

    @Autowired
    public TagTest(TagRepository tagRepository,
                   TagService tagService,
                   NoteRepository noteRepository) {
        this.tagRepository = tagRepository;
        this.tagService = tagService;
        this.noteRepository = noteRepository;
    }

    @Test
    void testServiceTransactionSuccess() {
        String tagName = UUID.randomUUID().toString();
        String noteName = UUID.randomUUID().toString();
        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        Note note = new Note();
        note.setHeader(noteName);
        note.setTags(tags);
        noteRepository.save(note);

        tagService.deleteTag(tagName);

        Optional<Tag> foundTag = tagRepository.findById(tag.getId());
        Assertions.assertTrue(foundTag.isEmpty());

        Optional<Note> foundNote = noteRepository.findById(note.getId());
        Assertions.assertTrue(foundNote.isEmpty());
    }

    @Test
    void testServiceTransactionFailure() throws InterruptedException {
        String tagName = UUID.randomUUID().toString();
        String note1Name = UUID.randomUUID().toString();
        String note2Name = UUID.randomUUID().toString();

        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        Note note1 = new Note();
        note1.setHeader(note1Name);
        note1.setTags(tags);
        noteRepository.save(note1);

        Note note2 = new Note();
        note2.setHeader(note2Name);
        note2.setTags(tags);
        noteRepository.save(note2);

        Thread thread = new Thread(() -> tagService.deleteTag(tagName));
        thread.start();
        noteRepository.delete(note2);
        Thread.sleep(500);


        Optional<Tag> foundTag = tagRepository.findById(tag.getId());
        Assertions.assertTrue(foundTag.isPresent());

    }
}
