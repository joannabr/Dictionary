package pl.joannabrania.Dictionary.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private String password;
    @Column(name = "registered_data")
    private LocalDateTime registerDate;
    private int points;
}
