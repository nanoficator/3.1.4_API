package api;

import api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private ApiService apiService;

    @Autowired
    public Application(ApiService apiService) {
        this.apiService = apiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void getResult() {
        this.apiService.getAllUsers();
    }

}
