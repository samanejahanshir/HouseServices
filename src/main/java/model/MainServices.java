package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MainServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupName;
}
