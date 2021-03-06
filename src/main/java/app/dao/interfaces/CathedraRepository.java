package app.dao.interfaces;

import app.entity.Cathedra;
import app.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CathedraRepository extends JpaRepository<Cathedra, Long> {

    Cathedra findByPublicIdAndActiveTrue(String publicId);

    @Query(value = "SELECT c FROM Cathedra c LEFT JOIN FETCH c.faculty WHERE c.active=true")
    List<Cathedra> findAllByActiveTrue();

    Cathedra findByNameAndActiveTrue(String name);

    List<Cathedra> findAllByFaculty(Faculty faculty);

}