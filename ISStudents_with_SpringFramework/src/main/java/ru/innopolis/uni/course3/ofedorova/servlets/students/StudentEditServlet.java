package ru.innopolis.uni.course3.ofedorova.servlets.students;

import ru.innopolis.uni.course3.ofedorova.controllers.StudentController;
import ru.innopolis.uni.course3.ofedorova.models.Student;
import ru.innopolis.uni.course3.ofedorova.service.Settings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olga on 22.12.2016.
 */
public class StudentEditServlet extends HttpServlet {

    private final StudentController studentController = (StudentController) Settings.APPLICATION_CONTEXT.getBean("students_controller");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Student student = this.studentController.get(Integer.valueOf(id));
        req.setAttribute("id", id);
        req.setAttribute("name", student.getName());
        req.setAttribute("group", student.getGroup());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/student/EditStudent.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.studentController.edit(new Student(Integer.valueOf(req.getParameter("id")), req.getParameter("name"), req.getParameter("group")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/student/view"));
    }
}