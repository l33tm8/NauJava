package ru.ilya.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilya.NauJava.model.Attachment;

import java.util.List;


@RepositoryRestResource(path = "attachments")
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    @Query("select a from Attachment a WHERE a.name like :name")
    List<Attachment> findByName(@Param("name") String name);
}
