package vn.techmaster.banking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "users")
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  private String id;
  private String name;
  private String address;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private List<Account> accounts = new ArrayList<>();

  public void add(Account account) {
    account.setUser(this);
    accounts.add(account);
  }

  public void remove(Account account) {
    account.setUser(null);
    accounts.remove(account);
  }

  @PreRemove
  public void PreRemove() {
    accounts.stream().forEach(p -> p.setUser(null));
    accounts.clear();
  }
}
