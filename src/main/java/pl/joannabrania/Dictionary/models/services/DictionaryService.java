package pl.joannabrania.Dictionary.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;
import pl.joannabrania.Dictionary.models.repositories.DictionaryRepository;
import pl.joannabrania.Dictionary.models.repositories.DictionaryUserRepository;
import pl.joannabrania.Dictionary.models.repositories.UserRepository;

import java.util.List;

@Service
public class DictionaryService {

    final UserRepository userRepository;
    final DictionaryRepository dictionaryRepository;
    final SessionService sessionService;
    final DictionaryUserRepository dictionaryUserRepository;

    @Autowired
    public DictionaryService(UserRepository userRepository, DictionaryRepository dictionaryRepository, SessionService sessionService, DictionaryUserRepository dictionaryUserRepository) {
        this.userRepository = userRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.sessionService = sessionService;
        this.dictionaryUserRepository = dictionaryUserRepository;
    }

    public List<DicitionaryEntity> getWords(int numberGroup) {
        return dictionaryRepository.findByNumberGroup(numberGroup);
    }

    public List<DicitionaryEntity> getUserWords(int numberGroup) {
        return dictionaryRepository.findUserWordsByGroup(numberGroup, sessionService.getUserEntity().getId());
    }



}
