package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
@RequestMapping("/")
public class RootController {
    @Autowired
    private UserService userService;

/*    @Autowired
    private UserMealRestController mealController;*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users" ,method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

/*    //mealsList

    @RequestMapping(value = "/meals",  method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", mealController.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/meals", params = {"action=create"}, method = RequestMethod.GET)
    public String mealCreate(@RequestParam("action") String action, HttpServletRequest request) {
        final UserMeal meal = new UserMeal(LocalDateTime.now(), "", 1000);
        request.setAttribute("meal", meal);
        return "mealEdit";
    }


    @RequestMapping(value = "/meals", params = {"action=update"}, method = RequestMethod.GET)
    public String mealUpdate(@RequestParam("action") String action, HttpServletRequest request) {
        final UserMeal meal = mealController.get(getId(request));
        request.setAttribute("meal", meal);
        return "mealEdit";
    }


    @RequestMapping(value = "/meals", params = {"action=delete"}, method = RequestMethod.GET)
    public String mealDelete(@RequestParam("action") String action, HttpServletRequest request) {
        int id = getId(request);
        mealController.delete(id);
        return "redirect:meals";
    }

    //mealEdit

    @RequestMapping(value = "/meals", params = {"action=save"}, method = RequestMethod.POST)
    public String mealSave(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            mealController.create(userMeal);
        } else {
            mealController.update(userMeal, getId(request));
        }
        return "redirect:meals";

    }
    @RequestMapping(value = "/meals", params = {"action=filter"}, method = RequestMethod.POST)
    public String mealFilter(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        request.setAttribute("mealList", mealController.getBetween(startDate, startTime, endDate, endTime));

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
    }*/
}
