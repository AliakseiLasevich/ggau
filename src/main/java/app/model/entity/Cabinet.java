package app.model.entity;

import app.model.enums.CabinetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "cabinets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cabinet {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "app.common.CustomIdGenerator")
    private String id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "number")
    private String number;

    @Column(name = "max_students")
    private int maxStudents;

    @Column(name = "type")
    private CabinetType type;

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
