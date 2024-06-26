package io.javabrains.microservicesratingsdataservice.resources;

import io.javabrains.microservicesratingsdataservice.models.Rating;
import io.javabrains.microservicesratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 5);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatingList(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("4567", 7),
                new Rating("1122", 10)
        );

        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
