package sliit.oop_server_app.config.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sliit.oop_server_app.entity.User;
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

    public List<User> getAllUser() {
        List<User> User = usersRepository.findAll();
        User.forEach(user -> user.setPassword(null));
        return User;
    }

    public ResponseEntity<?> login(User user) {
        Optional<User> existingUser = usersRepository.findByGmail(user.getGmail());
        if (existingUser.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        User dbUser = existingUser.get();
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }
        dbUser.setPassword(null);
        return ResponseEntity.ok(dbUser);
    }

    public List<User> register(List<User> User) {
        List<User> newUser = User.stream()
                .filter(user -> !usersRepository.existsByGmail(user.getGmail()))
                .collect(Collectors.toList());

        if (newUser.isEmpty()) return Collections.emptyList();

        newUser.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return usersRepository.saveAll(newUser);
    }

    public String delete(int id) {
        usersRepository.deleteById(id);
        return "User deleted successfully: " + id;
    }

    public ResponseEntity<?> updateUser(int id, User userDetails) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        User existingUser = optionalUser.get();
        if (userDetails.getName() != null) existingUser.setName(userDetails.getName());
        if (userDetails.getGmail() != null) existingUser.setGmail(userDetails.getGmail());

        usersRepository.save(existingUser);
        existingUser.setPassword(null);
        return ResponseEntity.ok(existingUser);
    }

    public List<User> sortUser() {
        List<User> User = usersRepository.findAll();
        User.sort((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
        User.forEach(user -> user.setPassword(null));
        return User;
    }

    public ResponseEntity<?> searchUserByGmail(String gmail) {
        Optional<User> user = usersRepository.findByGmail(gmail);
        if (user.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        User foundUser = user.get();
        foundUser.setPassword(null);
        return ResponseEntity.ok(foundUser);
    }
}