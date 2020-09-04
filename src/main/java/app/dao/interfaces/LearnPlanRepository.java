package app.dao.interfaces;


import app.entity.LearnPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LearnPlanRepository extends JpaRepository<LearnPlan, Long> {

    LearnPlan findByPublicId(String publicId);


    @Query(value = "from LearnPlan l where :date between l.startDate and l.endDate and l.active=true")
    List<LearnPlan> findByDateInclude(@Param("date") LocalDate date);

    List<LearnPlan> findAllByActiveTrue();
}
