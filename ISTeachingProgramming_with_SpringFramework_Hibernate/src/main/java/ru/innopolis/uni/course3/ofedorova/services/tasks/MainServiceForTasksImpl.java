package ru.innopolis.uni.course3.ofedorova.services.tasks;

import ru.innopolis.uni.course3.ofedorova.dao.exceptions.DAOtoTasksException;
import ru.innopolis.uni.course3.ofedorova.dao.tasks.DAOtoTasks;
import ru.innopolis.uni.course3.ofedorova.models.Task;

import java.util.Collection;

/**
 * Класс реализует главный сервис для работы с моделью данных "Task".
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 27.12.2016
 */
public class MainServiceForTasksImpl implements MainServiceForTasks {

    /**
     * Объект для доступа к данным модели "Task".
     */
    private final DAOtoTasks daOtoTasks;

    /**
     * Создает новый {@code MainServiceForTasksImpl}.
     *
     * @param daOtoTasks значение поля "daOtoTasks".
     */
    public MainServiceForTasksImpl(DAOtoTasks daOtoTasks) {
        this.daOtoTasks = daOtoTasks;
    }

    /**
     * Метод возвращает список заданий в БД.
     *
     * @param idUser идентификатор пользователя.
     * @return список заданий в БД.
     */
    @Override
    public Collection<Object[]> values(int idUser) throws DAOtoTasksException {
        return this.daOtoTasks.values(idUser);
    }

    /**
     * Метод возвращает задание по переданному id.
     *
     * @param id идентификатор задания.
     * @return задание найденное по id.
     */
    @Override
    public Task getById(int id) throws DAOtoTasksException {
        return this.daOtoTasks.getById(id);
    }
}
