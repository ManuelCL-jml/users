package crudusers.users.services;

import crudusers.users.exeptions.BusinessException;
import crudusers.users.models.UserDTO;
import crudusers.users.models.UsersModel;
import crudusers.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface{
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UsersModel usersModel) throws BusinessException {
        // Crear una nueva entidad de usuario a partir del DTO
        UsersModel userEntity = new UsersModel();
        userEntity.setUserName(usersModel.getUserName());

        // Guardar la entidad en la base de datos y obtener el ID generado
        userEntity = userRepository.save(userEntity);

        // Asignar el ID generado al UserDTO y devolverlo
        usersModel.setUser_id(userEntity.getUser_id());

        return  convertToDTO(userEntity);
    }

    public UserDTO getUserById(int userId) throws BusinessException {
        // Obtener la entidad de usuario por su ID desde el repositorio
        UsersModel userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        // Transformar la entidad en DTO y devolverlo
        return convertToDTO(userEntity);
    }

    public List<UserDTO> getAllUsers() throws BusinessException {
        // Obtener todas las entidades de usuario desde el repositorio
        List<UsersModel> userEntities = userRepository.findAll();

        // Transformar las entidades en DTOs y devolverlos
        return userEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsersModel updateUser(UsersModel userDTO) throws BusinessException {
        // Verificar si el usuario existe en la base de datos
        UsersModel existingUser = userRepository.findById(userDTO.getUser_id())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        // Actualizar los datos del usuario con los proporcionados en el DTO
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setLast_name(userDTO.getLast_name());
        existingUser.setFirst_name(userDTO.getFirst_name());
        existingUser.setMiddle_name(userDTO.getMiddle_name());


        // Guardar la entidad actualizada en la base de datos
        UsersModel updatedUser = userRepository.save(existingUser);

        // Transformar la entidad actualizada en DTO y devolverlo
        return updatedUser;
    }

    public void deleteUser(int userId) throws BusinessException {
        // Verificar si el usuario existe en la base de datos
        UsersModel existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        // Eliminar al usuario de la base de datos
        userRepository.delete(existingUser);
    }

    // Método para convertir una entidad de usuario en un DTO
    private UserDTO convertToDTO(UsersModel userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUser_id());
        userDTO.setUserName(userEntity.getUserName());
        return userDTO;
    }
}
