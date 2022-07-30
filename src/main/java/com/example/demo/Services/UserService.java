package com.example.demo.Services;

import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    UserRepository repositori;

    UserConverters userConverters = new UserConverters();
    public int saveUser(UserDTO usersDto)  {

        Users users = new Users();
        users = userConverters.FromUserDTOinUsers(usersDto);
        repositori.save(users);
        return users.getId();
    }
    public List<UserDTO> findLoginStartingWith(String login){
        List<Users> users = repositori.findByLoginStartingWith(login);
        List<UserDTO> userDTOList = new ArrayList<>();
        for(Users user: users){
            UserDTO userDTO = new UserDTO();
            userDTO = userConverters.FromUsersInUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;

    }
    public UserDTO findByEmail(String email)  {
        if(repositori.findByEmail(email)==null){
            return null;
        }
        Users users = repositori.findByEmail(email);
        UserDTO userDTO = userConverters.FromUsersInUserDTO(repositori.findByEmail(email));

        return userDTO;
    }
//    public List<UserDTO> findByEmailStartingWith(String login){
//        List<Users> users = repositori.findByLoginStartingWith(login);
//        List<UserDTO> userDTOList = new ArrayList<>();
//        for(Users user: users){
//            UserDTO userDTO = new UserDTO();
//            userDTO = userConverters.FromUsersInUserDTO(user);
//            userDTOList.add(userDTO);
//        }
//        return userDTOList;
//
//
//    }


    public void deleteUser(int id) {
        repositori.deleteById(id);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        List<Users> user_list = new ArrayList<Users>();
        user_list = repositori.findAll();
        System.out.println(user_list.size());


        for(int i=0;i<user_list.size();i++){
            Users users = user_list.get(i);
            UserDTO userDTO = userConverters.FromUsersInUserDTO(user_list.get(i));



            userDTOList.add(i,userDTO);
            System.out.println(i);


        }
        return userDTOList;
    }

    public UserDTO findById(int id){
        if(repositori.findById(id)==null) return null;

        Users users = repositori.findById(id);
    UserDTO userDTO = userConverters.FromUsersInUserDTO(repositori.findById(id));



        return userDTO;
}
}
