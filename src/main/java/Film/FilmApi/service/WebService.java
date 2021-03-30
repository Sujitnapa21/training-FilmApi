package Film.FilmApi.service;

import Film.FilmApi.model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Film.FilmApi.repository.FilmRepository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void filmExportExcel(HttpServletResponse response){
        response.setHeader("Content-disposition", "attachment;filename=filmList.xlsx");
        response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OutputStream os = null;

        try{
            os = response.getOutputStream();
            File file = new File("C:/Users/Sujitnapa21/Priorr/training-FilmApi/src/main/resources/excel-template/templat01.xlsx");
            FileInputStream fileInputStream = new FileInputStream((file));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet1");

            //init header
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateForm = simpleDateFormat.format(now);
            String dateTo = simpleDateFormat.format(now);

            //row 4
            Row row4 = sheet.getRow(4);

            Cell cell4 = row4.createCell(4);
            cell4.setCellValue(dateForm);
            //row4

            //fill Data
            List<FilmModel> filmEntityList = this.filmRepository.getOnly20Film();
            int rowStart = 6;
            for (int i = 0; i < filmEntityList.size(); i++) {
                FilmModel fi = filmEntityList.get(i);

                Row rowData = sheet.createRow(rowStart);

                Cell data0 = rowData.createCell(0);
                data0.setCellValue(fi.getFilmId());

                Cell data1 = rowData.createCell(1);
                data1.setCellValue(fi.getTitle());

                Cell data2 = rowData.createCell(2);
                data2.setCellValue(fi.getDescription());

                Cell data3 = rowData.createCell(3);
                data3.setCellValue(fi.getReleaseYear());

                Cell data6 = rowData.createCell(4);
                data6.setCellValue(fi.getLength());

                Cell data7 = rowData.createCell(5);
                data7.setCellValue(fi.getRating());

                rowStart++;
            }

            workbook.write(os);
            os.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void actorExportExcel(HttpServletResponse response){response.setHeader("Content-disposition", "attachment;filename=actorList.xlsx");
        response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OutputStream os = null;

        try{
            os = response.getOutputStream();
            File file = new File("C:/Users/Sujitnapa21/Priorr/training-FilmApi/src/main/resources/excel-template/templat02.xlsx");
            FileInputStream fileInputStream = new FileInputStream((file));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet1");

            //init header
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateForm = simpleDateFormat.format(now);
            String dateTo = simpleDateFormat.format(now);

            //row 4
            Row row4 = sheet.getRow(4);

            Cell cell4 = row4.createCell(4);
            cell4.setCellValue(dateForm);
            //row4

            //fill Data
            List<ActorModel> actorEntityList = this.filmRepository.getAllActor();
            int rowStart = 6;
            for (int i = 0; i < actorEntityList.size(); i++) {
                ActorModel actorModel = actorEntityList.get(i);

                Row rowData = sheet.createRow(rowStart);

                Cell data0 = rowData.createCell(0);
                data0.setCellValue(actorModel.getActorId());

                Cell data1 = rowData.createCell(1);
                data1.setCellValue(actorModel.getFirstName());

                Cell data2 = rowData.createCell(2);
                data2.setCellValue(actorModel.getLastName());

                Cell data3 = rowData.createCell(3);
                data3.setCellValue(actorModel.getLastUpdate());


                rowStart++;
            }

            workbook.write(os);
            os.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void searchActorExportExcel(HttpServletResponse response, SearchActorModel searchActorModel ){response.setHeader("Content-disposition", "attachment;filename=searchActorList.xlsx");
        response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OutputStream os = null;

        try{
            os = response.getOutputStream();
            File file = new File("C:/Users/Sujitnapa21/Priorr/training-FilmApi/src/main/resources/excel-template/templat02.xlsx");
            FileInputStream fileInputStream = new FileInputStream((file));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet1");

            //init header
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateForm = simpleDateFormat.format(now);
            String dateTo = simpleDateFormat.format(now);

            //row 4
            Row row4 = sheet.getRow(4);

            Cell cell4 = row4.createCell(4);
            cell4.setCellValue(dateForm);
            //row4

            //fill Data
            List<ActorModel> actorEntityList = this.filmRepository.getSearchActor(searchActorModel);
            int rowStart = 6;
            for (int i = 0; i < actorEntityList.size(); i++) {
                ActorModel actorModel = actorEntityList.get(i);

                Row rowData = sheet.createRow(rowStart);

                Cell data0 = rowData.createCell(0);
                data0.setCellValue(actorModel.getActorId());

                Cell data1 = rowData.createCell(1);
                data1.setCellValue(actorModel.getFirstName());

                Cell data2 = rowData.createCell(2);
                data2.setCellValue(actorModel.getLastName());

                Cell data3 = rowData.createCell(3);
                data3.setCellValue(actorModel.getLastUpdate());


                rowStart++;
            }

            workbook.write(os);
            os.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void searchFilmExportExcel(HttpServletResponse response, SearchFilmModel searchFilmModel){
        response.setHeader("Content-disposition", "attachment;filename=filmList.xlsx");
        response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OutputStream os = null;

        try{
            os = response.getOutputStream();
            File file = new File("C:/Users/Sujitnapa21/Priorr/training-FilmApi/src/main/resources/excel-template/templat01.xlsx");
            FileInputStream fileInputStream = new FileInputStream((file));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet1");

            //init header
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateForm = simpleDateFormat.format(now);
            String dateTo = simpleDateFormat.format(now);

            //row 4
            Row row4 = sheet.getRow(4);

            Cell cell4 = row4.createCell(4);
            cell4.setCellValue(dateForm);
            //row4

            //fill Data
            List<FilmModel> filmEntityList = this.filmRepository.getSearchFilm(searchFilmModel);
            int rowStart = 6;
            for (int i = 0; i < filmEntityList.size(); i++) {
                FilmModel fi = filmEntityList.get(i);

                Row rowData = sheet.createRow(rowStart);

                Cell data0 = rowData.createCell(0);
                data0.setCellValue(fi.getFilmId());

                Cell data1 = rowData.createCell(1);
                data1.setCellValue(fi.getTitle());

                Cell data2 = rowData.createCell(2);
                data2.setCellValue(fi.getDescription());

                Cell data3 = rowData.createCell(3);
                data3.setCellValue(fi.getReleaseYear());

                Cell data6 = rowData.createCell(4);
                data6.setCellValue(fi.getLength());

                Cell data7 = rowData.createCell(5);
                data7.setCellValue(fi.getRating());

                rowStart++;
            }

            workbook.write(os);
            os.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
