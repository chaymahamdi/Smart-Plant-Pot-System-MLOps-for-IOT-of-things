package smart.plantpot.cot.repositories;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import smart.plantpot.cot.entities.Pot;

import java.util.stream.Stream;

@Repository
public interface PotRepository extends CrudRepository<Pot,String> {
   Stream<Pot> findAll();
}
