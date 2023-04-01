package app.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@Table(name = "disciplines")
@Data
public class Discipline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
