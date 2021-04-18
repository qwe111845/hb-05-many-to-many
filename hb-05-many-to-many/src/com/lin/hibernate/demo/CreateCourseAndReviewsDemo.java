package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;
import com.lin.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Course.class)
				                 .addAnnotatedClass(Review.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			System.out.println("Create Course object...");
			
			// create a course
			Course tempCourse = new Course("Math");
			
			// add some reviews
			tempCourse.addReview(new Review("Choose this course is not a good choice"));
			tempCourse.addReview(new Review("Choose this course is a good choice"));
			tempCourse.addReview(new Review("Nice Course"));
			
			System.out.println("Saving the Course...");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
			
			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

}
