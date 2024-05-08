package crudusers.users.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;
    public String userName;
    public String password;
    public String first_name;
    public String middle_name;
    public String last_name;
    public String email;
}
