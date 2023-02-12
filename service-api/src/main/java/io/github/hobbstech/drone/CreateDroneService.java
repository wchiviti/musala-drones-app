package io.github.hobbstech.drone;

@FunctionalInterface
public interface CreateDroneService {

    DroneDto create(DroneCommand command);

}
