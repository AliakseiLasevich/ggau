package app.dao.interfaces;

import app.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT t FROM Teacher t LEFT JOIN FETCH t.cathedra WHERE t.active=true AND t.cathedra.active=true ")
    List<Teacher> findAllWithCathedras();

    Optional<Teacher> findByPublicIdAndActiveTrue(String publicId);

    @Query(value = """
            SELECT t FROM Teacher t
                INNER JOIN Lesson l ON t.id = l.teacher.id
                    WHERE t.id = :teacherId
                     AND l.orderNumber = :orderNumber
                     AND l.date = :date
            """)
    Optional<Teacher> findByParameters(@Param("teacherId") String id, @Param("orderNumber") int orderNumber, @Param("date") LocalDate date);

//    @Query("""
//            SELECT t FROM Teacher c
//            INNER JOIN Lesson l ON c.id = t.cabinet.id
//            WHERE c.id = :cabinetId
//            AND l.orderNumber = :orderNumber
//            AND l.date = :date
//            """)
//    Optional<Teacher> getByParameters(
//            @Param("teacherId") String teacherId,
//            @Param("orderNumber") int orderNumber,
//            @Param("date") LocalDate date
//    );

}
