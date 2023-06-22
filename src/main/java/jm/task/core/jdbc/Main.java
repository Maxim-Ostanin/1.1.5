package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 33);
        userService.saveUser("Петр", "Петров", (byte) 33);
        userService.saveUser("Сергей", "Сергеев", (byte) 33);
        userService.saveUser("Максим", "Максимов", (byte) 33);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}