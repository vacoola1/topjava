package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.LoggedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to userList");

        String userIdStr = request.getParameter("setuserid");
        if (userIdStr != null && !userIdStr.equals("")) {
            LoggedUser.setId(Integer.parseInt(userIdStr));
        }
        request.setAttribute("loggedUser", new LoggedUser());
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
