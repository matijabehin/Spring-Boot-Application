package hr.tvz.behin.studapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "authority")
public class Authority {
    @Id
    private Long id;
    private String name;

    public Authority(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
