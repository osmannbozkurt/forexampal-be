package com.fep.forexampal.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "NOTIFICATION")
@SequenceGenerator(name = "SEQ_NOTIFICATION", sequenceName = "SEQ_NOTIFICATION")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(generator = "SEQ_NOTIFICATION", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    //Soru Cevaplandı, vs vs
    @Column(name = "detail_type")
    private int detailType;

    //Kullanıucı tıklayıp açtığında sayfaya gönderilecek parametreler string json olacak
    @Column(name = "notification_params")
    private String notificationParams;

    @Column(name = "date")
    private Date date;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "read_date")
    private Date readDate;


}
