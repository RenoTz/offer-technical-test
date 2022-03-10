package offer.technical.test.helper;

import lombok.Data;

@Data
public class ExpectedTestViolation {

  private String message;
  private String stringPath;

  public ExpectedTestViolation(final String stringPath, final String message) {
    this.stringPath = stringPath;
    this.message = message;
  }

}
