package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;
import models.StudentStore;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Util;

import java.util.Set;

public class StudentController extends Controller {

    public static Result create() {
        JsonNode json = request().body().asJson();
        if (json == null){
            return badRequest(Util.createResponse(
                    "Expecting Json data", false));
        }
        Student student = StudentStore.getInstance().addStudent(
                (Student) Json.fromJson(json, Student.class));
        JsonNode jsonObject = Json.toJson(student);
        return created(Util.createResponse(jsonObject, true));
    }

    public static Result update() {
        JsonNode json = request().body().asJson();
        if (json == null){
            return badRequest(Util.createResponse(
                    "Expecting Json data", false));
        }
        Student student = StudentStore.getInstance().updateStudent(
                (Student) Json.fromJson(json, Student.class));
        if (student == null) {
            return notFound(Util.createResponse(
                    "Student not found", false));
        }

        JsonNode jsonObject = Json.toJson(student);
        return ok(Util.createResponse(jsonObject, true));
    }

    public static Result retrieve(int id) {
        Student student = StudentStore.getInstance().getStudent(id);
        if (student == null) {
            return notFound(Util.createResponse(
                    "Student with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(student);
        return ok(Util.createResponse(jsonObjects, true));
    }

    public static Result delete(int id) {
        boolean status = StudentStore.getInstance().deleteStudent(id);
        if (!status) {
            return notFound(Util.createResponse(
                    "Student with id:" + id + " not found", false));
        }
        return ok(Util.createResponse(
                "Student with id:" + id + " deleted", true));
    }

    public static Result listStudents() {
        Set<Student> result = StudentStore.getInstance().getAllStudents();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(Util.createResponse(jsonData, true));
    }

}
