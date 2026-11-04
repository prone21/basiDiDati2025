package cinema.exception;

public class DAOException extends Exception {
  private String state = "";

  public DAOException() {
    super();
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }

  public DAOException(String message) {
    super(message);
  }

  public DAOException(String state, String message) {
    super(message);
    this.state = state;
  }

  public String getState() {
    return state;
  }

}
