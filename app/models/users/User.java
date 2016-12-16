package models.users;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class User extends Model{

    @Id
    private String email;

    @Constraints.Required
    private String role;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String password;

    public User() {

    }

    public static Finder<String, User> find = new Finder<String, User>(User.class);

    public static List<User> findAll() {
        return User.findAll();
    }

    public static User authenticate(String email, String password){
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    public static User getUserById(String id) {
        if (id == null) {
            return null;
        }
        else
            return find.byId(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
