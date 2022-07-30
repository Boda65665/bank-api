package com.example.demo.Repositories;//package com.example.demo.Repositories;

import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);
    List<Message> findByOwner(Users owner);
}
