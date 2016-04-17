package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by r.vakulenko on 18.04.2016.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealController extends AbstractUserMealController {

    //mealsList

    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", super.getAll());
        return "mealList";
    }

    @RequestMapping(params = {"action=create"}, method = RequestMethod.GET)
    public String mealCreate(@RequestParam("action") String action, HttpServletRequest request) {
        final UserMeal meal = new UserMeal(LocalDateTime.now(), "", 1000);
        request.setAttribute("meal", meal);
        return "mealEdit";
    }


    @RequestMapping(params = {"action=update"}, method = RequestMethod.GET)
    public String mealUpdate(@RequestParam("action") String action, HttpServletRequest request) {
        final UserMeal meal = super.get(getId(request));
        request.setAttribute("meal", meal);
        return "mealEdit";
    }


    @RequestMapping(params = {"action=delete"}, method = RequestMethod.GET)
    public String mealDelete(@RequestParam("action") String action, HttpServletRequest request) {
        int id = getId(request);
        super.delete(id);
        return "redirect:meals";
    }

    //mealEdit

    @RequestMapping(params = {"action=save"}, method = RequestMethod.POST)
    public String mealSave(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            super.create(userMeal);
        } else {
            super.update(userMeal, getId(request));
        }
        return "redirect:meals";

    }
    @RequestMapping(params = {"action=filter"}, method = RequestMethod.POST)
    public String mealFilter(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        request.setAttribute("mealList", super.getBetween(startDate, startTime, endDate, endTime));

        return "mealList";
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"), "parameter id  must not be null");
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
