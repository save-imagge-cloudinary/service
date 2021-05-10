package com.example.service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
Created by NhanNguyen on 5/8/2021
@author: NhanNguyen
@date: 5/8/2021
*/

@Entity(name = "image")
@Getter
@Setter
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_id")
    private String imageId;

}
