package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;
import com.lin.hibernate.demo.entity.Review;
import com.lin.hibernate.demo.entity.Student;

public class AddCourseForJamesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Course.class)
				                 .addAnnotatedClass(Review.class)
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			System.out.println("Create Course object...");
			
			// create a course
			Course tempCourse = new Course("Math");
			
			// add some student
			System.out.println("Saving the Course...");
			session.save(tempCourse);
			System.out.println("Saved the Course...");

			Student tempStudent1 = new Student("Paul", "Wall" , "sdq@gmail.com");
			Student tempStudent2 = new Student("James", "Wall" , "James@gmail.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			System.out.println("\nSaving the Student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved the Student...");

			
			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

}
