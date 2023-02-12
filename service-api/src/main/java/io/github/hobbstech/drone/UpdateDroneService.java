package io.github.hobbstech.drone;

@FunctionalInterface
public interface UpdateDroneService {

    DroneDto update(UpdateDroneCommand updateDroneCommand);

}
