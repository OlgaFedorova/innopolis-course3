package ru.innopolis.uni.course3.ofedorova.controllers;

import ru.innopolis.uni.course3.ofedorova.dao.exceptions.DAOtoTasksException;
import ru.innopolis.uni.course3.ofedorova.dao.tasks.DAOtoTasks;
import ru.innopolis.uni.course3.ofedorova.dao.tasks.JdbcOfDAOtoTasks;
import ru.innopolis.uni.course3.ofedorova.models.Task;

import java.util.Collection;

/**
 * Класс реализует контроллер для работы с моделью данных "Task".
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 27.12.2016
 */
public class ControllerForTasks implements DAOtoTasks {

    /**
     * Объект для доступа к данным модели "Task".
     */
    private final DAOtoTasks storeOfTasks = new JdbcOfDAOtoTasks();

    /**
     * Метод возвращает список заданий в БД.
     *
     * @param idUser идентификатор пользователя.
     * @return список заданий в БД.
     */
    @Override
    public Collection<Task> values(int idUser) throws DAOtoTasksException {
        return this.storeOfTasks.values(idUser);
    }

    /**
     * Метод возвращает задание по переданному id.
     *
     * @param id     идентификатор задания.
     * @param idUser идентификатор пользователя.
     * @return задание найденное по id.
     */
    @Override
    public Task getById(int id, int idUser) throws DAOtoTasksException {
        return this.storeOfTasks.getById(id, idUser);
    }
}
