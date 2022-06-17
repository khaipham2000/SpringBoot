package vn.techmaster.bank.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingAccount {
    @Id 
    private String id;
  
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account; //Mỗi accountsaver phải gắn vào một account
    private Long startBalance;
    private Long endBalance;
    private Long months;
    private Double rate;
    @Enumerated(EnumType.STRING)
    private SavingType typeSave;
    private LocalDateTime openAt;
    private LocalDateTime updateAt;
    private LocalDateTime closeAt;
  public SavingAccount orElseThrow(Object object) {
      return null;
  }  
}
