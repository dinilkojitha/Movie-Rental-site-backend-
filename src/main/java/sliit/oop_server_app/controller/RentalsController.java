package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.RentalService;
import sliit.oop_server_app.entity.Rental;
import sliit.oop_server_app.repository.RentalsRepository;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rentals")
public class RentalsController {


    @Autowired
    private RentalService rentalService;
    @GetMapping(produces = "application/json")
    public List<Rental> get() {
      return rentalService.getAll();
    }

    @GetMapping("{id}")
    public List<Rental> get(@PathVariable int id) {
        return rentalService.getByUser(id);
    }

    @PostMapping("/save")
    public Rental saveUsers(@RequestBody Rental rentals) {
      return rentalService.addnew(rentals);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUsers(@PathVariable int id) {
        return rentalService.deleteRental(id);
    }


}







