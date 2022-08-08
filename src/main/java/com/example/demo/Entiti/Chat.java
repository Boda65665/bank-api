package com.example.demo.Entiti;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
@Data
public class Chat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "owner")
    private Users owner;



    @ManyToOne
    @JoinColumn(name = "friend")
    private Users friend;

    @OneToMany(mappedBy = "chat")
    List<Message> messageList;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                '}';
    }
}
