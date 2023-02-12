package io.github.hobbstech.drone;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DroneMapper {

    DroneDto toDto(Drone drone);

    Drone toEntity(DroneCommand command);

}
