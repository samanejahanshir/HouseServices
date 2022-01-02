package model;

import lombok.Data;
import service.ManagerService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SubServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupService;
    @Column(unique = true)
    private String subService;
    private double basePrice;
    private String description;
    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    private Set<Expert> expertSet = new HashSet<>();

    public SubServices() {

    }

    public static final class ServicesBuilder {
        private String groupService;
        private String subService;
        private double basePrice;
        private String description;
        private Set<Expert> expertSet = new HashSet<>();

        private ServicesBuilder() {
        }

        public static ServicesBuilder aServices() {
            return new ServicesBuilder();
        }

        public ServicesBuilder withGroupService(String groupService) {
            this.groupService = groupService;
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

        public ServicesBuilder withExpertSet(Set<Expert> expertSet) {
            this.expertSet = expertSet;
            return this;
        }

        public SubServices build() {
            SubServices subServices = new SubServices();
            subServices.setGroupService(groupService);
            subServices.setSubService(subService);
            subServices.setBasePrice(basePrice);
            subServices.setDescription(description);
            subServices.setExpertSet(expertSet);
            return subServices;
        }
    }
}
