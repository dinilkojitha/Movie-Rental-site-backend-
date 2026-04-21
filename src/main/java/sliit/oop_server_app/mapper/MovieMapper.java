package sliit.oop_server_app.mapper;

import sliit.oop_server_app.DTO.MovieRequest;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.entity.Movie;

public class MovieMapper {

    public static MovieResponse toResponse(Movie movie) {
        MovieResponse res = new MovieResponse();

        res.setName(movie.getName());
        res.setLanguage(movie.getLanguage());
        res.setCountry(movie.getCountry());
        res.setPrice(movie.getPrice());
        res.setDescription(movie.getDescription());
        res.setActors(movie.getActors());
        res.setImdb(movie.getImdb());
        res.setTomato(movie.getTomato());
        return res;
    }

    public static Movie toEntity(MovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setCountry(request.getCountry());
        movie.setDuration(request.getDuration());
        movie.setPrice(request.getPrice());
        movie.setImdb(request.getImdb());
        return movie;
    }
}