package smart.plantpot.cot.repositories;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import smart.plantpot.cot.entities.User;

import java.util.Optional;
import java.util.stream.Stream;
@Repository
public interface UserRepository  extends CrudRepository <User, String> { // repository containing the methods for interacting with SensorDB entity in mongodb
    Stream<User> findAll();
    Stream<User> findBypermissionLevel(Long L);
    Stream<User> findByfullnameIn(String s);


}