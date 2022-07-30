package com.example.demo.Repositories;//package com.example.demo.Repositories;

import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    Chat findById(int id);
    @Query(value = "select c FROM Chat c where c.owner.id=:id or c.friend=:id",nativeQuery = false)
    List<Chat> findByUser(@Param("id") int id);

}
