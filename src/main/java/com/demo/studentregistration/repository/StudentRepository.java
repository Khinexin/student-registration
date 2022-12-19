package com.demo.studentregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.studentregistration.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
