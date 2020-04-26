package dao.interfaces;

import entity.Faculty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long> {
    Faculty findByName(String name);
}
