package app.dao.interfaces;


import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubgroupRepository extends JpaRepository<StudentSubgroup, Long> {

    StudentSubgroup findByNameAndStudentGroupAndActiveTrue(String name, StudentGroup studentGroup);

    StudentSubgroup findByPublicIdAndActiveTrue(String publicId);

    List<StudentSubgroup> findAllByStudentGroupAndActiveTrue(StudentGroup studentGroup);

}
