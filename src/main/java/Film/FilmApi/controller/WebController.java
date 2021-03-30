package Film.FilmApi.controller;


import Film.FilmApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Film.FilmApi.service.WebService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebController {
    @Autowired
    private WebService webService;

    @CrossOrigin("*")
    @RequestMapping(value = "/getAllActor", method = RequestMethod.GET)
    public ResponseModel<List<ActorModel>> getAllActor(){

        return this.webService.getAllActor();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getAllFilm", method = RequestMethod.GET)
    public ResponseModel<List<FilmModel>> getAllFilm(){

        return this.webService.getAllFilm();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getSearchFilm", method = RequestMethod.POST)
    public ResponseModel<List<FilmModel>> getSearchFilm(@RequestBody SearchFilmModel searchFilmModel){

        return this.webService.getSearchFilm(searchFilmModel);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getSearchActor", method = RequestMethod.POST)
    public ResponseModel<List<ActorModel>> getSearchActor(@RequestBody SearchActorModel searchActorModel){

        return this.webService.getSearchActor(searchActorModel);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/getActorInFilm", method = RequestMethod.POST)
    public ResponseModel<List<ActorModel>> getActorInFilm(@RequestBody SentFilmIdModel sentFilmIdModel){

        return this.webService.getActorInFilm(sentFilmIdModel);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getFilmByActor", method = RequestMethod.POST)
    public ResponseModel<List<FilmModel>> getFilmByActor(@RequestBody SentActorIdModel sentActorIdModel){

        return this.webService.getFilmByActor(sentActorIdModel);
    }
}
