package app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cabinets")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cabinet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "number")
    private String number;

    @Column(name = "max_students")
    private int maxStudents;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
