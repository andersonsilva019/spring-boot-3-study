package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        List<Course> courses = tempInstructor.getCourses();

        for(Course course:courses){
            course.setInstructor(null);
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail "
                + "where i.id = :data", Instructor.class
        );

        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        this.entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        this.entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
         return this.entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course course = this.entityManager.find(Course.class, theId);

        this.entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCoursAndReviewsByCourseId(int courseId) {
        TypedQuery<Course> query = this.entityManager.createQuery(
                "select c from Course c "
                 + "JOIN FETCH c.reviews "
                 + "where c.id = :data",
                Course.class
        );

        query.setParameter("data", courseId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data",
                Course.class
        );

        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }
}
