package app.dao.interfaces;


import app.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    Discipline findByPublicIdAndActiveTrue(String publicId);

    List<Discipline> findAllByActiveTrue();
}
