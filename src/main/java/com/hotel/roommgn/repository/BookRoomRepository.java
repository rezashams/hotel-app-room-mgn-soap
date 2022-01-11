/*
 * Copyright (c) 2021 Birmingham City University. All rights reserved.
 * Author:  Reza Shams (rezashams86@gmail.com)
 */
package com.hotel.roommgn.repository;

import com.hotel.roommgn.model.BookRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRoomRepository extends CrudRepository<BookRoom,Long> {

   @Query("select case when count(*)= 0 then true else false end  FROM BookRoom " +
           "WHERE (:fromDate  BETWEEN date_from AND date_to " +
           "OR :toDate  BETWEEN date_from AND date_to " +
           "OR (:fromDate <= date_from and :toDate >= date_to)) " +
           "AND room_id= :roomId")
   boolean isRoomAvailable(@Param("roomId") Long roomId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);


}
