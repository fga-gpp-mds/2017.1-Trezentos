package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.DAO.PutDao;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import okhttp3.HttpUrl;
import okhttp3.MediaType;

public class UserExamControl{
    private static UserExamControl instance;
    private final Context context;
    private Exam exam;

    public UserExamControl(final Context context){
        this.context = context;
    }

    public static UserExamControl getInstance(final Context context){
        if(instance == null){
            instance = new UserExamControl(context);
        }
        return instance;
    }

    public String validateInformation(String examName, String userClassName, String classOwnerEmail)
            throws UserException{
        try{
            exam = new Exam(examName, userClassName, classOwnerEmail);
            return "Sucesso";
        }catch(UserException userException){
            return userException.getMessage();
        }
    }

    public void validateCreateExam(String examName, String userClassName, String classOwnerEmail)
            throws UserException{
        try{
            Exam exam = new Exam(examName, userClassName, classOwnerEmail);
            HttpUrl.Builder builder = HttpUrl.parse(
                    "https://trezentos-api.herokuapp.com/api/exam/register").newBuilder();

            builder.addQueryParameter("name", exam.getNameExam());
            builder.addQueryParameter("userClassName", exam.getUserClassName());
            builder.addQueryParameter("classOwnerEmail", exam.getClassOwnerEmail());

            PostDao postDao = new PostDao(builder.build().toString(), null, "");
            postDao.execute();
        }catch(UserException userException){
            userException.printStackTrace();
        }
    }

    public String validateAddsGrades(UserClass userClass, Exam exam, String gradeType)
            throws UserClassException, ExecutionException, InterruptedException{
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        String body = (gradeType == "first_grades") ?
                createGradesBody(userClass, exam, "firstGrades") :
                createGradesBody(userClass, exam, "secondGrades");
        String url = "https://trezentos-api.herokuapp.com/api/exam/";

        // return response
        return new PutDao(url + gradeType, json, body).execute().get();
    }

    // Get from api
    public ArrayList<Exam> getExamsFromUser(String email, String userClassName){
        String url = "https://trezentos-api.herokuapp.com/api/exam/class/user/find";

        String serverResponse = "404";
        serverResponse = new GetDao(getExamAvaiableUrl
                (email, userClassName, url)).get();

        ArrayList<Exam> userExams = new ArrayList<Exam>();

        try{
            userExams = getArrayList(serverResponse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return userExams;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getExamAvaiableUrl(String email, String userClassName, String url){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("email", email);
        builder.addQueryParameter("userClassName", userClassName);
        return builder.build().toString();
    }

    private ArrayList<Exam> getArrayList(String serverResponse) throws JSONException{
        JSONArray array = null;

        try{
            array = new JSONArray(serverResponse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        ArrayList<Exam> userExams = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            Exam exam = getUserExamFromJson(array.getJSONObject(i));
            userExams.add(exam);
        }

        return userExams;
    }

    private Exam getUserExamFromJson(JSONObject jsonObject) {
        Exam exam = new Exam();

        try{
            exam.setNameExam(jsonObject.getString("name"));
            exam.setUserClassName(jsonObject.getString("userClassName"));
            exam.setClassOwnerEmail(jsonObject.getString("classOwnerEmail"));
            exam.setFirstGrades(jsonObject.getString("firstGrades"));
        }catch(JSONException | UserException e){
            e.printStackTrace();
        }

        return exam;
    }


    public String createGradesBody(UserClass userClass, Exam exam, String grade) {
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email", exam.getClassOwnerEmail());
            jsonBody.put("userClassName", userClass.getClassName());
            jsonBody.put("name", exam.getNameExam());
            jsonBody.put(grade, exam.getFirstGrades());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody.toString();
    }
}