package ru.ilya.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilya.NauJava.model.Attachment;
import ru.ilya.NauJava.repository.AttachmentRepository;
import ru.ilya.NauJava.repository.AttachmentRepositoryCustom;
import ru.ilya.NauJava.repository.AttachmentRepositoryImpl;

import java.util.UUID;

@SpringBootTest
public class AttachmentTest {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentRepositoryCustom attachmentRepositoryCustom;

    @Autowired
    public AttachmentTest(AttachmentRepository attachmentRepository,
                          AttachmentRepositoryImpl attachmentRepositoryCustom) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentRepositoryCustom = attachmentRepositoryCustom;
    }

    @Test
    void testFindByName() {
        String attachmentName = UUID.randomUUID().toString();
        Attachment attachment = new Attachment();
        attachment.setName(attachmentName);
        attachmentRepository.save(attachment);

        Attachment foundAttachment = attachmentRepository.findByName(attachmentName).getFirst();

        Assertions.assertNotNull(foundAttachment);
        Assertions.assertEquals(attachment.getId(), foundAttachment.getId());
        Assertions.assertEquals(attachment.getName(), foundAttachment.getName());
    }

    @Test
    void testCustomFindByName() {
        String attachmentName = UUID.randomUUID().toString();
        Attachment attachment = new Attachment();
        attachment.setName(attachmentName);
        attachmentRepository.save(attachment);

        Attachment foundAttachment = attachmentRepositoryCustom.findByName(attachmentName).getFirst();
        Assertions.assertNotNull(foundAttachment);
        Assertions.assertEquals(attachment.getId(), foundAttachment.getId());
        Assertions.assertEquals(attachment.getName(), foundAttachment.getName());
    }
}
