package crudusers.users.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserDTO {
    @Id
    private int userId;
    private String userName;


    public UserDTO() {

    }

}
