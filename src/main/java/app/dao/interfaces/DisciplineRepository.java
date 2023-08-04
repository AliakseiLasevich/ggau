package app.dao.interfaces;


import app.model.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {

    Optional<Discipline> findByPublicIdAndActiveTrue(String publicId);

    List<Discipline> findAllByActiveTrue();
}
