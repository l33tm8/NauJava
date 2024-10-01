package ru.ilya.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ilya.NauJava.service.NoteService;

@Component
@Lazy
public class CommandProcessor {
    private final NoteService noteService;

    @Autowired
    public CommandProcessor(NoteService noteService) {
        this.noteService = noteService;
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");
        try {
            switch (commandParts[0]) {
                case "create" -> {
                    noteService.createNote(Long.valueOf(commandParts[1]), commandParts[2], commandParts[3]);
                    System.out.println("Заметка создана");
                }
                case "delete" -> {
                    noteService.deleteById(Long.valueOf(commandParts[1]));
                    System.out.println("Заметка удалена");
                }
                case "update" -> {
                    noteService.updateNote(Long.valueOf(commandParts[1]), commandParts[2], commandParts[3]);
                    System.out.println("Заметка обновлена");
                }
                case "read" -> System.out.println(noteService.findById(Long.valueOf(commandParts[1])));

                default -> System.out.println("Введена неизвестная команда...");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Неверное количество параметров");
        }

    }
}
