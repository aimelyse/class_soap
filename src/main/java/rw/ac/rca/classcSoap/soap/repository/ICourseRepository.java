package rw.ac.rca.classcSoap.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ac.rca.classcSoap.soap.bean.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer>{

}
