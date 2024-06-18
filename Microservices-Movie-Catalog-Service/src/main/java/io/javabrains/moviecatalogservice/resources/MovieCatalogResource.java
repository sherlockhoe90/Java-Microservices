package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate = null;

    @Autowired
    WebClient.Builder builder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        //hardcoding a bunch of responses
        UserRating ratings = restTemplate.getForObject("http://localhost:8079/ratingsdata/users/foo" + userId, UserRating.class); //ParameterizedTypeReference<ResponseWrapper<E>> can also be used instead in the second parameter

        //rest template prep
        return ratings.getUserRating().stream().map(rating -> {
                    //RestTemplate (older way)
                    //ideally, for each movie ID, call the movie info service and get details
                    Movie movie = restTemplate.getForObject("http://localhost:8078/movies/" + rating.getMovieId(), Movie.class);

                    //WebClient (newer way)
                    /*Movie movie = builder.build()
                            .get()
                            .uri("http://localhost:8078/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();*/

                    //put all'a them values together
                    return new CatalogItem(movie.getName(), "Test", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}