package Film.FilmApi.repository;

import Film.FilmApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ActorModel> getAllActor(){
        StringBuilder str  =new StringBuilder();
        str.append("select ");
        str.append(" * ");
        str.append(" from ");
        str.append(" actor");
        String sql = str.toString();

        List<ActorModel> resultList = this.jdbcTemplate.query(sql, new RowMapper<ActorModel>() {
            @Override
            public ActorModel mapRow(ResultSet resultSet, int i) throws SQLException {
                ActorModel actorModel = new ActorModel();
                actorModel.setActorId(resultSet.getLong(1));
                actorModel.setFirstName(resultSet.getString(2));
                actorModel.setLastName((resultSet.getString(3)));
                actorModel.setLastUpdate(resultSet.getString(4));

                return actorModel;
            }
        });
        return resultList;
    }

    public List<ActorModel> getSearchActor(SearchActorModel searchActorModel){
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sb.append("select * from actor where 1=1");
        if (!searchActorModel.getFirstName().isEmpty()) {
            sb.append(" and first_name Like ? ");
            params.add(searchActorModel.getFirstName()+"%");
        }
        if (!searchActorModel.getLastName().isEmpty()) {
            sb.append(" and last_name like ? ");
            params.add(searchActorModel.getLastName()+"%");
        }
        String sql = sb.toString();
        List<ActorModel> resultList = this.jdbcTemplate.query(sql, params.toArray(), new RowMapper<ActorModel>() {
            @Override
            public ActorModel mapRow(ResultSet resultSet, int i) throws SQLException {

                ActorModel actorModel = new ActorModel();
                actorModel.setActorId(resultSet.getLong(1));
                actorModel.setFirstName(resultSet.getString(2));
                actorModel.setLastName((resultSet.getString(3)));
                actorModel.setLastUpdate(resultSet.getString(4));

                return actorModel;
            }
        });
        return resultList;
    }

    public List<FilmModel> getSearchFilm(SearchFilmModel searchFilmModel){
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();
        sb.append("select * from film where 1=1");

        if (!searchFilmModel.getTitle().isEmpty()) {
            sb.append(" and title like ?");
            params.add(searchFilmModel.getTitle() +"%");
        }
        if (!searchFilmModel.getReleaseYear().isEmpty()) {
            sb.append(" and release_year like ?");
            params.add(searchFilmModel.getReleaseYear()+"%");
        }

//
        String sql = sb.toString();

        List<FilmModel> resultList = this.jdbcTemplate.query(sql, params.toArray(), new RowMapper<FilmModel>() {
            @Override
            public FilmModel mapRow(ResultSet resultSet, int i) throws SQLException {

                FilmModel filmModel = new FilmModel();

                filmModel.setFilmId(resultSet.getLong(1));
                filmModel.setTitle(resultSet.getString(2));
                filmModel.setDescription(resultSet.getString(3));
                filmModel.setReleaseYear(resultSet.getString(4));
                filmModel.setLength(resultSet.getString(9));
                filmModel.setRating(resultSet.getString(11));
                return filmModel;
            }
        });
        return resultList;
    }

    public List<ActorModel> getActorInFilm(SentFilmIdModel sentFilmIdModel){
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();
        System.out.print(sentFilmIdModel.getFilmId());

        sb.append("SELECT a.actor_id,a.first_name, a.last_name, a.last_update ");
        sb.append("FROM film f");
        sb.append(" INNER join film_actor fa on f. film_id = fa.film_id ");
        sb.append("INNER join actor a on fa.actor_id = a.actor_id ");
        sb.append("WHERE f.film_id like ? ");

        if (sentFilmIdModel.getFilmId() > 0){
            params.add(sentFilmIdModel.getFilmId());
            sb.append(" GROUP BY a.first_name ,a.last_name, a.last_update , a.actor_id ");
        }

        String sql = sb.toString();
        List<ActorModel> resultList = this.jdbcTemplate.query(sql, params.toArray(), new RowMapper<ActorModel>() {
            @Override
            public ActorModel mapRow(ResultSet resultSet, int i) throws SQLException {
                ActorModel actorModel = new ActorModel();

                actorModel.setActorId(resultSet.getLong(1));
                actorModel.setFirstName(resultSet.getString(2));
                actorModel.setLastName((resultSet.getString(3)));
                actorModel.setLastUpdate(resultSet.getString(4));
                return actorModel;
            }
        });
        return resultList;
    }
    public List<FilmModel> getFilmByActor(SentActorIdModel sentActorIdModel){
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sb.append("SELECT f.film_id ,f.title ,f.description,f.release_year, f.length  ,f.rating ");
        sb.append(" FROM film f");
        sb.append(" INNER join film_actor fa on f.film_id = fa.film_id ");
        sb.append("INNER join actor a on fa.actor_id = a.actor_id ");
        sb.append("WHERE a.actor_id like ? ");






        //เพื่อไม่ให้เกิดค่าว่าง
        if (sentActorIdModel.getActorId() > 0){
            params.add(sentActorIdModel.getActorId());
            sb.append(" GROUP BY f.film_id ,f.title ,f.description,f.release_year , f.length ,f.rating");
        }

        String sql = sb.toString();
        List<FilmModel> resultList = this.jdbcTemplate.query(sql, params.toArray(), new RowMapper<FilmModel>() {
            @Override
            public FilmModel mapRow(ResultSet resultSet, int i) throws SQLException {

                FilmModel filmModel = new FilmModel();

                filmModel.setFilmId(resultSet.getLong(1));
                filmModel.setTitle(resultSet.getString(2));
                filmModel.setDescription(resultSet.getString(3));
                filmModel.setReleaseYear(resultSet.getString(4));
                filmModel.setLength(resultSet.getString(5));
                filmModel.setRating(resultSet.getString(6));
                return filmModel;
            }
        });
        return resultList;
    }

    public List<FilmModel> getAllFilm(){
        StringBuilder str  = new StringBuilder();
        str.append("select ");
        str.append(" * ");
        str.append(" from ");
        str.append(" film");
//        str.append(" where actor_id < 20");
        String sql = str.toString();

        List<FilmModel> resultList = this.jdbcTemplate.query(sql, new RowMapper<FilmModel>() {
            @Override
            public FilmModel mapRow(ResultSet resultSet, int i) throws SQLException {

                FilmModel filmModel = new FilmModel();
                filmModel.setFilmId(resultSet.getLong(1));
                filmModel.setTitle(resultSet.getString(2));
                filmModel.setDescription(resultSet.getString(3));
                filmModel.setReleaseYear(resultSet.getString(4));
                filmModel.setLength(resultSet.getString(9));
                filmModel.setRating(resultSet.getString(11));
                return filmModel;
            }
        });
        return resultList;
    }

}
