package io.github.hobbstech.drone;

import io.github.hobbstech.enums.DroneState;
import io.github.hobbstech.util.BaseRepository;

import java.util.Collection;
import java.util.Optional;

interface DronesRepository extends BaseRepository<Drone> {

    Optional<Drone> findByUuid(String uuid);

    boolean existsBySerialNumber(String serialNumber);

    Collection<Drone> findByStateAndBatteryCapacityIsGreaterThan(DroneState droneState, double minCapacity);

}
