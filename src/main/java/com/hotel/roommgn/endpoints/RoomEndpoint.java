package com.hotel.roommgn.endpoints;

import com.hotel.roommgn.model.BookRoom;
import com.hotel.roommgn.model.Room;
import com.hotel.roommgn.model.RoomRes;
import com.hotel.roommgn.service.RoomService;
import com.hotelapp.xml.room.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Endpoint
public class RoomEndpoint {

    private static final String NAMESPACE_URI = "http://www.hotelapp.com/xml/room";
    private  final RoomService roomService;

    @Autowired
    public RoomEndpoint(RoomService roomService) {
        this.roomService = roomService;
    }




    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStringIdRoomsRequest")
    @ResponsePayload
    public GetAllStringIdRoomsResponse getAllStringIdRooms() {
        System.out.println("start to get all string rooms");
        StringBuilder stringBuilder = new StringBuilder();
        List<Room> rooms = roomService.findAll();
        stringBuilder.append("[");
        for(Room room:rooms) {
            stringBuilder.append(room.toString());
            stringBuilder.append(",");
        }
        if (rooms.size()>0)
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        GetAllStringIdRoomsResponse response= new GetAllStringIdRoomsResponse();
        response.setRoomsJson(stringBuilder.toString());
        return response;
    }



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "isRoomAvailableRequest")
    @ResponsePayload
    public IsRoomAvailableResponse isRoomAvailable(@RequestPayload IsRoomAvailableRequest request)  {
        System.out.println("checkRoomAvailability");
        Date fromDates= null;
        Date toDates= null;
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            fromDates = new SimpleDateFormat("yyyy-MM-dd").parse(request.getFromDate());
            toDates=new SimpleDateFormat("yyyy-MM-dd").parse(request.getToDate());
        } catch (ParseException e) {
            e.printStackTrace();
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Error in Date Format");
        }
        boolean result =roomService.isRoomAvailable(new BookRoom(request.getRoomId(),fromDates,toDates));
        serviceStatus.setStatusCode("SUCCESS");
        IsRoomAvailableResponse response = new IsRoomAvailableResponse();
        response.setIsRoomAvailable(result);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRoomRequest")
    @ResponsePayload
    public UpdateRoomResponse updateRoom(@RequestPayload UpdateRoomRequest request) {
        Room room = new Room(request.getName(), request.getPrice(), request.getDescription());
        room.setId(request.getRoomId());
        Room updatedRoom = roomService.updateRoom(room);
        System.out.println("Start update!");
        UpdateRoomResponse response = new UpdateRoomResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Room updated Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteRoomRequest")
    @ResponsePayload
    public DeleteRoomResponse deleteRoom(@RequestPayload DeleteRoomRequest request) {
        System.out.println("Start delete");
        roomService.deleteRoomById(request.getRoomId());
        DeleteRoomResponse response = new DeleteRoomResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Room deleted Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createRoomRequest")
    @ResponsePayload
    public CreateRoomResponse createRoom(@RequestPayload CreateRoomRequest request) {
        System.out.println("Start addRoom");
        Room room = new Room(request.getName(), request.getPrice(), request.getDescription());
        Room createdRoom = roomService.saveRoom(room);
       CreateRoomResponse response = new CreateRoomResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Room added Successfully");
       response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBookRequest")
    @ResponsePayload
    public CreateBookResponse createBook(@RequestPayload CreateBookRequest request)  {
        System.out.println("Start BookRoom!");
        ServiceStatus serviceStatus= new ServiceStatus();
        try {
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getFromDate());
            Date toDate=new SimpleDateFormat("yyyy-MM-dd").parse(request.getToDate());
            BookRoom saveBookRoom = roomService.saveBookRoom(new BookRoom(request.getRoomId(),fromDate,toDate));
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Room booked Successfully");
        } catch (ParseException e) {
            e.printStackTrace();
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Error in Date Format");
        }
        CreateBookResponse response = new CreateBookResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
