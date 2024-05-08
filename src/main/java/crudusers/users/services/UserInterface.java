package crudusers.users.services;

import crudusers.users.exeptions.BusinessException;
import crudusers.users.models.UserDTO;
import crudusers.users.models.UsersModel;

import java.util.List;

public interface UserInterface {
    UserDTO createUser(UsersModel userDTO) throws BusinessException;

    UserDTO getUserById(int userId) throws BusinessException;

    List<UserDTO> getAllUsers() throws BusinessException;

    UsersModel updateUser(UsersModel userDTO) throws BusinessException;

    void deleteUser(int userId) throws BusinessException;
}
