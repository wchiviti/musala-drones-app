package io.github.hobbstech.medication;

import io.github.hobbstech.drone.DroneQueryService;
import io.github.hobbstech.validations.Validations;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
class CreateMedicationServiceImpl implements CreateMedicationService {

    private final MedicationRepository medicationRepository;

    private final MedicationMapper medicationMapper;

    private final DroneQueryService droneQueryService;

    @Override
    public MedicationDto create(String droneUuid, MedicationCommand command) {

        Validations.validate(command);

        val drone = droneQueryService.findByUuid(droneUuid);

        val medication = medicationMapper.toEntity(command);

        medication.setDroneUuid(drone.uuid());
        medication.setDroneId(drone.id());

        val persistedMedication = medicationRepository.save(medication);

        return medicationMapper.toDto(persistedMedication);
    }
}
