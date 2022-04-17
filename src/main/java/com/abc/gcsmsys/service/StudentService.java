package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Student;

import java.util.List;

/**
 * @Description
 */
public interface StudentService {

    void addStu1(Student student);

    List<Student> loadStu();
}
