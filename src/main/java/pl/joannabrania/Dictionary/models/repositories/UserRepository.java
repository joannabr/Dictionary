package pl.joannabrania.Dictionary.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.Dictionary.models.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    UserEntity findById(int id);
    List<UserEntity> findAllByOrderByPointsDesc();

}