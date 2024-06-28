package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetailById(appDAO);
			deleteInstructorDetail(appDAO);
		};
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
		int theId = 1;

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
