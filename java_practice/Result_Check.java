public class Result_Check {
    public static void main(String[] args) {
        int expectedResults = 6;
        int actualResults = 4;
        String expectedTitle = "Home Page";
        String actualTitle = "Login Page";

        if (expectedResults == actualResults) {
            System.out.println("PASS: Result count matches");
        } else {
            System.out.println("FAIL: Expected " + expectedResults + " but found " + actualResults);
        }

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("PASS: Page title matches");
        } else {
            System.out.println("FAIL: Expected '" + expectedTitle + "' but found '" + actualTitle + "'");
        }
    }
}