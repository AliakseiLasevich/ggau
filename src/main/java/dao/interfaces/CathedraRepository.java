package dao.interfaces;

import entity.Cathedra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CathedraRepository extends JpaRepository<Cathedra, Long> {

    @Query(value = "SELECT c FROM Cathedra c  JOIN FETCH c.faculty WHERE c.faculty.id= ?1")
    List<Cathedra> findByFacultyId(Long facultyId);

    @Query(value = "SELECT c FROM Cathedra c LEFT JOIN FETCH c.faculty")
    List<Cathedra> cathedrasWithFaculty();
}
