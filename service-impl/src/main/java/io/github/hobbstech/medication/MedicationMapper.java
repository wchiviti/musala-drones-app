package io.github.hobbstech.medication;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MedicationMapper {

    MedicationDto toDto(Medication drone);

    Medication toEntity(MedicationCommand command);

}
