package ru.innopolis.uni.course3.ofedorova.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.uni.course3.ofedorova.controllers.ControllerForUsers;
import ru.innopolis.uni.course3.ofedorova.dao.users.JdbcOfDAOtoUsers;
import ru.innopolis.uni.course3.ofedorova.services.ConnectionPoolFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Класс для тестирования MainServlet.
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 27.12.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPoolFactory.class, JdbcOfDAOtoUsers.class, ControllerForUsers.class})
public class MainServletTest {

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
     * Метод тестирует вызов метода "doGet".
     *
     * @throws Exception
     */
    @Test
    public void whenDoGet() throws Exception {
        when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);

        final MainServlet mainServlet = new MainServlet();
        mainServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("index.jsp");
    }

}