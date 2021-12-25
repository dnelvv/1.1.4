package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User[] users ={new User("Daniil", "Vaskin", (byte) 99),
                new User("Kolya","Ivanov",(byte) 15),
                new User("Ivan","Ivanov", (byte) 16),
                new User("Maksim","Gantelya",(byte) 24)};
        UserService userServ = new UserServiceImpl();
        userServ.createUsersTable();
        for (User user : users) {
            userServ.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("Челика добавили в базу \n", user.getName());
        }
        userServ.cleanUsersTable();
        userServ.dropUsersTable();

    }
}
