package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dap.UserDao;
import ru.itsjava.domain.User;

import java.sql.SQLException;


@SpringBootApplication
public class SpringBootJdbcFoundationsApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringBootJdbcFoundationsApplication.class, args);

        Console.main(args);
    }

}
