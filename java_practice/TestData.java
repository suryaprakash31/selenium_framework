public class TestData {
    public static void main(String[] args) {
        String url = "https://example.com";
        String username = "qa_tester";
        int expectedResults = 6;
        boolean isLoginSuccessful = true;
        String browser = "Chrome";

        System.out.println("Opening: " + url);
        System.out.println("Browser: " + browser);
        System.out.println("Logging in as: " + username);
        System.out.println("Expected results: " + expectedResults);
        System.out.println("Login successful? " + isLoginSuccessful);
    }
}