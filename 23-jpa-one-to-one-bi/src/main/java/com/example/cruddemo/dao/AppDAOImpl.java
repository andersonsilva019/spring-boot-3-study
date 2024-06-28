package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{
    @Autowired
    private EntityManager entityManager;
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        this.entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) throws RuntimeException  {

        Instructor tempInstructor = this.entityManager.find(Instructor.class, theId);

        if(tempInstructor == null){
            throw new RuntimeException("Not found instructor with id: "+ theId);
        }

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return this.entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, theId);

        instructorDetail.getInstructor().setInstructorDetail(null);

        System.out.println("Aqui" + instructorDetail.getInstructor());

        this.entityManager.remove(instructorDetail);
    }
}
