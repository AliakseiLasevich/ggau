package app.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentCourseRequest {

    private String specialtyId;
    private int courseNumber;
}
