package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    ProxyUserRepository proxyUserRepository;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        /*
        User ref = proxyUserRepository.getOne(userId);
        userMeal.setUser(ref);

        if (userMeal.isNew()) {
            return proxy.save(userMeal);
        } else {
            return get(userMeal.getId(), userId) == null ? null : proxy.save(userMeal);
        }
        */


        if (!userMeal.isNew() && get(userMeal.getId(), userId) == null) {
            return null;
        } else {
            User ref = proxyUserRepository.getOne(userId);
            userMeal.setUser(ref);
            return proxy.save(userMeal);
        }

    }


    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }


    @Override
    public UserMeal getWithUser(int id, int userId) {
        return proxy.getWithUser(id, userId);
    }
}
