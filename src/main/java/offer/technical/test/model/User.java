package offer.technical.test.model;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import offer.technical.test.model.validation.AdultConstraint;
import offer.technical.test.model.validation.CountryConstraint;
import offer.technical.test.model.validation.GenderConstraint;
import offer.technical.test.model.validation.PhoneNumberConstraint;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

  @NotBlank
  private String name;

  @NotNull
  @AdultConstraint
  private LocalDate birthDate;

  @NotBlank
  @CountryConstraint
  private String country;

  @PhoneNumberConstraint
  private String phoneNumber;

  @GenderConstraint
  private String gender;

}
