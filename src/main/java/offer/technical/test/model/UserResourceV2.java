package offer.technical.test.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import offer.technical.test.model.validation.AdultConstraint;
import offer.technical.test.model.validation.BeginByWConstraint;

import java.time.LocalDate;

@Data
public class UserResourceV2 {

  @NotBlank
  @BeginByWConstraint
  private String name;

  @NotNull
  @AdultConstraint
  private LocalDate birthDate;

  @NotBlank
  private String country;

}
