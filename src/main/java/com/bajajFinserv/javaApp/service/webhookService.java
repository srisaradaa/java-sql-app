package com.bajajFinserv.javaApp.service;

import com.bajajFinserv.javaApp.util.sqlSolution;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class webhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void executeFlow() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> body = new HashMap<>();
        body.put("name", "John Doe");
        body.put("regNo", "REG12347");
        body.put("email", "john@example.com");

        ResponseEntity<Map> response = restTemplate.postForEntity(url, body, Map.class);

        if (response.getBody() != null) {
            String webhookUrl = (String) response.getBody().get("webhook");
            String accessToken = (String) response.getBody().get("accessToken");


            String finalQuery = sqlSolution.getQuery();

            Map<String, String> submitBody = new HashMap<>();
            submitBody.put("finalQuery", finalQuery);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(submitBody, headers);


            ResponseEntity<String> submitResponse =
                    restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

            System.out.println("Submission Response: " + submitResponse.getBody());
        }
    }
}

