package app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    NO_FACULTY_FOUND("Данный факультет не найден."),
    NO_CATHEDRA_FOUND("Данная кафедра не найдена."),
    NO_TEACHER_FOUND("Преподаватель не найден."),
    NO_BUILDING_FOUND("Здание не найдено."),
    NO_CABINET_FOUND("Кабинет не найден."),
    NO_SPECIALTY_FOUND("Специальность не найдена."),
    NO_STUDENT_COURSE_FOUND("Студенческий курс не найден."),
    NO_STUDENT_GROUP_FOUND("Студенческая группа не найдена."),
    NO_STUDENT_SUBGROUP_FOUND("Студенческая подгруппа не найдена."),
    NO_DISCIPLINE_FOUND("Учебная дисциплина не найдена."),
    NO_LEARN_PLAN_FOUND("Учебный план не найден."),
    MISSING_REQUIRED_FIELD("Не достаточно данных. Проверьте правильность ввода."),
    RECORD_ALREADY_EXISTS("Запись уже содержится в базе данных: %s");

    private String errorMessage;
}
