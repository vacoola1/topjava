package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        return repository.save(userMeal);
    }

    @Override
    public void delete(Integer userId, int id) throws NotFoundException {
        ExceptionUtil.check(repository.delete(userId, id), id);
    }

    @Override
    public UserMeal get(Integer userId, int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(userId, id), id);
    }

    @Override
    public List<UserMeal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Integer userId, UserMeal userMeal) {
        repository.save(userMeal);
    }

    @Override
    public List<UserMeal> getFiltered(Integer userId, LocalDate startDate, LocalDate endDate) {
        return repository.getFiltered(userId, startDate, endDate);
    }

}
