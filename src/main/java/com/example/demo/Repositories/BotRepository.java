package com.example.demo.Repositories;

import com.example.demo.Entiti.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepository extends JpaRepository<Bot,Integer> {
    Bot findById(int id);
    Bot findByToken(String token);
}
