package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.oop_server_app.entity.Rental;
import sliit.oop_server_app.repository.RentalsRepository;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalsRepository rentalsRepository;

    public List<Rental> getAll() {

        List<Rental> rentals = rentalsRepository.findAll();

        return rentals.stream().map(rental -> {
            Rental r = new Rental();
            r.setId(rental.getId());
            r.setUsers(rental.getUsers());
            r.setMovies(rental.getMovies());
            return r;
        }).toList();
    }

    public List<Rental> getByUser(int id) {
        return rentalsRepository.findByUsers_Id(id);
    }

    public Rental addnew(Rental data) {
        return rentalsRepository.save(data);
    }

}