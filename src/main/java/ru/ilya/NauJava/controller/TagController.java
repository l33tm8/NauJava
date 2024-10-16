package ru.ilya.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.NauJava.model.Tag;
import ru.ilya.NauJava.repository.TagRepository;

@Controller
@RequestMapping("/custom/tags/view")
public class TagController {
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/list")
    public String tagListView(Model model) {
        Iterable<Tag> tags = tagRepository.findAll();
        model.addAttribute("tags", tags);
        return "tagList";
    }
}
