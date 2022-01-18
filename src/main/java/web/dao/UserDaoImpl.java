package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> index() {
        return em.createQuery("from User").getResultList();
    }

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        em.merge(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        em.remove(getUser(id));
    }

}
