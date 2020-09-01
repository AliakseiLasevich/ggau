package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


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
