package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.domain.Student;
import com.abc.gcsmsys.mapper.StudentMapper;
import com.abc.gcsmsys.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStu1(Student student) {
//        studentMapper.insert(student);
        studentMapper.addStu1(student);
    }

    @Override
    public List<Student> loadStu() {
//        QueryWrapper<Student> qw = new QueryWrapper<>();
        return studentMapper.selectList(null);
    }
}
