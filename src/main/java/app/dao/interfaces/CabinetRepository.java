package app.dao.interfaces;


import app.entity.Building;
import app.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {

    List<Cabinet> findByActiveTrueAndBuildingActiveTrue();

    Cabinet findByPublicIdAndActiveTrue(String publicId);

    List<Cabinet> findAllByBuildingAndActiveTrue(Building building);

    Cabinet findByNumberAndBuildingAndActiveTrue(String number, Building building);

}
