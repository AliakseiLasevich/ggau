package app.dao.interfaces;

import app.entity.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long>, CrudRepository<Faculty, Long> {
    List<Faculty> findAllByActiveTrue();

    Faculty findByNameAndActiveTrue(String name);

    Faculty findByPublicIdAndActiveTrue(String publicId);
}
