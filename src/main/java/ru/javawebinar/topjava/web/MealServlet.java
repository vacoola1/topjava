package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private ConfigurableApplicationContext appCtx;

    private UserMealRestController userMealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userMealController = appCtx.getBean(UserMealRestController.class);
    }

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        boolean filtered = Boolean.parseBoolean(request.getParameter("filtered"));

        if (filtered) {

            LOG.info("getFiltered");

            LocalDate startDate = request.getParameter("startDate").isEmpty() ? (LocalDate.MIN) : LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = request.getParameter("endDate").isEmpty() ? (LocalDate.MIN) : LocalDate.parse(request.getParameter("endDate"));
            LocalTime startTime = request.getParameter("startTime").isEmpty() ? (LocalTime.MIN) : LocalTime.parse(request.getParameter("startTime"));
            LocalTime endTime = request.getParameter("endTime").isEmpty() ? (LocalTime.MIN) : LocalTime.parse(request.getParameter("endTime"));

            request.setAttribute("mealList", userMealController.getWithExceeded(startDate, endDate, startTime, endTime));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else {
            String id = request.getParameter("id");

            UserMeal userMeal = new UserMeal(LoggedUser.id(), id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            if (userMeal.isNew()) {
                LOG.info("Create {}", userMeal);
                userMealController.create(userMeal);
            } else {
                LOG.info("Update {}", userMeal);
                userMealController.update(userMeal, userMeal.getId());
            }
            response.sendRedirect("meals");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList", userMealController.getWithExceeded());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

            /*} else if (action.equals("filter")) {
            LOG.info("getFiltered");
            request.setAttribute("mealList", userMealController.getWithExceeded());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);*/

        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            userMealController.delete(id);
            response.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LoggedUser.id(), LocalDateTime.now(), "", 1000) :
                    userMealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
