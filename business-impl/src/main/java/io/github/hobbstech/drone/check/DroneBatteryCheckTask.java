package io.github.hobbstech.drone.check;

import io.github.hobbstech.drone.DroneQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DroneBatteryCheckTask {

    private final DroneQueryService droneQueryService;


    /**
     * checks drone battery level in 1min periodic intervals
     */
    @Scheduled(fixedRate = 60000)
    public void checkDroneBatteryLevels(){

    }

}
