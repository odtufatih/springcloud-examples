package com.fatihk.examples.springcloud.foa.restaurant.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8571261118900116242L;

    @Id
    //private String id = UUID.randomUUID().toString();
    private String id; //let mongo db autogenerate id
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}