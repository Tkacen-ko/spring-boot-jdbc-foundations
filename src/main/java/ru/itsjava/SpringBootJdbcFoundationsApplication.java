package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.services.UserService;

import java.sql.SQLException;


@SpringBootApplication
public class SpringBootJdbcFoundationsApplication {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootJdbcFoundationsApplication.class, args);
        applicationContext.getBean(UserService.class)
                .insert(new User("bora", 31, new Pet(1L, "Bolonka")));
        Console.main(args);
    }
}
