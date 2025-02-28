package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car){
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        carIterator.forEachRemaining(carList::add);
        return carList;
    }

    @Override
    public Car findById(String carId) {
        Car car = carRepository.findById(Integer.parseInt(carId));
        return car;
    }

    @Override
    public void update(String carId, Car updatedCar) {
        carRepository.update(carId, updatedCar);
    }

    @Override
    public void deleteCarById(String carId){
        carRepository.delete(carId);
    }
}