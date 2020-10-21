package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAllUsers();

        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
//        PrintWriter writer = response.getWriter();
//
//        writer.println("<table>");
//        writer.println("    <tr>");
//        writer.println("        <th>Username</th>");
//        writer.println("        <th>Password</th>");
//        writer.println("    </tr>");
//        for (User user : users) {
//            writer.println("<tr>");
//            writer.println("    <td>" + user.getUsername() + "</td>");
//            writer.println("    <td>" + user.getPassword() + "</td>");
//            writer.println("</tr>");
//        }
//        writer.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }
}
