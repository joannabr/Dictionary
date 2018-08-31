package pl.joannabrania.Dictionary.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "dictionary")
public class DicitionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "english_word")
    private String englishWord;

    @Column(name = "polish_word")
    private String polishWord;

   @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
