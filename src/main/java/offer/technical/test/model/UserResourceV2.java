package offer.technical.test.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResourceV2 {

  private String name;

  private LocalDate birthDate;

  private String country;

}
