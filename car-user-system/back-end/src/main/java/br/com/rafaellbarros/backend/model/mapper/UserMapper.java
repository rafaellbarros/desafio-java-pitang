package br.com.rafaellbarros.backend.model.mapper;

import br.com.rafaellbarros.domain.core.model.mapper.BaseMapper;
import br.com.rafaellbarros.domain.model.dto.CarDTO;
import br.com.rafaellbarros.domain.model.dto.UserDTO;
import br.com.rafaellbarros.domain.model.entity.Car;
import br.com.rafaellbarros.domain.model.entity.User;
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
