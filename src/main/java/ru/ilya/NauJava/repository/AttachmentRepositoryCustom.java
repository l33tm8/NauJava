package ru.ilya.NauJava.repository;

import ru.ilya.NauJava.model.Attachment;

import java.util.List;

public interface AttachmentRepositoryCustom {
    List<Attachment> findByName(String name);
}
