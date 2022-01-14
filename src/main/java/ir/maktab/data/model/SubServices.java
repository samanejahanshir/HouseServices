package ir.maktab.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class SubServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupName;
    @Column(unique = true)
    private String name;
    private double basePrice;
    private String description;
   /* @ManyToMany(mappedBy = "services")
    private List<Expert> experts = new ArrayList<>();*/

    @Override
    public String toString() {
        return "SubServices{" +
                "id=" + id +
                ", groupService='" + groupName + '\'' +
                ", subService='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubServices)) return false;
        SubServices that = (SubServices) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
