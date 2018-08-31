package pl.joannabrania.Dictionary.models.services;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;
import pl.joannabrania.Dictionary.models.UserDictionaryEntity;
import pl.joannabrania.Dictionary.models.UserEntity;
import pl.joannabrania.Dictionary.models.repositories.DictionaryRepository;
import pl.joannabrania.Dictionary.models.repositories.UserRepository;

import java.util.List;

@Service
public class DictionaryService {

    final UserRepository userRepository;
    final DictionaryRepository dictionaryRepository;
    final SessionService sessionService;

    @Autowired
    public DictionaryService(UserRepository userRepository, DictionaryRepository dictionaryRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.sessionService = sessionService;
    }

    public List<DicitionaryEntity> getWords(int numberGroup) {
        return dictionaryRepository.findByNumberGroup(numberGroup);
    }

    public List<DicitionaryEntity> getUserWords(int numberGroup) {
        return dictionaryRepository.findUserWordsByGroup(numberGroup, sessionService.getUserEntity().getId());
    }


}
