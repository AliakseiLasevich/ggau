package app.dao.interfaces;

import app.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByActiveTrue();

    @Query(value = """
            SELECT * FROM lessons_student_subgroups as lss
                LEFT OUTER JOIN lessons as l on l.id=lss.lesson_id
                LEFT OUTER JOIN student_subgroups as ss on ss.id=lss.students_subgroup_id
                INNER JOIN student_groups as sg on sg.id=ss.student_group_id
                INNER JOIN student_courses as sc on sg.student_course_id=sc.id
                WHERE sc.public_id= :courseId
                AND l.date_time BETWEEN :firstDate AND :lastDate
                GROUP BY lss.lesson_id
            """, nativeQuery = true)

    List<Lesson> findAllByStudentCourseAndDateRange(@Param("courseId") String courseId,
                                                    @Param("firstDate") LocalDate firstDate,
                                                    @Param("lastDate") LocalDate lastDate);


}
