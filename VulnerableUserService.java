import java.sql.*;
import java.io.*;
import java.util.Base64;

public class VulnerableUserService {

    private Connection connection;

    public VulnerableUserService(Connection connection) {
        this.connection = connection;
    }

    // 1. SQL Injection
    public void login(String username, String password) throws Exception {
        String query = "SELECT * FROM users WHERE username='" + username +
                       "' AND password='" + password + "'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    // 2. Command Injection
    public void pingHost(String host) throws Exception {
        Runtime.getRuntime().exec("ping " + host);
    }

    // 3. Hardcoded Secret
    private static final String API_KEY = "my-secret-api-key-123";

    // 4. Weak Encryption
    public String encrypt(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    // 5. Path Traversal
    public String readFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(
                new FileReader("/var/data/" + filename));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        br.close();
        return sb.toString();
    }

    // 6. Sensitive Information Disclosure
    public void printDatabasePassword() {
        String dbPassword = "SuperSecretPassword123";
        System.out.println("Database Password: " + dbPassword);
    }

    // 7. Insecure Random
    public int generateOTP() {
        java.util.Random random = new java.util.Random();
        return random.nextInt(999999);
    }

    // 8. Resource Leak
    public void getUsers() throws Exception {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        while (rs.next()) {
            System.out.println(rs.getString("username"));
        }

        // Statement and ResultSet never closed
    }

    // 9. Null Pointer Risk
    public int getLength(String value) {
        return value.length();
    }

    // 10. Information Leak
    public void process() {
        try {
            int x = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}