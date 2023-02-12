package io.github.hobbstech.webapi;

import io.github.hobbstech.drone.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Tag(name = "Drones")
public class DronesRestController {

    private final RegisterDroneService registerDroneService;

    private final DroneQueryService droneQueryService;

    @PostMapping("/v1/drones")
    @Operation(summary = "Register drone")
    public DroneDto register(@RequestBody DroneCommand command){
        return registerDroneService.register(command);
    }

    @GetMapping("/v1/drones/available")
    @Operation(summary = "checking available drones for loading")
    public Collection<DroneDto> findAvailableDrones(){
        return droneQueryService.findLoadableDrones();
    }

    @GetMapping("/v1/drones/{droneUuid}")
    @Operation(summary = "check drone battery level for a given drone")
    public BatteryLevelDto checkBattery(@PathVariable String droneUuid){
        return droneQueryService.getBatteryDetailsByUuid(droneUuid);
    }

}
