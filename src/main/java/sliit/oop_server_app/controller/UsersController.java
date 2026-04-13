package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Users;
import sliit.oop_server_app.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @GetMapping(produces = "application/json")
    public List<Users> get() {
        List<Users> users = this.usersRepository.findAll();
        users.forEach(user -> {
            user.setPassword(null);
        });
        return users;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsers(@RequestBody Users user) {
        System.out.println("Login attempt: " + user.getGmail() + " / " + user.getPassword());
        Optional<Users> existingUser = usersRepository.findByGmail(user.getGmail());

        if (existingUser.isEmpty()) {
            System.out.println("User not found in DB");
            return ResponseEntity.badRequest().body("User not found");
        }

        Users dbUser = existingUser.get();
        boolean passwordMatch = passwordEncoder.matches(user.getPassword(), dbUser.getPassword());

        if (!passwordMatch) {
            System.out.println("Password mismatch");
            return ResponseEntity.badRequest().body("Invalid password");
        }

        dbUser.setPassword(null);
        return ResponseEntity.ok(dbUser);
    }

    @PostMapping("/register")
    public List<Users> registerUsers(@RequestBody List<Users> users) {
        // 1. Filter out users that already exist based on Gmail
        List<Users> newUsers = users.stream()
                .filter(user -> !usersRepository.existsByGmail(user.getGmail()))
                .collect(Collectors.toList());

        if (newUsers.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. Loop through the list and encrypt each password
        newUsers.forEach(user -> {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        });

        // 3. Save the entire list of new users
        return usersRepository.saveAll(newUsers);
    }

//    @PutMapping("/update")
//    public ResponseEntity<List<FinalPage>> updateFinalPages(@RequestBody List<FinalPage> finals) {
//        for (FinalPage fine : finals) {
//            Optional<FinalPage> existing = finalPageRepository.findById(fine.getId());
//            if (existing.isPresent()) {
//                FinalPage updated = existing.get();
//                updated.setTitle(fine.getTitle());
//                updated.setDescription(fine.getDescription());
//                updated.setImage(fine.getImage());
//                updated.setAbout_detail(fine.getAbout_detail());
//                updated.setAbout_title(fine.getAbout_title());
//                // Add any other fields you want to update
//                finalPageRepository.save(updated);
//            }
//        }
//        // Return the full MainPage list (or filter as needed)
//        List<FinalPage> mainPages = finalPageRepository.findAll();
//        return ResponseEntity.ok(mainPages);
//    }



}







