package pl.joannabrania.Dictionary.models.services;

import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.UserDictionaryEntity;
import pl.joannabrania.Dictionary.models.repositories.DictionaryRepository;
import pl.joannabrania.Dictionary.models.repositories.DictionaryUserRepository;

import java.util.List;

@Service
public class UserDictionaryService {

    final DictionaryRepository dictionaryRepository;
    final SessionService sessionService;
    final DictionaryUserRepository dictionaryUserRepository;

    public UserDictionaryService(DictionaryRepository dictionaryRepository, SessionService sessionService, DictionaryUserRepository dictionaryUserRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.sessionService = sessionService;
        this.dictionaryUserRepository = dictionaryUserRepository;
    }

    public void addWordToUser(int wordId, String nameGroup){
        UserDictionaryEntity userDictionaryEntity = createUserDictionaryEntity(wordId,nameGroup);
        dictionaryUserRepository.save(userDictionaryEntity);
    }

    public UserDictionaryEntity createUserDictionaryEntity(int wordId, String nameGroup){
        UserDictionaryEntity userDictionaryEntity = new UserDictionaryEntity();

        userDictionaryEntity.setWord(dictionaryRepository.findById(wordId).get());
        userDictionaryEntity.setUser(sessionService.getUserEntity());
        userDictionaryEntity.setTranslation("");
        return userDictionaryEntity;
    }

    public List<UserDictionaryEntity> getUserWord(){
        return dictionaryUserRepository.getUserWord(sessionService.getUserEntity().getId());
    }

}
