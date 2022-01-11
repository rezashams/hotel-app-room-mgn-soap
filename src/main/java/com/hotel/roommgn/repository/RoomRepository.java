package com.hotel.roommgn.repository;

import com.hotel.roommgn.model.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("select u from room u where id= :roomId")
    Room findById(@Param("roomId") Long roomId);

    @Transactional
    @Modifying
    @Query("delete  from room  where id= :roomId")
    void deleteById(@Param("roomId") Long roomId);
}
