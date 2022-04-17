package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


/**
 * @Description
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    void addStu1(Student student);
}
