package app.dao.interfaces;


import app.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query(value = "SELECT distinct b FROM Building b INNER JOIN FETCH b.cabinets")
    List<Building> findAllWithCabinets();

    Building findByPublicIdAndActiveTrue(String publicId);

    Building findByNameAndActiveTrue(String name);
}
