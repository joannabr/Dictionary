package pl.joannabrania.Dictionary.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;
import pl.joannabrania.Dictionary.models.UserDictionaryEntity;
import pl.joannabrania.Dictionary.models.UserEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DictionaryUserRepository extends CrudRepository<UserDictionaryEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM userdictionary WHERE user_id = ?1 AND translation ='' ")
    List<UserDictionaryEntity> getUserWord(int numberUser);

    Optional<UserDictionaryEntity> findByUserAndWord(UserEntity userEntity, DicitionaryEntity dicitionaryEntity);

//    @Modifying
    @Query(nativeQuery = true, value = "UPDATE userdictionary SET translation = '?1' WHERE (user_id = ?2 AND word_id = ?3)")
    void setTranslation(String checkWord, int userId, int wordId);
}
