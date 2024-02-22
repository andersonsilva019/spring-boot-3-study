package com.anderson.cruddemo;

import com.anderson.cruddemo.dao.StudentDAO;
import com.anderson.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 1;

		System.out.println("Deleting student with id: "+studentId);

		studentDAO.delete(1);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;

		System.out.println("Getting student with id: "+studentId);

		Student student = studentDAO.findById(studentId);

		System.out.println("Updating student...");

		student.setFirstName("And");
		studentDAO.update(student);

		System.out.println("Updated student: " + student);
	}

	private void queryStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Souza");

		for(Student student: students){
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for(Student student: students){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO){
		System.out.println("Creating new student object...");
		Student student = new Student("John", "Doe", "doe@doe.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());

		System.out.println("Retrieve student with id: " + student.getId());

		Student studentRetrieved = studentDAO.findById(student.getId());
		System.out.println("Found the student: " + studentRetrieved);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 students objects...");
		Student studentOne = new Student("Anderson 1", "Souza 1", "teste1@test.com");
		Student studentTwo = new Student("Anderson 2", "Souza 2", "teste2@test.com");
		Student studentThee = new Student("Anderson 3", "Souza 3", "teste3@test.com");

		System.out.println("Saving the student...");
		studentDAO.save(studentOne);
		studentDAO.save(studentTwo);
		studentDAO.save(studentThee);
	}

	private void createStudent(StudentDAO studentDAO){

		// create the student object
		System.out.println("Creating new student object...");
		Student student = new Student("Anderson", "Souza", "teste@test.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
