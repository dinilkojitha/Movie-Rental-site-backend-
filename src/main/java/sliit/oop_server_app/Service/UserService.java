package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        List<Users> users = usersRepository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public ResponseEntity<?> login(Users user) {
        Optional<Users> existingUser = usersRepository.findByGmail(user.getGmail());
        if (existingUser.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        Users dbUser = existingUser.get();
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }
        dbUser.setPassword(null);
        return ResponseEntity.ok(dbUser);
    }

    public List<Users> register(List<Users> users) {
        List<Users> newUsers = users.stream()
                .filter(user -> !usersRepository.existsByGmail(user.getGmail()))
                .collect(Collectors.toList());

        if (newUsers.isEmpty()) return Collections.emptyList();

        newUsers.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return usersRepository.saveAll(newUsers);
    }

    public String delete(String id) {
        usersRepository.deleteById(id);
        return "User deleted successfully: " + id;
    }

    public ResponseEntity<?> updateUser(String id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        Users existingUser = optionalUser.get();
        if (userDetails.getName() != null) existingUser.setName(userDetails.getName());
        if (userDetails.getGmail() != null) existingUser.setGmail(userDetails.getGmail());

        usersRepository.save(existingUser);
        existingUser.setPassword(null);
        return ResponseEntity.ok(existingUser);
    }

    public List<Users> sortUsers() {
        List<Users> users = usersRepository.findAll();
        users.sort((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public ResponseEntity<?> searchUserByGmail(String gmail) {
        Optional<Users> user = usersRepository.findByGmail(gmail);
        if (user.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        Users foundUser = user.get();
        foundUser.setPassword(null);
        return ResponseEntity.ok(foundUser);
    }
}