package model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Data
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String groupService;
    private  String subService;
    private double basePrice;
    private  String description;
    @ManyToMany(mappedBy = "services")
    private Set<Expert> expertSet=new HashSet<>();

    public Services() {

    }
}
