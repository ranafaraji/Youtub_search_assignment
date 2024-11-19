package org.example.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/news")
    public String searchVideos(@RequestParam String topic, Model model) {
        List<Map<String, String>> results = youTubeService.searchVideos(topic, 5);
        model.addAttribute("topic", topic);
        model.addAttribute("results", results);
        return "results";
    }
}

