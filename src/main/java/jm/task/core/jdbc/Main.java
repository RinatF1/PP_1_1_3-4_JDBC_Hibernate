package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Vasya", "Pupkin", (byte) 34);
        userDao.saveUser("Dima", "Zalupkin", (byte) 38);
        userDao.saveUser("Sasha", "Krendel", (byte) 25);
        userDao.saveUser("Yura", "Pendel", (byte) 19);
        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();

       // userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
