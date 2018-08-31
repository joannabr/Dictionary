package pl.joannabrania.Dictionary.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;

import java.util.List;

@Repository
public interface DictionaryRepository extends CrudRepository<DicitionaryEntity, Integer>{
    @Query(nativeQuery = true, value = "SELECT * FROM dictionary WHERE category_id = ?1")
    List<DicitionaryEntity> findByNumberGroup(int numberGroup);

    @Query(nativeQuery = true, value = "SELECT * FROM dictionary WHERE category_id = ?1 AND " +
            "id NOT IN (SELECT word_id FROM userdictionary WHERE user_id = ?2)")
    List<DicitionaryEntity> findUserWordsByGroup(int groupId, int userId);
}
