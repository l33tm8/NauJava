package ru.ilya.NauJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.User;
import ru.ilya.NauJava.repository.NoteRepositoryCustom;
import ru.ilya.NauJava.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/custom/notes")
public class NoteRestController {

    private final NoteRepositoryCustom noteRepository;
    private final UserRepository userRepository;

    public NoteRestController(NoteRepositoryCustom noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/findByHeaderAndUser")
    public List<Note> findByHeaderAndUser(@RequestParam String header, @RequestParam Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        return noteRepository.findByHeaderAndUser(header, user);
    }

}
