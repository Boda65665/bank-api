package com.example.demo.Entiti;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Data
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    private String text;
    @Column(name = "time")
    private Date time;
    @OneToOne
    @JoinColumn(name = "owner",referencedColumnName="id")
    private Users owner;



}
