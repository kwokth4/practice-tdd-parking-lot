package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.park(car);
        //should
        assertNotNull(ticket);
    }
    @Test
    void should_return_car_when_fetch_given_ticket_and_parked_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        //when
        Car fetchedCar = parkingBoy.fetch(ticket);
        //should
        assertEquals(car, fetchedCar);
    }
    @Test
    void should_return_correct_car_when_fetch_given_two_ticket_and_two_parked_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        //when
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        //should
        assertEquals(car1,fetchedCar1);
    }
    @Test
    void should_return_exception_when_fetch_given_unrecognized_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket unrecognizedTicket = new Ticket();
        //when
        //should
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(unrecognizedTicket));
        assertEquals("Unrecognized parking ticket.",exception.getMessage());
    }
    @Test
    void should_return_exception_when_fetch_given_used_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);
        //when
        //should
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized parking ticket.",exception.getMessage());
    }
    @Test
    void should_return_exception_when_park_given_full_parking_lot(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(new Car());
        //when
        //should
        Exception exception = assertThrows(parkingLotFullException.class,
                () -> parkingBoy.park(car));
        assertEquals("No available position.",exception.getMessage());
    }
    @Test
    void should_return_ticket_when_park_given_two_parking_lot_and_car(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.park(car);
        //should
        assertNotNull(ticket);
    }
    @Test
    void should_return_second_parking_lot_when_park_given_first_of_the_two_parking_lot_is_full_and_car(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingLot1.park(new Car());
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.park(car);
        //should
        assertEquals(parkingLot2,ticket.getParkingLotOfTheTicket());
    }
    @Test
    //Case 19
    void should_return_car_when_park_given_two_parking_lot_and_two_car_are_in_different_parking_lot(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        parkingLot1.park(car1);
        Car car2 = new Car();
        parkingLot2.park(car2);
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        //when
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        //should
        assertEquals(car1,fetchedCar1);
        assertEquals(car2,fetchedCar2);
    }
    @Test
    //case 20
    void should_return_exception_when_fetch_given_unrecognized_ticket_and_two_parking_lot(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket unrecognizedTicket = new Ticket();
        //when
        //should
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(unrecognizedTicket));
        assertEquals("Unrecognized parking ticket.",exception.getMessage());
    }
    @Test
    //case 21
    void should_return_exception_when_fetch_given_used_ticket_and_two_parking_lot(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);
        //when
        //should
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized parking ticket.",exception.getMessage());
    }
    @Test
    //case 22
    void should_return_exception_when_park_given_full_parking_lot_and_two_parking_slot(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        //when
        //should
        Exception exception = assertThrows(parkingLotFullException.class,
                () -> parkingBoy.park(car));
        assertEquals("No available position.",exception.getMessage());
    }
}
