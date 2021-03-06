package ru.innopolis.uni.course3.ofedorova.servlets;

import ru.innopolis.uni.course3.ofedorova.models.User;
import ru.innopolis.uni.course3.ofedorova.servlets.security.ServletsCommon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для отображения главной страницы.
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 26.12.2016
 */
public class MainServlet extends HttpServlet {

    /**
     * Вызывается сервером и позволяют сервлету обрабатывать GET-запрос.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletsCommon.getUserFromSession(req.getSession());
        req.setCharacterEncoding(ServletsCommon.UTF_8);
        req.setAttribute("username", user == null ? "не авторизован" : user.getName());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
