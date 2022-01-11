package com.hotel.roommgn.service;


import com.hotel.roommgn.model.BookRoom;
import com.hotel.roommgn.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAll();

    Room saveRoom(Room room);

    Room getRoomById(Long id);

    Room updateRoom(Room room);

    void deleteRoomById(Long id);

    boolean isRoomAvailable(BookRoom bookRoom);

    BookRoom saveBookRoom(BookRoom bookRoom);
}
