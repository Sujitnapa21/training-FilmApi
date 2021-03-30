package Film.FilmApi.service;

import Film.FilmApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Film.FilmApi.repository.FilmRepository;

import java.util.List;

@Service
public class WebService {
    @Autowired
    private FilmRepository filmRepository;

    public ResponseModel<List<ActorModel>> getAllActor(){
        ResponseModel<List<ActorModel>> result = new ResponseModel<List<ActorModel>>();
        try {
            List<ActorModel> data = this.filmRepository.getAllActor();
            result.setStatus("OK");
            result.setMessage("Get Actor success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());

        }
        return result;
    }

    public ResponseModel<List<FilmModel>> getAllFilm(){
        ResponseModel<List<FilmModel>> result = new ResponseModel<List<FilmModel>>();
        try {
            List<FilmModel> data = this.filmRepository.getAllFilm();
            result.setStatus("OK");
            result.setMessage("Get Actor success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());

        }
        return result;
    }

    public ResponseModel<List<FilmModel>> getSearchFilm(SearchFilmModel searchFilmModel){
        ResponseModel<List<FilmModel>> result = new ResponseModel<List<FilmModel>>();
        try {
            List<FilmModel> data = this.filmRepository.getSearchFilm(searchFilmModel);
            result.setStatus("OK");
            result.setMessage("Get Film success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<ActorModel>> getActorInFilm(SentFilmIdModel id){
        ResponseModel<List<ActorModel>> result = new ResponseModel<List<ActorModel>>();
        try {
            List<ActorModel> data = this.filmRepository.getActorInFilm(id);
            result.setStatus("OK");
            result.setMessage("Collect Actor success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<FilmModel>> getFilmByActor(SentActorIdModel id){
        ResponseModel<List<FilmModel>> result = new ResponseModel<List<FilmModel>>();
        try {
            List<FilmModel> data = this.filmRepository.getFilmByActor(id);
            result.setStatus("OK");
            result.setMessage("Collect Film Title success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ResponseModel<List<ActorModel>> getSearchActor(SearchActorModel searchActorModel){
        ResponseModel<List<ActorModel>> result = new ResponseModel<List<ActorModel>>();
        try {
            List<ActorModel> data = this.filmRepository.getSearchActor(searchActorModel);
            result.setStatus("OK");
            result.setMessage("Get Actor success");
            result.setData(data);
        }catch (Exception e){
            result.setStatus("ERROR");
            result.setMessage("data base error" + e.getMessage());
        }
        return result;
    }
}
