package io.github.hobbstech.drone;

@FunctionalInterface
public interface RegisterDroneService {

    DroneDto register(DroneCommand command);

}
