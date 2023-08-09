package com.example.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private static final int numbers = 6;
    public List<Integer> userNumbers = new ArrayList<>();
    public Set<Integer> randomNumbers = new HashSet<>();

    public ResponseEntity <String> getNumbers (List<Integer> getNumbers) {
        userNumbers = getNumbers;
        if(userNumbers.size() != numbers){
            return ResponseEntity.badRequest().body("Musisz podac dokladnie 6 liczb");
        }
        return ResponseEntity.ok("Przyjeto podane liczby");
    }

    public void GenerateRandom(){
        Random random = new Random();

        while(randomNumbers.size() < numbers){
            int number = random.nextInt(50);
            if (!randomNumbers.contains(number)){
                randomNumbers.add(number);
            }
        }
    }

    public int countMatching (){
        int count = 0;
        for (Integer number : userNumbers){
            if(randomNumbers.contains(number)){
                count++;
            }
        }
        return count;
    }

    public String displayRandom (){
        String display = randomNumbers.toString();
        randomNumbers.clear();
        return display;
    }

}