package ru.ilya.NauJava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.ilya.NauJava.repository.NoteRepository;
import ru.ilya.NauJava.repository.TagRepository;
import ru.ilya.NauJava.model.Note;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;
    private final PlatformTransactionManager transactionManager;

    public TagServiceImpl(TagRepository tagRepository, NoteRepository noteRepository,
                          PlatformTransactionManager transactionManager) {
        this.tagRepository = tagRepository;
        this.noteRepository = noteRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteTag(String tagName) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<Note> notes = noteRepository.findByTagsName(tagName);
            noteRepository.deleteAll(notes);
            tagRepository.deleteByName(tagName);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}
