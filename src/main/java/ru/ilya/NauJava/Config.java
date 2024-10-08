package ru.ilya.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import ru.ilya.NauJava.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Config {

    @Autowired
    @Lazy
    private CommandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. 'exit' для выхода");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Note> noteContainer() {
        return new ArrayList<>();
    }

}
