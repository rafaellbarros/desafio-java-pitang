package br.com.rafaellbarros.backend.utils;

import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;

public class CarCreator {

    public static CarDTO createCarDTOtoBeSaved() {
        return CarDTO.builder()
                .year(2024L)
                .licensePlate("KSK-2025")
                .model("Mercedes-Benz")
                .color("AZUL")
                .build();
    }

    public static CarDTO createValidCarDTO() {
        return CarDTO.builder()
                .id(1L)
                .year(2018L)
                .licensePlate("PDV-0625")
                .model("Audi")
                .color("White")
                .build();
    }

    public static CarDTO createValidUpdateCarDTO() {
        return CarDTO.builder()
                .id(1L)
                .year(2024L)
                .licensePlate("PDV-0625")
                .model("Audi Update")
                .color("White Update")
                .build();
    }

    public static Car createValidCar() {
        return Car.builder()
                .id(1L)
                .year(2018L)
                .licensePlate("PDV-0625")
                .model("Audi")
                .color("White")
                .build();
    }
}
