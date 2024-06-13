package br.com.rafaellbarros.backend.endpoint.mapper;


import br.com.rafaellbarros.core.model.dto.UserDTO;
import br.com.rafaellbarros.core.model.entity.User;
import br.com.rafaellbarros.core.model.mapper.BaseMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CarMapper.class)
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Override
    UserDTO toDTO(User entity);

    @Override
    User toEntity(UserDTO dto);

    @Override
    List<UserDTO> toDTO(List<User> entities);

    @Override
    List<UserDTO> toDTO(Iterable<User> entities);

    @Override
    List<User> toEntity(List<UserDTO> dtos);

    @Override
    @InheritInverseConfiguration(name = "toDTO")
    void fromDTO(UserDTO dto, @MappingTarget User entity);

}

