public class MethodsDemo {

    static void verifyText(String checkName, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("\"✔ PASS: \"" + checkName);
        } else {
            System.out.println("FAIL: " + checkName + " | Expected '" + expected + "' but found '" + actual + "'");
        }
    }

    public static void main(String[] args) {
        verifyText("Page title", "Home Page", "Home Page");
        verifyText("Login button label", "Login", "Sign In");
        verifyText("Welcome message", "Welcome, qa_tester", "Welcome, qa_tester");
        verifyText("is Pingd", "YES", "YES");
    }
}