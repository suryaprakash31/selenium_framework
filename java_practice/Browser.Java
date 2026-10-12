public class Browser {
    String name;

    Browser(String browserName) {
        name = browserName;
    }

    void open(String url) {
        System.out.println(name + " opened " + url);
    }

    void close() {
        System.out.println(name + " closed");
    }
}