package pl.joannabrania.Dictionary.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.Dictionary.models.CategoryEntity;
import pl.joannabrania.Dictionary.models.CommentEntity;
import pl.joannabrania.Dictionary.models.UserEntity;

import java.util.List;

@Repository
public interface ForumRepository extends CrudRepository<CommentEntity, Integer> {
    List<CommentEntity> findAll();
}
