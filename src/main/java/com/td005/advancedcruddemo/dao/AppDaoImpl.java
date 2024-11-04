package com.td005.advancedcruddemo.dao;

import com.td005.advancedcruddemo.entity.Course;
import com.td005.advancedcruddemo.entity.Instructor;
import com.td005.advancedcruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AppDaoImpl implements AppDao{



    //define field for entity manager
    //inject entity manager using constructor injection
    private EntityManager entityManager;


    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }



    @Override
    public Instructor findInstructorById(int theId)
    {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    public void removeInstructor(int deleteId) {
        Instructor tempInstructor = entityManager.find(Instructor.class,deleteId);
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {

        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    public void deleteInstructorDetailById(int id) {
//        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
//        entityManager.remove(instructorDetail);

        //retreive the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class,id);
        List<Course> courses = tempInstructor.getCourses();

        //break associations off all courses for instructor
        //course ve ınstructor ilişkisini kopartır.
        for(Course tempCourse : courses)
            tempCourse.setInstructor(null);


        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:data",Course.class);
        query.setParameter("data",theId);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i"
                        +"JOIN FETCH i.courses"
                        +"JOIN FETCH i.instructorDetail"
                        +"where i.id = :data",Instructor.class
        );


        query.setParameter("data",theId);

        //execute query
        Instructor instructor = query.getSingleResult();
        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor instructor)
    {
        entityManager.merge(instructor);

    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    public void deleteCourse(int theId) {
        //finding delete course

        Course course = entityManager.find(Course.class,theId);

        entityManager.remove(course);
    }

//    @Override
//    @Transactional
//    public void deleteInstructor(int id)
//    {
//        //retreive the instructor
//        Instructor tempInstructor = entityManager.find(Instructor.class,id);
//        List<Course> courses = tempInstructor.getCourses();
//
//        //break associations off all courses for instructor
//        //course ve ınstructor ilişkisini kopartır.
//        for(Course tempCourse : courses)
//            tempCourse.setInstructor(null);
//
//
//        // delete the instructor
//        entityManager.remove(tempInstructor);
//    }


}
