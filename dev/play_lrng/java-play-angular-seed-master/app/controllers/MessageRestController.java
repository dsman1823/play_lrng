package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Message;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.MessageService;
import services.UserService;
import utils.ApplicationUtil;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

public class MessageRestController {

    MessageService messageService = MessageService.getInstance();
    UserService userService = UserService.getInstance();

    public Result listMessages() {
        List<Message> messages = messageService.getAllMessages();
        ObjectMapper mapper = new ObjectMapper();

        messages.forEach(m -> m.setAuthor(null));

        JsonNode jsonData = mapper.convertValue(messages, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    public Result getMessage(Long id) {
        Message message = messageService.getMessage(id);

        if (message == null) {
            return notFound(ApplicationUtil.createResponse("Message with id:" + id + " not found", false));
        }

        JsonNode jsonObjects = Json.toJson(message);
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result createMessage(Long userId) {
        JsonNode json = request().body().asJson();
        if (json == null || !userService.doesUserExist(userId)) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }

        User user = userService.getUser(userId);
        Message message = messageService.createMessage(user, Json.fromJson(json, Message.class));
        message.setAuthor(null);
        JsonNode jsonObject = Json.toJson(message);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result deleteMessage(Long id) {
        messageService.deleteMessage(id);
        return ok(ApplicationUtil.createResponse("Message with id:" + id + " deleted", true));
    }

    public Result updateMessage(Long messageId) {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }

        Message newMessage = Json.fromJson(json, Message.class);
        Message oldMessage = messageService.getMessage(messageId);
        messageService.updateMessage(oldMessage, newMessage);
        oldMessage.setAuthor(null);
        JsonNode jsonObject = Json.toJson(oldMessage);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}

