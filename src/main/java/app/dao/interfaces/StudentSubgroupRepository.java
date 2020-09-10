package app.dao.interfaces;


import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubgroupRepository extends JpaRepository<StudentSubgroup, Long> {

    StudentSubgroup findByNameAndStudentGroupAndActiveTrue(String name, StudentGroup studentGroup);

}
