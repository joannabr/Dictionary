package pl.joannabrania.Dictionary.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userdictionary")
public class UserDictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "word_id")
    @ManyToOne
    private DicitionaryEntity word;

    String translation;

}
