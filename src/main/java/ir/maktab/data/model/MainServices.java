package ir.maktab.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class MainServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainServices)) return false;
        MainServices that = (MainServices) o;
        return groupName.equals(that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
    }
}
