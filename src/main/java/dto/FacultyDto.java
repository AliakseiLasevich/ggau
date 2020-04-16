package dto;

import lombok.Data;

@Data
public class FacultyDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
