package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "teachers")
@Data
public class Teacher implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cathedra_id")
    private Cathedra cathedra;

    @Column(name = "note")
    private String note;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
