package com.student.repository;

import com.student.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA generates the SQL (INSERT, SELECT, UPDATE, DELETE) for us.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // SELECT * FROM students WHERE LOWER(name) LIKE LOWER('%keyword%')
    List<Student> findByNameContainingIgnoreCase(String keyword);

    boolean existsByEmail(String email);
}
