package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "disciplines")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Discipline extends BaseEntity  implements GeneratedId {

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "DSPLN";
    }
}
