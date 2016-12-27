package ru.innopolis.uni.course3.ofedorova.servlets.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.uni.course3.ofedorova.controllers.ControllerForUsers;
import ru.innopolis.uni.course3.ofedorova.dao.users.JdbcOfDAOtoUsers;
import ru.innopolis.uni.course3.ofedorova.models.User;
import ru.innopolis.uni.course3.ofedorova.service.ConnectionPoolFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Класс для тестирования UserCreateServlet.
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 27.12.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPoolFactory.class, JdbcOfDAOtoUsers.class, ControllerForUsers.class})
public class UserCreateServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    ControllerForUsers controller;
    @Mock
    HttpSession session;
    @Mock
    JdbcOfDAOtoUsers jdbcOfDAOtoUsers;
    @Mock
    Connection connection;

    /**
     * Инициализация.
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        mockStatic(ConnectionPoolFactory.class);
        when(ConnectionPoolFactory.getConnection()).thenReturn(connection);
        mock(JdbcOfDAOtoUsers.class);
        whenNew(JdbcOfDAOtoUsers.class).withNoArguments().thenReturn(jdbcOfDAOtoUsers);
        mock(ControllerForUsers.class);
        whenNew(ControllerForUsers.class).withNoArguments().thenReturn(controller);
        when(request.getSession()).thenReturn(session);
    }

    /**
     * Метод проверяет вызов метода "doGet" и переход на страницу, информирующей о том, что пользователь уже авторизован.
     *
     * @throws Exception
     */
    @Test
    public void whenDoGetAndGoAuthorization() throws Exception {
        final User user = new User(1, "name", "password");

        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestDispatcher(String.format("%s%s", request.getContextPath(), "/info-about-authorization"))).thenReturn(dispatcher);

        final UserCreateServlet userCreateServlet = new UserCreateServlet();
        userCreateServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher(String.format("%s%s", request.getContextPath(), "/info-about-authorization"));

    }

    /**
     * Метод проверяет вызов метода "doGet" и переход на страницу регситрации.
     *
     * @throws Exception
     */
    @Test
    public void whenDoGetAndGoRegistration() throws Exception {
        final User user = null;

        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);

        final UserCreateServlet userCreateServlet = new UserCreateServlet();
        userCreateServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/registration.jsp");

    }

    /**
     * Метод проверяет вызов метода "doPost" и ситуацию, когда пользователь успешно создан.
     *
     * @throws Exception
     */
    @Test
    public void whenDoPostAndUserCreate() throws Exception {
        final String name = "test_name";
        final String password = "test_password";
        final User user = new User(1, name, password);

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("user_password")).thenReturn(password);
        when(request.getParameter("confirm_user_password")).thenReturn(password);
        when(jdbcOfDAOtoUsers.addNewUser(any(), any())).thenReturn(user);

        final UserCreateServlet userCreateServlet = new UserCreateServlet();
        userCreateServlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("user_password");
        verify(request, atLeastOnce()).getParameter("confirm_user_password");
        verify(jdbcOfDAOtoUsers, atLeastOnce()).addNewUser(name, password);
    }

    /**
     * Метод проверяет вызов метода "doPost" и ситуацию, когда пользователь не создан.
     *
     * @throws Exception
     */
    @Test
    public void whenDoPostAndUserNotCreate() throws Exception {
        final String name = "test_name";
        final String password = "test_password";
        final String confirm_password = "test_password1";
        final User user = new User(1, name, password);

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("user_password")).thenReturn(password);
        when(request.getParameter("confirm_user_password")).thenReturn(confirm_password);
        when(jdbcOfDAOtoUsers.addNewUser(any(), any())).thenReturn(user);
        when(request.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);

        final UserCreateServlet userCreateServlet = new UserCreateServlet();
        userCreateServlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("user_password");
        verify(request, atLeastOnce()).getParameter("confirm_user_password");
        verify(jdbcOfDAOtoUsers, atLeast(0)).addNewUser(name, password);
    }
}