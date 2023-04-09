package app.dao.interfaces;


import app.model.entity.Building;
import app.model.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {

    List<Cabinet> findByActiveTrueAndBuildingActiveTrue();

    Optional<Cabinet> findByPublicIdAndActiveTrue(String publicId);

    Cabinet findByNumberAndBuildingAndActiveTrue(String number, Building building);

}
