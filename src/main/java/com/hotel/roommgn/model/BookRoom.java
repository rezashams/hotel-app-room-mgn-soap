/*
 * Copyright (c) 2021 Birmingham City University. All rights reserved.
 * Author:  Reza Shams (rezashams86@gmail.com)
 */
package com.hotel.roommgn.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "BookRoom")
@Table(name = "book_room")
public class BookRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "room_id", nullable = false)
    private long roomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_from", nullable = false)
    private Date fromDate;

    @Column(name = "date_to", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    public BookRoom() {
    }

    public BookRoom(long roomId, Date fromDate, Date toDate) {
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
