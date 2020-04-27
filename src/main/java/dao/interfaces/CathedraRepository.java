package dao.interfaces;

import entity.Cathedra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CathedraRepository extends PagingAndSortingRepository<Cathedra, Long> {

}
