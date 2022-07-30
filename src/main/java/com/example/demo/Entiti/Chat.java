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

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinTable(name = "message_in_chat",joinColumns={@JoinColumn(name = "chat")},
            inverseJoinColumns = {@JoinColumn(name = "message")}
    )
    List<Message> messageList;

}
