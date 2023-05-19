package com.pet.studentmanagmentsystem.repository;

import com.pet.studentmanagmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
