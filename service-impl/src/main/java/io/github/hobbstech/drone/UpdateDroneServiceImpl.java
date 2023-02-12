package io.github.hobbstech.drone;


import io.github.hobbstech.validations.Validations;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class UpdateDroneServiceImpl implements UpdateDroneService {

    private final DroneMapper droneMapper;

    private final DronesRepository dronesRepository;

    @Override
    public DroneDto update(UpdateDroneCommand updateDroneCommand) {

        Validations.validate(updateDroneCommand);

        val drone = dronesRepository.findByUuid(updateDroneCommand.uuid())
                .orElseThrow();

        drone.setState(updateDroneCommand.state());
        drone.setBatteryCapacity(updateDroneCommand.batteryCapacity());

        val updatedEntity = dronesRepository.save(drone);

        return droneMapper.toDto(updatedEntity);
    }
}
