package io.github.hobbstech.drone.loading;

import io.github.hobbstech.medication.MedicationCommand;

import java.util.Collection;

public interface DroneLoadingHandler {

    LoadedDrone loadDrone(String uuid, Collection<MedicationCommand> medications);

}
