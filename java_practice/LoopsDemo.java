public class LoopsDemo {
    public static void main(String[] args) {
        String[] searchResults = {"Laptop", "Mouse", "Keyboard", "Monitor", "Webcam"};

        System.out.println("Total results: " + searchResults.length);

        for (String item : searchResults) {
            System.out.println("Found: " + item);
        }

        boolean mouseFound = false;
        for (String item : searchResults) {
            if (item.equals("Printer")) {
                mouseFound = true;
            }
        }
        System.out.println("Mouse in results? " + mouseFound);
    }
}