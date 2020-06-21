package api.controller;

import api.model.User;
import api.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/")
public class ApiController {

    private ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<String> getCode() {

        String code = new String();

        Collection<User> allUsers = apiService.getAllUsers();

        User user = new User();
        user.setId(Long.parseLong("3"));
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 50);

        code += apiService.addUser(user);

        user.setName("Thomas");
        user.setLastName("Shelby");

        code += apiService.changeUser(user);

        code += apiService.delete(user);

        return new ResponseEntity<>(code, HttpStatus.OK);
    }

}
