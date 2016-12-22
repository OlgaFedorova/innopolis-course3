package ru.innopolis.uni.course3.ofedorova.servlets;

import ru.innopolis.uni.course3.ofedorova.store.storageofstudents.StudentCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olga on 22.12.2016.
 */
public class StudentViewServlet extends HttpServlet {

    private final StudentCache studentCache = new StudentCache();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", this.studentCache.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/student/StudentView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.studentCache.close();
    }
}
