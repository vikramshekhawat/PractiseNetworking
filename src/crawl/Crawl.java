package crawl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class IncorrectURLException extends Exception {

    IncorrectURLException(String s) {
        super(s);
    }
}

public class Crawl {

    public static void main(String[] args) {
        try {
            System.setProperty("http.proxyhost", "127.0.0.1");
            System.setProperty("http.proxyport", "4146");
            String userAgent = Jsoup.connect("http://www.useragentstring.com")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .ignoreContentType(true).timeout(8000).get().text();
            Document doc = Jsoup.connect("http://in.one.un.org/sdgpoll/#utm_source=OPLIFI&utm_campaign=Display").get();

            //System.out.println("The value is " + et);
            Elements ele = doc.select("div.form-control");
            System.out.println("Elements " + ele);
            String pageTile = doc.title();
            String location = doc.location();
            if (!pageTile.equalsIgnoreCase("United Nations India")) {
                throw new IncorrectURLException("not valid");
            } else {
                System.out.println("User Agent " + userAgent);
                System.out.println("Page Title : - " + pageTile);
                System.out.println("Location : -" + "[" + location + "]");
            }
        } catch (Exception e) {

            Logger.getLogger(Crawl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
