package app.dao.interfaces;


import app.entity.Specialty;
import app.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findAllByActiveTrue();

    StudentCourse findByCourseNumberAndSpecialtyAndActiveTrue(int courseNumber, Specialty specialty);

    StudentCourse findByPublicIdAndActiveTrue(String publicId);
}
