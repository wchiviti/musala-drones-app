package io.github.hobbstech.batterylog;

import io.github.hobbstech.drone.BatteryLevelDto;

public interface CreateBatteryLogService {

    void logBattery(BatteryLevelDto batteryLevelDto);

}
