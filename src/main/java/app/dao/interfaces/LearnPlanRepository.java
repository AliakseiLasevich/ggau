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

    @Query(value = "from LearnPlan where startDate between :firstDate and :secondDate AND endDate between :firstDate and :secondDate")
    List<LearnPlan> findByDateRange(@Param("firstDate") LocalDate firstDate, @Param("secondDate") LocalDate secondDate);

    List<LearnPlan> findAllByActiveTrue();
}
