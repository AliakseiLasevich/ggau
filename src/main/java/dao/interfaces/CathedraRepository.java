package dao.interfaces;

import entity.Cathedra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CathedraRepository extends PagingAndSortingRepository<Cathedra, Long> {
//    Cathedra findByName(String name);
//
//    List<Cathedra> findAll(int page, int limit);
}
