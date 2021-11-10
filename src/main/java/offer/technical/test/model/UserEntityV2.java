package offer.technical.test.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "users")
public class UserEntityV2 {

  private String name;

  private LocalDate birthDate;

  private String country;

}
