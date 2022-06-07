package vn.techmaster.banking.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


@Data
@Builder
@Table(name="account")
@Entity(name="account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id 
    private String id;
    private String accountName;
    private String password;
    private long balance;

    
      @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JsonIgnore
      private User user;
    

}
