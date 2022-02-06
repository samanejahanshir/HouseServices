package ir.maktab.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Manager extends User{
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;

}
