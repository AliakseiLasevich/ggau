package app.dao.interfaces;


import app.entity.DisciplinePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinePlanRepository extends JpaRepository<DisciplinePlan, Long> {


}
