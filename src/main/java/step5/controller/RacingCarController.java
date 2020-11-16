package step5.controller;

import step5.domain.Car;
import step5.domain.RacingWinner;
import step5.util.CarUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarController {
    private final List<Car> cars;
    private final int numberOfMove;
    private int round;

    public RacingCarController(List<Car> cars, int numberOfMove) {
        this.cars = cars;
        this.numberOfMove = numberOfMove;
    }

    public static RacingCarController of(String[] carNames, int numberOfMove) {
        CarUtils.carNameMaxValid(carNames);
        return new RacingCarController(newCars(carNames), numberOfMove);
    }

    private static List<Car> newCars(String[] racingCars) {
        return Arrays.stream(racingCars)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void racing() {
        cars.forEach(car -> car.move(CarUtils.getRandom()));
        hasNextRound();
    }

    private void hasNextRound() {
        round++;
    }

    public List<Car> gameProgress() {
        racing();
        return getCars();
    }

    public boolean isFinish() {
        return numberOfMove == round;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public String getWinners(){
        RacingWinner racingWinner = new RacingWinner(cars);
        return racingWinner.getWinners();
    }
}
