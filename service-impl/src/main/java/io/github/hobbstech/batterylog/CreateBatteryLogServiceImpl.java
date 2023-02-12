package io.github.hobbstech.batterylog;

import io.github.hobbstech.drone.BatteryLevelDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateBatteryLogServiceImpl implements CreateBatteryLogService {

    private final BatteryLogRepository batteryLogRepository;

    @Override
    public void logBattery(BatteryLevelDto batteryLevelDto) {

        val log = new BatteryLog();
        log.setBatteryCapacity(batteryLevelDto.batteryCapacity());
        log.setDroneUuid(batteryLevelDto.uuid());

        batteryLogRepository.save(log);

    }
}
