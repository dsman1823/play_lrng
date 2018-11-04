package services;

import models.Message;
import models.User;

import java.util.List;

public class MessageService {

    private static MessageService instance;

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService();
        }
        return instance;
    }

    public Message getMessage(Long id) {
        return Message.find.byId(id);
    }

    public List<Message> getAllMessages() {
        return Message.find.all();
    }

    public Message createMessage(User user, Message message) {
        message.setAuthor(user);
        message.save();
        user.getMessages().add(message);
        user.save();
        return message;
    }

    public void deleteMessage(Long id) {
        Message.find.deleteById(id);
    }

    public Message updateMessage(Message updated, Message newInfo) {
        updated.setText(newInfo.getText());
        updated.setTitle(newInfo.getTitle());
        updated.setSpeciality(newInfo.getSpeciality());
        updated.setDescription(newInfo.getDescription());
        updated.save();
        return updated;
    }
}
