package org.example.youtube;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YouTubeService {

    private static final String API_KEY = "YOUR_YOUTUBE_API_KEY";
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search";

    public List<Map<String, String>> searchVideos(String query, int maxResults) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?part=snippet&q=%s&type=video&maxResults=%d&key=%s",
                BASE_URL, query, maxResults, API_KEY);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, String>> results = new ArrayList<>();
        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

        for (Map<String, Object> item : items) {
            Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
            Map<String, Object> id = (Map<String, Object>) item.get("id");
            String videoId = (String) id.get("videoId");

            results.add(Map.of(
                    "title", (String) snippet.get("title"),
                    "link", "https://www.youtube.com/watch?v=" + videoId
            ));
        }
        return results;
    }
}

