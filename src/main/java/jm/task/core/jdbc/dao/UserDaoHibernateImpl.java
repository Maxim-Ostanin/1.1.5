package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = util.getSession()) {
            session.beginTransaction();
            String hql = "CREATE TABLE IF NOT EXISTS USERS" +
                    "(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "NAME VARCHAR(45) NOT NULL," +
                    "LASTNAME VARCHAR(45) NOT NULL," +
                    "AGE TINYINT(255) NOT NULL)";

            session.createSQLQuery(hql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP table if exists users").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSession()) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {

        String hql = "FROM User";
        try (Session session = util.getSession()) {
            session.beginTransaction();
            List<User> userList = session.createQuery(hql).list();
            session.getTransaction().commit();
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSession()) {
            session.beginTransaction();

            session.createQuery("delete FROM User").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
