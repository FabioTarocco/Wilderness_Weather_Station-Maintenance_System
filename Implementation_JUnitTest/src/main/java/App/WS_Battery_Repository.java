package App;

//import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WS_Battery_Repository extends CrudRepository<WS_Battery, Long> {

   //List<WeatherStation> findByLastName(String lastName);


   WS_Battery findById(long id);

}
