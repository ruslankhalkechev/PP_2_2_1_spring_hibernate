package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CarService {
    List<Car> listCars();

    void showListOfCars(List<Car> carList);
}
