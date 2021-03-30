package Film.FilmApi.entity;

import lombok.Data;

@Data
public class Film {
    private Long film_id;
    private String title;
    private String description;
    private String release_year;
    private Long language_id;
    private Long original_language_id;
    private Long rental_duration;
    private Long rental_rate;
    private Long length;
    private Long replacement_cost;
    private String rating;
    private String special_features;
    private String last_update;
}
