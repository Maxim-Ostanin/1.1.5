package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoHibernateImpl() {

    }

    private final String hqlCreateTable = "CREATE TABLE IF NOT EXISTS USERS" +
            "(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            "NAME VARCHAR(45) NOT NULL," +
            "LASTNAME VARCHAR(45) NOT NULL," +
            "AGE TINYINT(255) NOT NULL)";

    private final String hqlDropTable = "DROP table if exists users";
    private final String hqlFU = "FROM User";
    private final String hqlDelete = "delete FROM User";


    private final Session session = util.getSession();
    @Override
    public void createUsersTable() {
        try (session) {
            session.beginTransaction();

            session.createSQLQuery(hqlCreateTable).addEntity(User.class).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try (session) {
            session.beginTransaction();

            session.createSQLQuery(hqlDropTable).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (session) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (session) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (session) {
            List<User> userList = session.createQuery(hqlFU).list();

            System.out.println(userList);
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (session) {
            session.beginTransaction();

            session.createQuery(hqlDelete).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
