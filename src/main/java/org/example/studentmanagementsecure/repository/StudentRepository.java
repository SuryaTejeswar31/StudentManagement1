package org.example.studentmanagementsecure.repository;

import org.example.studentmanagementsecure.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String email);
    Optional<Student> findByIdAndDeletedFalse(Long id);
    List<Student> findAllByDeletedFalse();
    List<Student> findAllByDepartmentAndDeletedFalse(String department);
}
