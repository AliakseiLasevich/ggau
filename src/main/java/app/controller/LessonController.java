package app.controller;

import app.exception.LessonException;
import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.service.LessonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    public LessonResponse createLesson(@RequestBody @Valid LessonRequest lessonRequest) throws JsonProcessingException {
        return lessonService.createLesson(lessonRequest);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<LessonResponse> getLessonsBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastDate) {
        validateDates(firstDate, lastDate);
        return lessonService.getLessonsBetweenDates(firstDate, lastDate);
    }

    private void validateDates(LocalDate firstDate, LocalDate lastDate) {
        if (firstDate.getDayOfWeek() != DayOfWeek.MONDAY || lastDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            throw new LessonException("FirstDay дожен быть Понедельник, lastDay - воскресенье");
        }
        if (firstDate.plusDays(6).compareTo(lastDate) != 0) {
            throw new LessonException("Выбранные дни должны быть в рамках одной недели");
        }
    }

}
