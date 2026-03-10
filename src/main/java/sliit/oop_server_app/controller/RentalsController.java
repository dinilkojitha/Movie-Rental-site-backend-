package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Rentals;
import sliit.oop_server_app.repository.RentalsRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/rentals")
public class RentalsController {
    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping(produces = "application/json")
    public List<Rentals> get() {
        List<Rentals> rentals = this.rentalsRepository.findAll();
        return rentals;
    }

    @PostMapping("/register")
    public List<Rentals> registerRentals(@RequestBody List<Rentals> rentals) {
        // 1. Filter out rentals that already exist based on Gmail
        List<Rentals> newRentals = rentals.stream()
                .filter(user -> !rentalsRepository.existsByGmail(user.getGmail()))
                .collect(Collectors.toList());

        if (newRentals.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. Loop through the list and encrypt each password
        newRentals.forEach(user -> {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        });

        // 3. Save the entire list of new rentals
        return rentalsRepository.saveAll(newRentals);
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







