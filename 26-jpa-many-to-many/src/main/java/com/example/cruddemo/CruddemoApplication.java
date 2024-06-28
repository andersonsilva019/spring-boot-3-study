package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createCourseAndStudent(appDAO);

			findCourseAndStudents(appDAO);
		};
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;

		Course course = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: "+course);
		System.out.println("associated students: "+course.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		Course course = new Course("Spring boot");

		course.addStudent(new Student("Anderson", "Silva", "and@and.com"));
		course.addStudent(new Student("Teste", "Teste lastname", "and3@and.com"));

		System.out.println("Saving the course: "+course);
		System.out.println("associated students: "+course.getStudents());
		appDAO.save(course);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int courseId = 10;

		appDAO.deleteCourseById(courseId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int courseId = 10;

		Course course = appDAO.findCoursAndReviewsByCourseId(courseId);

		System.out.println(course);

		System.out.println(course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		var course = new Course("Spring boot avançado");

		course.addReview(new Review("Excelente"));
		course.addReview(new Review("Ótimo"));

		System.out.println(course);

		appDAO.save(course);
		System.out.println("Done");
	}

	private void deleteCourse(AppDAO appDAO) {
		int courseId = 12;

		appDAO.deleteCourseById(courseId);
	}

	private void updateCourse(AppDAO appDAO) {
		int courseId = 11;

		Course course = appDAO.findCourseById(courseId);

		course.setTitle("React");

		appDAO.updateCourse(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 2;

		Instructor instructor = appDAO.findInstructorById(theId);

		instructor.setFirstName("Mari");

		appDAO.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: "+theId);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor > " +instructor);
		System.out.println("The associated courses > "+instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: "+theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor > " +instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		instructor.setCourses(courses);

		System.out.println("The associated courses > "+instructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourse(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: "+theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor > " +instructor);
		System.out.println("The associated courses > "+instructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor theInstructor = new Instructor("Susan", "Souza", "test@test.com");

		InstructorDetail instructorDetail = new InstructorDetail("canal_do_youtube_new", "futebol");

		theInstructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Spring");
		Course course2 = new Course("Java");

		theInstructor.add(course1);
		theInstructor.add(course2);

		// Save the instructor
		System.out.println("Saving instructor: "+theInstructor);
		System.out.println("The courses: "+theInstructor.getCourses());

		appDAO.save(theInstructor);
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theInstructorDetailId = 8;

		appDAO.deleteInstructorDetailById(theInstructorDetailId);
	}

	private void findInstructorDetailById(AppDAO appDAO) {
		int theInstructorDetailId = 2;

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theInstructorDetailId);

		System.out.println("InstructorDetail" + instructorDetail);

		System.out.println("Instructor" + instructorDetail.getInstructor());

	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 2;

		appDAO.deleteInstructorById(theId);
	}


	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		if(tempInstructor == null){
			throw new RuntimeException("Not found instructor with id "+theId);
		}

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate instructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor theInstructor = new Instructor("Anderson", "Souza", "test@test.com");

		InstructorDetail instructorDetail = new InstructorDetail("canal_do_youtube", "futebol");

		theInstructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor:" +theInstructor);

		appDAO.save(theInstructor);
	}

}
