package app.dao.interfaces;

import app.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT t FROM Teacher t LEFT JOIN FETCH t.cathedra WHERE t.active=true AND t.cathedra.active=true ")
    List<Teacher> findAllWithCathedras();

    Teacher findByPublicIdAndActiveTrue(String publicId);

}
