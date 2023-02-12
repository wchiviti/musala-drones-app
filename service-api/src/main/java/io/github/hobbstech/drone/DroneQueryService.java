package io.github.hobbstech.drone;

import java.util.Collection;

public interface DroneQueryService {

    Collection<DroneDto> findLoadableDrones();

    DroneDto findByUuid(String uuid);

    BatteryLevelDto getBatteryDetailsByUuid(String uuid);

}
