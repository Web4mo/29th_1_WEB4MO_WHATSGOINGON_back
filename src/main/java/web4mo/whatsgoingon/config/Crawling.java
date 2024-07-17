//package web4mo.whatsgoingon.config;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//
//public class Crawling {
//    public static void main(String[] args) {
//        Crawling c = new Crawling();
//        //크롤링 기사 URL
//        System.out.println(c.makeContent("https://n.news.naver.com/mnews/article/003/0012672453"));
//    }
//
//    public String makeContent(String url) throws IOException {
//        Document docs = Jsoup.connect(url).get();
//
//        //기사 본문
//        Element content = docs.select("");
//
//    }
//}
//
//
