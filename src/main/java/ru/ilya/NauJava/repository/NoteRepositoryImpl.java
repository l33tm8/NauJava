package ru.ilya.NauJava.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;

import java.util.List;

@Repository
public class NoteRepositoryImpl implements NoteRepositoryCustom {

    private final EntityManager entityManager;

    public NoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Note> findByHeaderAndUser(String header, User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);

        Root<Note> root = criteriaQuery.from(Note.class);
        Predicate headerPredicate = criteriaBuilder.equal(root.get("header"), header);
        Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
        criteriaQuery.where(criteriaBuilder.and(headerPredicate, userPredicate));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
