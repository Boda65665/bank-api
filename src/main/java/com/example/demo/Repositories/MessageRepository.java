package com.example.demo.Repositories;//package com.example.demo.Repositories;

import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Status;
import com.example.demo.Entiti.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);
    @Query(value = "select * from message where chat=:id ORDER BY time",nativeQuery = true)
    List<Message> findTimeChat(@Param("id") int id);
    List<Message> findByOwner(Users owner);
    List<Message> findByOwnerAndStatusAndChat(Users owner, Status status, Chat chat);
}
