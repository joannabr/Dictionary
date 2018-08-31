package pl.joannabrania.Dictionary.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;
import pl.joannabrania.Dictionary.models.UserDictionaryEntity;

import java.util.List;

@Repository
public interface DictionaryUserRepository extends CrudRepository<UserDictionaryEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM userdictionary WHERE user_id = ?1")
    List<UserDictionaryEntity> getUserWord(int numberUser);
}
