package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cathedras")
public class Cathedra implements GeneratedId {

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

    @OneToMany(mappedBy = "cathedra", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "CTHDR";
    }
}
