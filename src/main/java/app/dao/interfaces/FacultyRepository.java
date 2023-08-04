package app.dao.interfaces;

import app.model.entity.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends PagingAndSortingRepository<Faculty, String>, CrudRepository<Faculty, String> {
    List<Faculty> findAllByActiveTrue();

    Faculty findByNameAndActiveTrue(String name);

    Optional<Faculty> findByPublicIdAndActiveTrue(String publicId);
}
