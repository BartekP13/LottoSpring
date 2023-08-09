package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;
    private final GameService gameService;
@Autowired
    public UserController(UserService userService, GameService gameService) {
        this.userService = userService;
    this.gameService = gameService;
}


    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody User user){
        boolean loginSuccessful = userService.authenticate(user.getLogin(), user.getPassword());

        return loginSuccessful
                ?ResponseEntity.ok("Login successful")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/game")
    public ResponseEntity<String>checkNumbers(@RequestBody List<Integer> numbers){ return gameService.getNumbers(numbers); }

    @GetMapping("/numbers")
    public ResponseEntity<String> generateNumbers(){
    gameService.GenerateRandom();
    int matching = gameService.countMatching();
    String randomNumbers = gameService.displayRandom();
    return ResponseEntity.ok("Wylosowane liczby to: " + randomNumbers + "ilosc trafien to " + matching);
    }
}
