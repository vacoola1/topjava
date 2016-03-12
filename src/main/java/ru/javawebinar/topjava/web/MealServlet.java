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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by r.vakulenko on 09.03.2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private static String ADD_OR_EDIT = "/meal.jsp";
    private static String MEAL_LIST = "/mealList.jsp";

    private UserMealDao dao;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public MealServlet() {
        super();
        this.dao = new UserMealDaoImplMap();
    }

    private void setListToRequest(HttpServletRequest req) {
        List<UserMeal> mealList = dao.getAllUserMeal();
        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(23, 0), 2000);

        req.setAttribute("mealList", filteredMealsWithExceeded);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward;
        String action = req.getParameter("action");
        action = (action == null) ? "" : action;

        if (action.equalsIgnoreCase("add")) {
            LOG.debug("Add action, forward to mealList.jsp");

            forward = ADD_OR_EDIT;

            req.setAttribute("mealId", "");
            req.setAttribute("description", "");
            req.setAttribute("calories", "");
            req.setAttribute("dateTime", "");

        } else if (action.equalsIgnoreCase("edit")) {
            LOG.debug("Edit action, forward to meal.jsp");

            forward = ADD_OR_EDIT;

            UserMeal meal = dao.getUserMealById(Integer.parseInt(req.getParameter("mealId")));

            req.setAttribute("mealId", meal.getId());
            req.setAttribute("description", meal.getDescription());
            req.setAttribute("calories", meal.getCalories());
            req.setAttribute("dateTime", meal.getDateTime().format(formatter));

        } else if (action.equalsIgnoreCase("delete")) {
            LOG.debug("Delete action, forward to mealList.jsp");

            forward = MEAL_LIST;

            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.deleteUserMeal(dao.getUserMealById(mealId));
            setListToRequest(req);

        } else {
            LOG.debug("List action, forward to mealList.jsp");

            forward = MEAL_LIST;
            setListToRequest(req);
        }

        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Post, forward to mealList.jsp");

        req.setCharacterEncoding("UTF-8");

        String mealId = req.getParameter("mealId");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");
        String dateTime = req.getParameter("dateTime");

        if(mealId == null || mealId.isEmpty())
        {
            UserMeal meal = new UserMeal(LocalDateTime.parse(dateTime, formatter), description, Integer.parseInt(calories));
            dao.addUserMeal(meal);
        }
        else
        {
            UserMeal meal = dao.getUserMealById(Integer.parseInt(mealId));
            meal.setDateTime(LocalDateTime.parse(dateTime, formatter));
            meal.setDescription(description);
            meal.setCalories(Integer.parseInt(calories));
            dao.updateUserMeal(meal);
        }

        setListToRequest(req);
        req.getRequestDispatcher(MEAL_LIST).forward(req, resp);
    }
}
