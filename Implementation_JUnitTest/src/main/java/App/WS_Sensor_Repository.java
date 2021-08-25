package App;

//import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WS_Sensor_Repository extends CrudRepository<WS_Sensor, Long> {

   //List<WeatherStation> findByLastName(String lastName);


   WS_Sensor findById(long id);

}
