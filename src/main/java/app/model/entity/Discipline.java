package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "disciplines")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discipline implements GeneratedId {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "app.common.CustomIdGenerator")
    private String id;

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

    @Override
    public String getPrefix() {
        return "DSPLN";
    }
}
