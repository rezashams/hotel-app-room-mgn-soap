/*
 * Copyright (c) 2021 Birmingham City University. All rights reserved.
 * Author:  Reza Shams (rezashams86@gmail.com)
 */
package com.hotel.roommgn.service;

import com.hotel.roommgn.model.BookRoom;
import com.hotel.roommgn.model.Room;
import com.hotel.roommgn.repository.BookRoomRepository;
import com.hotel.roommgn.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final BookRoomRepository bookRoomRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookRoomRepository bookRoomRepository) {
        this.roomRepository = roomRepository;
        this.bookRoomRepository = bookRoomRepository;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        Iterator<Room> roomIterator= roomRepository.findAll().iterator();
        while(roomIterator.hasNext()) {
            rooms.add(roomIterator.next());
        }
        return rooms;
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public boolean isRoomAvailable(BookRoom bookRoom) {
        return bookRoomRepository.isRoomAvailable(bookRoom.getRoomId(),
                new SimpleDateFormat("yyyy-MM-dd").format(bookRoom.getFromDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(bookRoom.getToDate()));
    }

    @Override
    public BookRoom saveBookRoom(BookRoom bookRoom) {
        return bookRoomRepository.save(bookRoom);
    }
}
