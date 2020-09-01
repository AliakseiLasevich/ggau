package app.dao.interfaces;


import app.entity.LearnPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnPlanRepository extends JpaRepository<LearnPlan, Long> {


}
