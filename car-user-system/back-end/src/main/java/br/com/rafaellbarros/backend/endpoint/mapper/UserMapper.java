package br.com.rafaellbarros.backend.endpoint.mapper;


import br.com.rafaellbarros.core.model.dto.CarDTO;
import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.Car;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.model.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {

    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);

    List<UserDTO> toDTO(List<User> entities);

    List<UserDTO> toDTO(Iterable<User> entities);

    List<User> toEntity(List<UserDTO> dtos);

    @InheritInverseConfiguration(name = "toDTO")
    void fromDTO(UserDTO dto, @MappingTarget User entity);

    CarDTO carToDto(Car entity);

    @InheritInverseConfiguration(name = "carToDto")
    Car carFromDto(CarDTO dto);

    @AfterMapping
    default void afterMapEntityTelefone(@MappingTarget User entity) {
        List<Car> cars = entity.getCars();
        if (cars != null) {
            cars.forEach(car -> car.setUser(entity));
        }
    }

}
