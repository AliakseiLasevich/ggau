package dao.interfaces;

import entity.Cathedra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CathedraRepository extends PagingAndSortingRepository<Cathedra, Long> {

    List<Cathedra> findByFacultyId(Long facultyId);
}
