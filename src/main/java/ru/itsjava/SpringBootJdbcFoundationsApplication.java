package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;
//import ru.itsjava.repository.UserRepository;

import java.sql.SQLException;


@SpringBootApplication
public class SpringBootJdbcFoundationsApplication {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootJdbcFoundationsApplication.class, args);

        PetRepository petRepository = applicationContext.getBean(PetRepository.class);
        System.out.println("petRepository.getById(1L) = " + petRepository.getById(1L));

        Pet pet = new Pet(0L, "Trinog");
        petRepository.insert(pet);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        Pet pet3 = petRepository.getById(3L);
        pet.setBreed("UltaDog");
        petRepository.update(pet3);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        petRepository.deleteBuId(3L);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        System.out.println("userRepository.findAll() = " + userRepository.findAll());
        //Console.main(args);
    }
}
