package com.kristianandi.BackendTest.Persistence.Repositories;

import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findByPhoneNumberAndPassword(String phoneNumber, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phone AND password = :password", User.class);
        query.setParameter("phone", phoneNumber);
        query.setParameter("password", password);

        return query.getResultList();
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        Query query = entityManager.createQuery("SELECT u FROM User u wHERE phoneNumber = :phoneNumber", User.class);
        query.setParameter("phoneNumber", phoneNumber);
        query.setMaxResults(1);
        return (User)query.getSingleResult();
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public User delete(User user) {
        entityManager.remove(user);
        return user;
    }

    @Override
    @Transactional
    public User deleteById(Long id) {
        User user = findById(id);

        if(user != null)
        {
            entityManager.remove(user);
        }

        return user;
    }

    @Override
    @Transactional
    public int deleteAll() {
        Query query = entityManager.createQuery("DELETE FROM User");
        return query.executeUpdate();
    }
}
