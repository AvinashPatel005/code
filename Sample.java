import java.util.*;

public class Sample {
    private String password = "admin1213"; // hardcoded!

    public void processData(String input) {
        String query = "SELECT * FROM users WHERE id = " + input; // SQL injection
        System.out.println(query);
    }

    public void unused() {
    } // dead code
}