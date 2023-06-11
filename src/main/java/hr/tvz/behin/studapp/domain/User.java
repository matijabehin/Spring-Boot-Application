package hr.tvz.behin.studapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    @ManyToMany(targetEntity = Authority.class)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities = new ArrayList<>();

    public User(Long id, String username, String password, String firstName,
                String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = firstName;
        this.last_name = lastName;
    }
    public User(Long id, String username, String password, String firstName,
                String lastName, List<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = firstName;
        this.last_name = lastName;
        this.authorities = authorities;
    }
}
