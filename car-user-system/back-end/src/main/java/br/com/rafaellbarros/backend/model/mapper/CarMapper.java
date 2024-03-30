package br.com.rafaellbarros.backend.model.mapper;

import br.com.rafaellbarros.domain.core.model.mapper.BaseMapper;
import br.com.rafaellbarros.domain.model.dto.CarDTO;
import br.com.rafaellbarros.domain.model.entity.Car;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper extends BaseMapper<Car, CarDTO> {

    @Override
    CarDTO toDTO(Car entity);

    @Override
    Car toEntity(CarDTO dto);

    @Override
    List<CarDTO> toDTO(List<Car> entities);

    @Override
    List<Car> toEntity(List<CarDTO> dtos);

    @Override
    @InheritInverseConfiguration(name = "toDTO")
    void fromDTO(CarDTO dto, @MappingTarget Car entity);
}
