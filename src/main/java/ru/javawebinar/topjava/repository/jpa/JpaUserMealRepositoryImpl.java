package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {

            User ref = em.getReference(User.class, userId);
            userMeal.setUser(ref);
            em.persist(userMeal);
            return userMeal;
        } else {
            if (userMeal.getUser().getId() == userId) {
                return em.merge(userMeal); //todo: do exepion heppends, if userId changed
            } else {
                return null;
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        Query query = em.createQuery("DELETE FROM UserMeal um WHERE um.userId = :userId AND um.id=:id");
        return query
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.user =:user AND um.id = :id");
        User ref = em.getReference(User.class, userId);
        return (UserMeal) query
                .setParameter("user", ref)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.userId=:userId");
        return query.setParameter("userId", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.userId=:userId AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.datetime DESC");

        return query.setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }
}