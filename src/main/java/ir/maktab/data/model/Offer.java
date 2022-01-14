package ir.maktab.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date offerCreateDate;
    private double offerPrice;
    private int durationTime;

    private int startTime;
    @ManyToOne(fetch =FetchType.EAGER)
    private Orders orders;
    @ManyToOne
    private Expert expert;

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", offerCreateDate=" + offerCreateDate +
                ", offerPrice=" + offerPrice +
                ", doneTime=" + durationTime +
                ", startTime=" + startTime +
                ", expert=" + expert +
                '}';
    }
}
