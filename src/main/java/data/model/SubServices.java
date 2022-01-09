package data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class SubServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupName;
    @Column(unique = true)
    private String subService;
    private double basePrice;
    private String description;
   /* @ManyToMany(mappedBy = "services")
    private List<Expert> experts = new ArrayList<>();*/

    public SubServices() {

    }

    public static final class ServicesBuilder {
        private String groupName;
        private String subService;
        private double basePrice;
        private String description;
       // private List<Expert> experts = new ArrayList<>();

        private ServicesBuilder() {
        }

        public static ServicesBuilder aServices() {
            return new ServicesBuilder();
        }

        public ServicesBuilder withGroupService(String groupService) {
            this.groupName = groupService;
            return this;
        }

        public ServicesBuilder withSubService(String subService) {
            this.subService = subService;
            return this;
        }

        public ServicesBuilder withBasePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public ServicesBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

       /* public ServicesBuilder withExpertSet(List<Expert> expertSet) {
            this.experts = expertSet;
            return this;
        }*/

        public SubServices build() {
            SubServices subServices = new SubServices();
            subServices.setGroupName(groupName);
            subServices.setSubService(subService);
            subServices.setBasePrice(basePrice);
            subServices.setDescription(description);
           // subServices.setExperts(experts);
            return subServices;
        }
    }

    @Override
    public String toString() {
        return "SubServices{" +
                "id=" + id +
                ", groupService='" + groupName + '\'' +
                ", subService='" + subService + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubServices)) return false;
        SubServices that = (SubServices) o;
        return subService.equals(that.subService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subService);
    }
}
