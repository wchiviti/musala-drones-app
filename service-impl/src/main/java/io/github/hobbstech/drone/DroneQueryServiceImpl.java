package io.github.hobbstech.drone;

import io.github.hobbstech.enums.DroneState;
import io.github.hobbstech.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
class DroneQueryServiceImpl implements DroneQueryService {

    private final DronesRepository dronesRepository;

    private final DroneMapper droneMapper;

    @Override
    public Collection<DroneDto> findLoadableDrones() {
        return dronesRepository.findByStateAndBatteryCapacityIsGreaterThan(DroneState.IDLE, 25)
                .parallelStream()
                .map(droneMapper::toDto)
                .toList();
    }

    @Override
    public DroneDto findByUuid(String uuid) {
        return dronesRepository.findByUuid(uuid)
                .map(droneMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException("Drone record was not found"));
    }

    @Override
    public BatteryLevelDto getBatteryDetailsByUuid(String uuid) {
        val drone = findByUuid(uuid);
        return new BatteryLevelDto(drone.uuid(), drone.batteryPercentage());
    }
}
