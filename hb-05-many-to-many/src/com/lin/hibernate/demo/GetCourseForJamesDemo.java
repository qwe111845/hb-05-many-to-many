package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;
import com.lin.hibernate.demo.entity.Review;
import com.lin.hibernate.demo.entity.Student;

public class GetCourseForJamesDemo {

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
			System.out.println("Get Student object...");
			
			int theId = 2;
			
			// get student james
			Student tempStudent = session.get(Student.class, theId);

			// add some student
			System.out.println("Loaded the Student: " + tempStudent);
			System.out.println("Course: " + tempStudent.getCourses());

			
			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

}
