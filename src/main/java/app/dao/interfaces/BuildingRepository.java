package app.dao.interfaces;


import app.model.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    List<Building> findAllByActiveTrue();

    Optional<Building> findByPublicIdAndActiveTrue(String publicId);

    Building findByNameAndActiveTrue(String name);
}
