package offer.technical.test.model;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserEntity {

  private String name;

  private LocalDate birthDate;

  private String country;

  private String phoneNumber;

  private String gender;

}
