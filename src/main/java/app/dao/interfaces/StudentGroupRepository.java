package app.dao.interfaces;


import app.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    List<StudentGroup> findAllByActiveTrue();

    StudentGroup findByPublicIdAndActiveTrue(String publicId);

}
