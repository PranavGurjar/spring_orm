package com.spring.orm.dao;

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

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
