package app.dao.interfaces;


import app.model.entity.Building;
import app.model.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, String> {

    List<Cabinet> findByActiveTrueAndBuildingActiveTrue();

    Optional<Cabinet> findByPublicIdAndActiveTrue(String publicId);

    Cabinet findByNumberAndBuildingAndActiveTrue(String number, Building building);

    @Query("""
            SELECT c FROM Cabinet c
            INNER JOIN Lesson l ON c.id = l.cabinet.id
            WHERE c.id = :cabinetId
            AND l.orderNumber = :orderNumber
            AND l.date = :date
            """)
    Optional<Cabinet> getByParameters(
            @Param("cabinetId") String cabinetId,
            @Param("orderNumber") int orderNumber,
            @Param("date") LocalDate date
    );

}
