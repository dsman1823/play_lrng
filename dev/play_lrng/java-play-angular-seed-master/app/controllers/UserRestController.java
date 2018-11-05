package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;
import utils.ApplicationUtil;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

public class UserRestController {

    UserService userService = UserService.getInstance();

    public Result listUsers() {
        List<User> users = userService.getAllUsers();
        ObjectMapper mapper = new ObjectMapper();
        users.forEach(u ->
        {
            u.getMessages().forEach(m -> m.setAuthor(null));
        });
        JsonNode jsonData = mapper.convertValue(users, JsonNode.class);
//        return ok(ApplicationUtil.createResponse(jsonData, true));
        return ok(jsonData).as("application/json");
    }

    public Result getUser(long id) {
        User user = userService.getUser(id);

        if (user == null) {
            return notFound(ApplicationUtil.createResponse("User with id:" + id + " not found", false));
        }

        JsonNode jsonObjects = Json.toJson(user);
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result createUser() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }

        User user = userService.createUser(Json.fromJson(json, User.class));

        if (userService.doesUserExist(user)) {
            return badRequest(ApplicationUtil.createResponse("User exists", false));
        }

        user.setMessages(null);
        JsonNode jsonObject = Json.toJson(user);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result deleteUser(Long id) {
        userService.deleteUser(id);
        return ok(ApplicationUtil.createResponse("User with id:" + id + " deleted", true));
    }
}
