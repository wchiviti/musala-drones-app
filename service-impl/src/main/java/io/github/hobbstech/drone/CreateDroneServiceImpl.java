package io.github.hobbstech.drone;

import io.github.hobbstech.exceptions.InvalidRequestException;
import io.github.hobbstech.validations.Validations;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateDroneServiceImpl implements CreateDroneService {

    private final DronesRepository dronesRepository;

    private final DroneMapper droneMapper;

    @Override
    public DroneDto create(DroneCommand command) {

        Validations.validate(command);

        val serialNumberExists = dronesRepository.existsBySerialNumber(command.serialNumber());

        if(serialNumberExists){
            throw new InvalidRequestException("A drone with the same serial number already exists");
        }

        val drone = droneMapper.toEntity(command);

        val persistedDrone = dronesRepository.save(drone);

        return droneMapper.toDto(persistedDrone);
    }
}
