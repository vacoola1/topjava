package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.UserMealDao;
import ru.javawebinar.topjava.dao.UserMealDaoImplMap;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by r.vakulenko on 09.03.2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to mealList.jsp");
        UserMealDao userMealDao = new UserMealDaoImplMap();
        List<UserMeal> mealList = userMealDao.getAllUserMeal();

        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 4000);

        req.setAttribute("mealList", filteredMealsWithExceeded);
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserMealDao userMealDao = new UserMealDaoImplMap();
        super.doPost(req, resp);
    }
}
