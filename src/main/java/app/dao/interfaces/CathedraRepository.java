package app.dao.interfaces;

import app.model.entity.Cathedra;
import app.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CathedraRepository extends JpaRepository<Cathedra, Long> {

    Optional<Cathedra> findByPublicIdAndActiveTrue(String publicId);

    //    @Query(value = "SELECT c FROM Cathedra c LEFT JOIN FETCH c.faculty WHERE c.active=true")
    List<Cathedra> findAllByActiveTrue();

    Cathedra findByNameAndActiveTrue(String name);

    List<Cathedra> findAllByFaculty(Faculty faculty);

}