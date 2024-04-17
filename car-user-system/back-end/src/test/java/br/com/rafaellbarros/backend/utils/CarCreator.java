package br.com.rafaellbarros.backend.utils;

import br.com.rafaellbarros.core.model.dto.CarDTO;

public class CarCreator {
    public static CarDTO createValidCarDTO() {
        return CarDTO.builder()
                .year(2018L)
                .licensePlate("PDV-0625")
                .model("Audi")
                .color("White")
                .build();
    }
}
