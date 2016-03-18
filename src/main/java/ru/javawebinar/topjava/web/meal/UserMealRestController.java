package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.to.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll(LoggedUser.id());
    }

    public List<UserMealWithExceed> getWithExceeded() {
        LOG.info("getWithExceeded");
        List<UserMeal> userMeals = service.getAll(LoggedUser.id());
        return UserMealsUtil.getWithExceeded(userMeals, LoggedUser.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getWithExceeded(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        LOG.info("getFilteredWithExceeded");
        List<UserMeal> userMeals = service.getFiltered(LoggedUser.id(), startDate, endDate);
        return UserMealsUtil.getFilteredWithExceeded(userMeals, startTime, endTime, LoggedUser.getCaloriesPerDay());
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(LoggedUser.id(), id);
    }

    public UserMeal create(UserMeal userMeal) {
        userMeal.setId(null);
        LOG.info("create " + userMeal);
        return service.save(LoggedUser.id(), userMeal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(LoggedUser.id(), id);
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(LoggedUser.id(), userMeal);
    }
}
