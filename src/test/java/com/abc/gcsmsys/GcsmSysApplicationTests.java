package com.abc.gcsmsys;

import cn.hutool.crypto.SecureUtil;
import com.abc.gcsmsys.domain.HouseIdInfoVo;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.Student;
import com.abc.gcsmsys.mapper.HouseHoldMapper;
import com.abc.gcsmsys.mapper.ResidentMapper;
import com.abc.gcsmsys.service.ResidentService;
import com.abc.gcsmsys.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Slf4j
class GcsmSysApplicationTests{


    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }

    @Autowired
    private StudentService studentService;

    @Test
    public void saveStu(){

        Student stu = new Student();
        stu.setStuName("欧利");
        stu.setStuHeight(173.0);
        studentService.addStu1(stu);
    }

    @Test
    public void sele(){

//        List<Student> students = studentService.loadStu();
//        students.forEach(System.out::println);

    }

    @Test
    public void test1(){

        String i  =  "admin";
        System.out.println(SecureUtil.md5(i));
    }

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private ResidentService residentService;

    @Test
    public void test2(){

        List<Resident> residents = residentService.loadResident();
        for (Resident s:residents)
            System.out.println(s);
    }




}
