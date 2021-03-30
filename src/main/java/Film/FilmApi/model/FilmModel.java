package Film.FilmApi.model;

import lombok.Data;

@Data
public class FilmModel {
    private Long filmId;
    private String title;
    private String description;
    private String length;
    private String releaseYear;
    private String rating;

}
