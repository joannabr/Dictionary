package pl.joannabrania.Dictionary.models;
import lombok.Data;
import pl.joannabrania.Dictionary.models.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "forum")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "comment_data")
    private LocalDateTime commentData;

    int opinion;


}
