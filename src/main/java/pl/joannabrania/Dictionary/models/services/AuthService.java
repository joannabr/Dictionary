package pl.joannabrania.Dictionary.models.services;

import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.UserEntity;
import pl.joannabrania.Dictionary.models.forms.RegisterForm;
import pl.joannabrania.Dictionary.models.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    final UserRepository userRepository;
    final SessionService sessionService;

    public AuthService(UserRepository userRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public int getPoints() {
        UserEntity userEntity = userRepository.findById(sessionService.getUserEntity().getId());
        return userEntity.getPoints();
    }

    public String findWinner(){
        List<UserEntity> userEntityList = userRepository.findAllByOrderByPointsDesc();
        UserEntity userEntity = userEntityList.get(0);
        String winner = userEntity.getUsername();
        return winner;
    }
    public boolean tryLogin(String username, String password){
        Optional<UserEntity> userEntity
                = userRepository.findByUsernameAndPassword(username, password);
        if(userEntity.isPresent()){
            sessionService.setLogin(true);
            sessionService.setUserEntity(userEntity.get());
        }
        return userEntity.isPresent();
    }

    public boolean tryToRegister(RegisterForm registerForm){
        if(userRepository.existsByUsername(registerForm.getUsername())){
            return false;
        }

        UserEntity userEntity = createEntityFromForm(registerForm);
        userRepository.save(userEntity);
        return true;
    }

    private UserEntity createEntityFromForm(RegisterForm registerForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerForm.getUsername());
        userEntity.setPassword(registerForm.getPassword());
        userEntity.setEmail(registerForm.getEmail());
        userEntity.setPoints(0);

        return userEntity;
    }

    public void removeAccount(){

        userRepository.delete(sessionService.getUserEntity());
    }


}
