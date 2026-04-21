package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sliit.oop_server_app.entity.Users;
import sliit.oop_server_app.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public ResponseEntity<?> login(@RequestBody Users user) {
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


    public List<Users> register(@RequestBody List<Users> users) {
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





    public String delete(@PathVariable String id) {
        usersRepository.deleteById(id);
        return "User deleted successfully: " + id;
    }
}
