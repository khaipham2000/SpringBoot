package vn.techmaster.bank.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "rate")
@Table(name = "rate")
public class Rate {
    @Id
    private String id;
    private Long months;
    private double rate;
}
