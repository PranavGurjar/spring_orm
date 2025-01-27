package com.spring.orm.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import com.spring.orm.entities.Student;


import org.springframework.transaction.annotation.Transactional;


public class StudentDao {

    private HibernateTemplate hibernateTemplate;

    // Insert student - @Transactional required for write operations
    @Transactional
    public int insert(Student student) {
        Integer id = (Integer) this.hibernateTemplate.save(student);
        return id;
    }
    
    //get single data(object)
    public Student getStudent(int studentId) {
    	Student student = this.hibernateTemplate.get(Student.class, studentId);
    	return student;
    }
    
    //get All data(All row)
    public List<Student> getAllStudents(){
    	List<Student> students = this.hibernateTemplate.loadAll(Student.class);
    	return students;
    }

    //deleting the data
    public void deleteStudent(int studentId) {
    	Student student = this.hibernateTemplate.get(Student.class, studentId);
    	this.hibernateTemplate.delete(student);
    }
    
    //updating the data
    @Transactional
    public void updateStudent(Student student) {
    	this.hibernateTemplate.update(student);
    }
    
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
