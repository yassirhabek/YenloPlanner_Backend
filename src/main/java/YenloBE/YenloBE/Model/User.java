package YenloBE.YenloBE.Model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 255)
    public String Email;
    @Column(length = 255)
    public String Password;
    @Column(length = 255)
    public String Name;
    public Boolean IsManager = false;

    public User()
    {
    }

    public User(int id, String email, String password, String name, Boolean isManager) {
        Id = id;
        Email = email;
        Password = password;
        Name = name;
        IsManager = isManager;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getManager() {
        return IsManager;
    }

    public void setManager(Boolean manager) {
        IsManager = manager;
    }
}
