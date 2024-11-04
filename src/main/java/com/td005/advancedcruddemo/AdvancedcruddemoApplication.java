package com.td005.advancedcruddemo;

import com.td005.advancedcruddemo.dao.AppDao;
import com.td005.advancedcruddemo.entity.Course;
import com.td005.advancedcruddemo.entity.Instructor;
import com.td005.advancedcruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedcruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao)
	{
		return runner -> {
//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructor(appDao);
			//findInstructorDetail(appDao);
//			deleteInstructorDetail(appDao);
			//createInsturctorWithCourses(appDao);
	//		findInstructorWithCourses(appDao);
			//findCoursesForInstructor(appDao);
			//findInstructorWithCoursesJoinFetch(appDao);

//			updateInstructor(appDao);
//			updateCourse(appDao);
			deleteCourse(appDao);


		};

	}

	private void deleteCourse(AppDao appDao)
	{
		int deleteCourseId = 10;
		System.out.println("Delete course id : "+deleteCourseId);

		appDao.deleteCourse(deleteCourseId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDao appDao)
	{
		int theId = 10;

		System.out.println("Finding course id : " + theId);
		Course course = appDao.findCourseById(theId);

		System.out.println("Updating course id : " + theId);
		course.setTitle("Enjoy The simple Things");

		appDao.update(course);
		System.out.println("Done!");

	}

	private void updateInstructor(AppDao appDao)
	{
		int id = 1;
		//find the instructor
		System.out.println("Finding instructor id : " + id);
		Instructor instructor = appDao.findInstructorById(id);
		//update the instructor
		System.out.println("Update instructor id : " + id);
		instructor.setLastName("TESTER");
		appDao.update(instructor);

		System.out.println("Done!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao)
	{
		int theId = 2;

		//find the instructor
		System.out.println("Finding insturctor id :" + theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDao appDao)
	{
		int theId = 1;
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor : " + tempInstructor);

		//find cıyrses for instructor
		System.out.println("Finding courses for instructor id : " + theId);
		List<Course> courses = appDao.findCoursesByInstructorId(theId);
		//associate the boject
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses : " +tempInstructor.getCourses());

		System.out.println("Done!");


	}


	private void findInstructorWithCourses(AppDao appDao)
	{
		int theId = 1;
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");


	}
	private void createInsturctorWithCourses(AppDao appDao)
	{
		//create the insturctor

		Instructor instructor = new Instructor("Doga","Dogan","dogandoga@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("TalkingYoutube","Video Games");

		instructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The PaintBall Masterclass");


		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		//save the instructor
		System.out.println("Saving instructor : " + instructor);
		System.out.println("The courses " + instructor.getCourses());
		appDao.save(instructor);

	}
	private void createInstructor(AppDao appDao)
	{
		//create the insturctor

		Instructor instructor = new Instructor("Doga","Dogan","toprakdgn@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("TalkingYoutube","Talking");

		instructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The PaintBall Masterclass");


		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		//save the instructor
		System.out.println("Saving instructor : " + instructor);
		System.out.println("The courses " + instructor.getCourses());
		appDao.save(instructor);
	}

	private void findInstructor(AppDao appDao)
	{
		int theId = 2;
		System.out.println("finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("Instructor : " + tempInstructor);
		System.out.println("the associate instructorDetail only : " + tempInstructor.getInstructorDetail());

	}

	public void deleteInstructor(AppDao appDao)
	{
		int id = 1;
		System.out.println("Deleting instructor id : " + id);
		appDao.removeInstructor(id);
		System.out.println("Done!");


	}
	private void findInstructorDetail(AppDao appDao)
	{
		//get the insturcot detail object
		int id = 3;
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);

		System.out.println("TempInstructorDetail : " + instructorDetail);

		System.out.println("The assıcşated instructor : " + instructorDetail.getInstructor());

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDao appDao)
	{
		int id = 2;
		System.out.println("Deleting instructor detail id: "+id);
		appDao.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

}
