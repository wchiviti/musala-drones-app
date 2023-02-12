package io.github.hobbstech.drone.loading;

import io.github.hobbstech.drone.DroneDto;
import io.github.hobbstech.drone.DroneQueryService;
import io.github.hobbstech.drone.UpdateDroneCommand;
import io.github.hobbstech.drone.UpdateDroneService;
import io.github.hobbstech.enums.DroneState;
import io.github.hobbstech.exceptions.InvalidRequestException;
import io.github.hobbstech.medication.CreateMedicationService;
import io.github.hobbstech.medication.MedicationCommand;
import io.github.hobbstech.medication.MedicationDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;


@Service
@RequiredArgsConstructor
class DroneLoadingHandlerImpl implements DroneLoadingHandler {

    private final CreateMedicationService createMedicationService;

    private final UpdateDroneService updateDroneService;

    private final DroneQueryService droneQueryService;

    @Override
    public LoadedDrone loadDrone(String uuid, Collection<MedicationCommand> medications) {

        if (requireNonNull(medications).isEmpty()) {
            throw new InvalidRequestException("Medications to be loaded should be at least 1");
        }

        val drone = droneQueryService.findByUuid(uuid);

        if (!isDroneLoadable(drone)) {
            throw new InvalidRequestException("This drone is not is a loadable state [should be in IDLE and battery above 25%]");
        }

        val totalMedicationWeight = medications.stream().mapToDouble(MedicationCommand::weight)
                .sum();

        if (totalMedicationWeight > drone.weightLimit()) {
            throw new InvalidRequestException("The drone can only carry a maximum of %s grams".formatted(drone.weightLimit()));
        }

        updateDroneService.update(new UpdateDroneCommand(uuid, DroneState.LOADING, drone.batteryPercentage()));

        val medicationDtos = new ArrayList<MedicationDto>();

        medications.forEach(command -> {
            medicationDtos.add(createMedicationService.create(uuid, command));
        });

        val loadedDrone = updateDroneService.update(new UpdateDroneCommand(uuid, DroneState.LOADED, drone.batteryPercentage()));

        return new LoadedDrone(loadedDrone, medicationDtos);
    }

    private boolean isDroneLoadable(DroneDto drone) {
        return drone.state().equals(DroneState.IDLE) && drone.batteryPercentage() > 25;
    }
}
