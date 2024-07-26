package web4mo.whatsgoingon.config.NaverApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NaverApi {

    // naverApiResearch(검색어, 개수, 정렬 방법)
    // date - 날짜순, sim - 정확도 순
    // articleApiDto 들의 List 형태로 반환

    // 예시 사용법
    /*
    public static void main(String[] args){
        NaverApi test = new NaverApi();

        List<articleApiDto> articles = test.naverApiResearch("트럽프", 2, "date");

        for (articleApiDto article : articles) {
            System.out.println(article.getTitle()); // 기사 제목들만 출력되는중
        }
    }
    */

    public static List<articleApiDto> parseJsonToArticles(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");

        Type listType = new TypeToken<List<articleApiDto>>(){}.getType();
        return gson.fromJson(itemsArray, listType);
    }

    private List<articleApiDto> naverApiResearch(String query, int display, String sort){
        String clientId = "bEN9b9S5f29hb9uLIAwk";
        String clientSecret = "7qhxwNjo7b";

        String text = null;
        try {
            text = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text + "&display=" + display +"&sort=" + sort;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        return parseJsonToArticles(get(apiURL,requestHeaders));
    }
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

}
