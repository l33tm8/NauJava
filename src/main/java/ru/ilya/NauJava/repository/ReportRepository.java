package ru.ilya.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.NauJava.model.Report;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    Optional<Report> findById(long id);
}
