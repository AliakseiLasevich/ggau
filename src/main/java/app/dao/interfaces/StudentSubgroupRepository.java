package app.dao.interfaces;


import app.model.entity.StudentGroup;
import app.model.entity.StudentSubgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubgroupRepository extends JpaRepository<StudentSubgroup, String> {

    StudentSubgroup findByNameAndStudentGroupAndActiveTrue(String name, StudentGroup studentGroup);

    StudentSubgroup findByPublicIdAndActiveTrue(String publicId);


    @Query(value = """
            SELECT ss FROM StudentSubgroup ss
            WHERE ss.publicId IN (:publicIds)
            AND ss.active = true
            """)
    List<StudentSubgroup> findByPublicIdsAndActiveTrue(List<String> publicIds);

    List<StudentSubgroup> findAllByStudentGroupAndActiveTrue(StudentGroup studentGroup);

//    @Query(value = """
//            SELECT ss FROM StudentSubgroup ss
//                INNER JOIN lesson_student_subgroups lss ON ss.id=lss.student.subgroup.id
//                INNER JOIN lessons l ON l.id=lss.lesson_id
//                    WHERE ss.id IN (:studentSubgroupIds)
//                     AND l.orderNumber = :orderNumber
//                     AND l.date = :date
//            """, nativeQuery = true)
//    List<StudentSubgroup> findByParameters(@Param("studentSubgroupIds") List<String> studentSubgroupIds, int orderNumber, LocalDate date);
//@Query("""
//        SELECT ss FROM student_subgroups ss
//        INNER JOIN ss.lessonStudentSubgroups lss
//        INNER JOIN lss.lesson l
//        WHERE ss.id IN :studentSubgroupIds
//        AND l.orderNumber = :orderNumber
//        AND l.date = :date
//        """)
//List<StudentSubgroup> findByParameters(
//        @Param("studentSubgroupIds") List<String> studentSubgroupIds,
//        @Param("orderNumber") int orderNumber,
//        @Param("date") LocalDate date
//);
}
