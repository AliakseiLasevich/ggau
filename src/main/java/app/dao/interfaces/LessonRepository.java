package app.dao.interfaces;

import app.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByActiveTrue();

    @Query(value = "SELECT * FROM lessons as l, student_subgroups as subs, student_groups as gr, student_courses as c " +
            "WHERE c.id=gr.student_course_id " +
            "AND gr.id=subs.student_group_id " +
            "AND c.public_id=:courseId " +
            "AND l.date_time BETWEEN :firstDate AND :lastDate", nativeQuery = true)
    List<Lesson> findAllByStudentCourseAndDateRange(@Param("courseId") String courseId, LocalDate firstDate, LocalDate lastDate);


}
