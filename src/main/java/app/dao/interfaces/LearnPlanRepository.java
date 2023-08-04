package app.dao.interfaces;


import app.model.entity.LearnPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LearnPlanRepository extends JpaRepository<LearnPlan, String> {

    LearnPlan findByPublicId(String publicId);


    @Query(value = "from LearnPlan l where :date between l.startDate and l.endDate and l.active=true")
    List<LearnPlan> findByDateInclude(@Param("date") LocalDate date);

    @Query(value = "from LearnPlan l where :date between l.startDate and l.endDate and l.active=true AND l.studentCourse.publicId = :studentCourseId")
    LearnPlan findByDateIncludeAndStudentCourse(@Param("date") LocalDate date,
                                                @Param("studentCourseId") String studentCourseId);

    List<LearnPlan> findAllByActiveTrue();
}
