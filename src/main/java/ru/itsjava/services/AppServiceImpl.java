package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final UserService userService;
    private final IOService ioService;
    private final PetDao petDao;

    @Override
    public void start() {
        System.out.println("Добро пожаловать ко мне в гости!");
        while (true) {
            System.out.println("Введите номер меню");
            System.out.println("1 -- напечатать всех пользователей, " +
                    "2 - добавить пользователя, остальное выход");
            int menuNum = ioService.inputInt();

            if (menuNum == 1) {
                printAllUser();
            } else if (menuNum == 2) {
                insertUser();
            } else if (menuNum == 2) {
                System.exit(0);
            }

        }
    }

    public void insertUser() {
        System.out.println("Введите пользователя");
        System.out.println("Введите имя");
        String name = ioService.input();
        System.out.println("Введите возраст");
        int age = ioService.inputInt();
        System.out.println("Введите имя питомца");
        String pet = ioService.input();
        Pet userPet = petDao.findByBreed(pet);

        User user = new User(name, age, userPet);

        userService.insert(user);
    }

    public void printAllUser() {
        List<User> userList = userService.findAll();

        System.out.println("Список студентов " + userList);
    }
}
