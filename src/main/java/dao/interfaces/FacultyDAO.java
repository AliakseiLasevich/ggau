package dao.interfaces;

import entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDAO extends JpaRepository<Faculty, Long> {

}
