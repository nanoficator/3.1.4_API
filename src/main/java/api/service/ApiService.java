package api.service;

import api.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class ApiService {

    private RestTemplate restTemplate;
    String sessionId;

    public ApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Collection<User> getAllUsers() {

        ResponseEntity<Collection<User>> responseEntity = restTemplate.exchange(
                "http://91.241.64.178:7081/api/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<User>>() {}
        );

        sessionId = responseEntity
                .getHeaders()
                .get("Set-Cookie").get(0)
                .substring(0, 43);

        return responseEntity.getBody();
    }

    public String addUser(User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionId);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://91.241.64.178:7081/api/users",
                HttpMethod.POST,
                new HttpEntity<>(user, httpHeaders),
                String.class
        );

        return responseEntity.getBody();
    }

    public String changeUser(User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionId);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://91.241.64.178:7081/api/users",
                HttpMethod.PUT,
                new HttpEntity<>(user, httpHeaders),
                String.class
        );

        return responseEntity.getBody();
    }

    public String delete(User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionId);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://91.241.64.178:7081/api/users/" + user.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(user, httpHeaders),
                String.class
        );

        return responseEntity.getBody();
    }

}
