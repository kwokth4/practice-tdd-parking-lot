package com.parkinglot;

import org.junit.jupiter.api.Test;

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
}
