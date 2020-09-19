package app.dao.interfaces;


import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubgroupRepository extends JpaRepository<StudentSubgroup, Long> {

    StudentSubgroup findByNameAndStudentGroupAndActiveTrue(String name, StudentGroup studentGroup);

    StudentSubgroup findByPublicIdAndActiveTrue(String publicId);

    List<StudentSubgroup> findAllByStudentGroupAndActiveTrue(StudentGroup studentGroup);

    @Query(value = "SELECT * from student_subgroups as subgr INNER JOIN " +
            "(SELECT gr.id FROM student_groups AS gr INNER JOIN " +
            "student_courses AS courses  on gr.student_course_id=courses.id" +
            " WHERE courses.public_id=:courseId) " +
            "as groupz on subgr.student_group_id=groupz.id", nativeQuery = true)
    List<StudentSubgroup> findAllByStudentCourseByQuery(@Param("courseId") String courseId);


}
