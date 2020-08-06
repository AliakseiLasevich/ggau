package app.dao.interfaces;

import app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT t FROM Teacher t LEFT JOIN FETCH t.cathedra WHERE t.active=true AND t.cathedra.active=true ")
    List<Teacher> findAllWithCathedras();

    Teacher findByPublicIdAndActiveTrue(String publicId);

}
