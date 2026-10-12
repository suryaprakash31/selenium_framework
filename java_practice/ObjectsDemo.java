public class ObjectsDemo {
    public static void main(String[] args) {
        Browser chrome = new Browser("Chrome");
        Browser firefox = new Browser("Firefox");
        Browser edge = new Browser("Edge");

        chrome.open("https://example.com");
        firefox.open("https://example.org");
        edge.open("https://example.org");
        chrome.close();
        firefox.close();
        edge.close();
    }
}