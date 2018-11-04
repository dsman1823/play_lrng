package models;

import io.ebean.Finder;
import org.hibernate.validator.constraints.NotBlank;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User extends BaseModel {

    @NotBlank(message = "message can't be Blank")
    private String username;
    @NotBlank(message = "password can't be empty")
    private String password;

    @Constraints.Email(message = "invalid email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Object clone() {
        User clone = new User();
        clone.setUsername(getUsername());
        clone.setPassword(this.getPassword());
        clone.setEmail(getEmail());
        return clone;
    }

    public static final Finder<Long, User> find = new Finder<>(User.class);


    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}