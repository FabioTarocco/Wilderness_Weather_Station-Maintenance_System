package App;

//import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WS_Network extends CrudRepository<WeatherStation, Long> {

   WeatherStation findByCode(String code);

   WeatherStation findById(long id);
}
