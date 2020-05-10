package service;

import config.DataSourceConfig;
import dao.interfaces.CathedraRepository;
import entity.Cathedra;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import service.interfaces.CathedraService;

import java.util.List;

@SpringJUnitWebConfig(DataSourceConfig.class)
class CathedraServiceImplTest {

    @Autowired
    CathedraService cathedraService;

    @Autowired
    CathedraRepository cathedraRepository;

    @Test
    void findCathedrasWithFaculty(){
        List<Cathedra> cathedraList = cathedraRepository.cathedrasWithFaculty();
        System.out.println(cathedraList);
    }


}