package dao.interfaces;


import entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    @Query(value = "SELECT s FROM StudentGroup s LEFT JOIN FETCH s.specialty LEFT JOIN FETCH s.studentSubgroups")
    List<StudentGroup> findAllWithSpecialtyAndSubgroups();

}
