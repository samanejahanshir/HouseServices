package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Expert extends User{
    @Lob
    @Column(nullable = false)
    private byte[] image;
    @ManyToMany
    private Set<Services> services=new HashSet<>();
}
