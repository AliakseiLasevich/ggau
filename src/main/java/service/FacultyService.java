package service;

import dao.interfaces.FacultyDAO;
import entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyDAO facultyDAO;

    @Transactional
    public List<Faculty> findAll() {
        return facultyDAO.findAll();
    }

    @Transactional
    public Optional<Faculty> findById(Long id){
        return facultyDAO.findById(id);
    }

}
