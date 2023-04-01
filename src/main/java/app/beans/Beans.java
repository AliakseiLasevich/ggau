package app.beans;

import app.model.mapper.BuildingMapper;
import app.model.mapper.BuildingMapperImpl;
import app.model.mapper.CabinetMapper;
import app.model.mapper.CabinetMapperImpl;
import app.model.mapper.CathedraMapper;
import app.model.mapper.CathedraMapperImpl;
import app.model.mapper.DisciplineMapper;
import app.model.mapper.DisciplineMapperImpl;
import app.model.mapper.FacultyMapper;
import app.model.mapper.FacultyMapperImpl;
import app.model.mapper.TeacherMapper;
import app.model.mapper.TeacherMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {

    @Bean
    BuildingMapper buildingMapper() {
        return new BuildingMapperImpl();
    }

    @Bean
    CabinetMapper cabinetMapper() {
        return new CabinetMapperImpl();
    }

    @Bean
    CathedraMapper cathedraMapper() {
        return new CathedraMapperImpl() {
        };
    }

    @Bean
    DisciplineMapper disciplineMapper() {
        return new DisciplineMapperImpl() {
        };
    }

    @Bean
    FacultyMapper facultyMapper() {
        return new FacultyMapperImpl() {
        };
    }

    @Bean
    TeacherMapper teacherMapper() {
        return new TeacherMapperImpl() {
        };
    }

}
