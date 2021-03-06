package ru.innopolis.uni.course3.ofedorova.common.controllers;

import ru.innopolis.uni.course3.ofedorova.common.models.Student;
import ru.innopolis.uni.course3.ofedorova.dao.students.DAOForStudent;

import java.util.Collection;

/**
 * Created by Olga on 22.12.2016.
 */
public class StudentController implements AbstractStudentController {

    static final long SerialVersionUID = 1L;

    private DAOForStudent daoForStudent;

    public void setDaoForStudent(DAOForStudent daoForStudent) {
        this.daoForStudent = daoForStudent;
    }

    @Override
    public Collection<Student> getStudents() {
        return this.daoForStudent.getStudents();
    }

    @Override
    public int add(Student student) {
        return this.daoForStudent.add(student);
    }

    @Override
    public void edit(Student student) {
        this.daoForStudent.edit(student);
    }

    @Override
    public void delete(int id) {
        this.daoForStudent.delete(id);
    }

    @Override
    public Student getStudent(int id) {
        return this.daoForStudent.getStudent(id);
    }

    public int generateId() {
        return 0;
    }
}
