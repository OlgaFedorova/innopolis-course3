package ru.innopolis.uni.course3.ofedorova.services.main;

import ru.innopolis.uni.course3.ofedorova.dao.decisions.DAOtoDecisions;
import ru.innopolis.uni.course3.ofedorova.dao.exceptions.DAOtoDecisionsException;
import ru.innopolis.uni.course3.ofedorova.dao.exceptions.DAOtoMarksException;
import ru.innopolis.uni.course3.ofedorova.dao.marks.DAOtoMarks;
import ru.innopolis.uni.course3.ofedorova.services.marks.ServiceOfMarks;

/**
 * Класс реализует главный сервис для работы с моделью данных "Decision".
 *
 * @author Olga Fedorova
 * @version 1.0
 * @since 27.12.2016
 */
public class MainServiceForDecisionsAndMarks implements DAOtoDecisions {

    /**
     * Объект для доступа к данным модели "Decision".
     */
    private final DAOtoDecisions daOtoDecisions;
    /**
     * Объект для доступа к данным модели "Mark".
     */
    private final DAOtoMarks daOtoMarks;
    /**
     * Сервисный объект для выставления оценок пользователю.
     */
    private final ServiceOfMarks serviceOfMarks;

    /**
     * Создает новый {@code MainServiceForDecisionsAndMarks}.
     *
     * @param storeOfDecisions значение поля "daOtoDecisions".
     * @param storeOfMarks     значение поля "daOtoMarks".
     * @param serviceOfMarks   значение поля "serviceOfMarks".
     */
    public MainServiceForDecisionsAndMarks(DAOtoDecisions storeOfDecisions, DAOtoMarks storeOfMarks, ServiceOfMarks serviceOfMarks) {
        this.daOtoDecisions = storeOfDecisions;
        this.daOtoMarks = storeOfMarks;
        this.serviceOfMarks = serviceOfMarks;
    }

    /**
     * Метод добавляет решение пользователя в систему и выставляет оценку..
     *
     * @param idTask   идентификатор задачи.
     * @param idUser   идентификатор пользователя.
     * @param decision текст решения.
     */
    @Override
    public void add(int idTask, int idUser, String decision) throws DAOtoDecisionsException, DAOtoMarksException {
        this.daOtoDecisions.add(idTask, idUser, decision);
        this.daOtoMarks.add(idTask, idUser, this.serviceOfMarks.getMark());
    }
}