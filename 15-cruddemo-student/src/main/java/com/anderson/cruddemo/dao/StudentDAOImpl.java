package com.anderson.cruddemo.dao;

import com.anderson.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = this.entityManager.createQuery("FROM Student order by lastName desc", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = this.entityManager.createQuery(
                "FROM Student WHERE lastName =:lastNameValue",
                Student.class
        );

        query.setParameter("lastNameValue", lastName);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            Student student = this.entityManager.find(Student.class, id);

            if(student == null){
                throw new Exception("Student not found with id " + id);
            }

            this.entityManager.remove(student);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = this.entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
