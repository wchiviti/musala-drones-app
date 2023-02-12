package io.github.hobbstech.medication;

@FunctionalInterface
public interface CreateMedicationService {

    MedicationDto create(String droneUuid, MedicationCommand command);

}
