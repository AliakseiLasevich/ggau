package app.entity;

import app.entity.Interfaces.EntityInterface;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "cabinets")
@Getter
@Setter
@NoArgsConstructor
public class Cabinet implements Serializable, EntityInterface {

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
