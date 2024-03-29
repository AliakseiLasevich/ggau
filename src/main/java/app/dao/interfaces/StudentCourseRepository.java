package app.dao.interfaces;


import app.model.entity.Faculty;
import app.model.entity.Specialty;
import app.model.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, String> {

    List<StudentCourse> findAllByActiveTrue();

    List<StudentCourse> findAllBySpecialty_FacultyAndActiveTrue(Faculty faculty);
    List<StudentCourse> findAllBySpecialty(Specialty specialty);

    StudentCourse findByCourseNumberAndSpecialtyAndActiveTrue(int courseNumber, Specialty specialty);

    StudentCourse findByPublicIdAndActiveTrue(String publicId);
}
