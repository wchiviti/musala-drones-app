package io.github.hobbstech.drone.loading;

import io.github.hobbstech.drone.DroneDto;
import io.github.hobbstech.medication.MedicationDto;

import java.util.Collection;

public record LoadedDrone(DroneDto drone, Collection<MedicationDto> medications) {
}
