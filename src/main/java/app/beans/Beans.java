package app.beans;

import app.converters.BuildingMapper;
import app.converters.BuildingMapperImpl;
import app.converters.CabinetMapper;
import app.converters.CabinetMapperImpl;
import app.converters.CathedraMapper;
import app.converters.CathedraMapperImpl;
import app.converters.DisciplineMapper;
import app.converters.DisciplineMapperImpl;
import app.converters.FacultyMapper;
import app.converters.FacultyMapperImpl;
import app.converters.TeacherMapper;
import app.converters.TeacherMapperImpl;
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
