package ru.ilya.NauJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.NauJava.model.Attachment;
import ru.ilya.NauJava.repository.AttachmentRepositoryCustom;

import java.util.List;

@RestController
@RequestMapping("/custom/attachments")
public class AttachmentRestController {
    private final AttachmentRepositoryCustom attachmentRepository;

    public AttachmentRestController(AttachmentRepositoryCustom attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @GetMapping("/findByNameLike")
    public List<Attachment> findByNameLike(@RequestParam("name") String name) {
        return attachmentRepository.findByNameLike(name);
    }

}
