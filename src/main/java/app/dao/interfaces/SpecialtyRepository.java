package app.dao.interfaces;


import app.entity.Faculty;
import app.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    List<Specialty> findAllByActiveTrue();

    Specialty findByPublicIdAndActiveTrue(String publicId);

    Specialty findByCodeAndActiveTrue(String code);

    List<Specialty> findByFacultyAndActiveTrue(Faculty faculty);
}
