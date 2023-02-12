package io.github.hobbstech.webapi;

import io.github.hobbstech.drone.loading.DroneLoadingHandler;
import io.github.hobbstech.drone.loading.LoadedDrone;
import io.github.hobbstech.medication.MedicationCommand;
import io.github.hobbstech.medication.MedicationDto;
import io.github.hobbstech.medication.MedicationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Tag(name = "Drone Payload")
public class MedicationsRestController {

    private final DroneLoadingHandler droneLoadingHandler;

    private final MedicationQueryService medicationQueryService;

    @PostMapping("/v1/drones/{droneUuid}/load")
    @Operation(summary = "loading a drone with medication items")
    public LoadedDrone load(@PathVariable String droneUuid, @RequestBody Collection<MedicationCommand> medicationCommands){
        return droneLoadingHandler.loadDrone(droneUuid, medicationCommands);
    }

    @GetMapping("/v1/drones/{droneUuid}/medications")
    @Operation(summary = "checking loaded medication items for a given drone")
    public Collection<MedicationDto> checkLoadedMedications(@PathVariable String droneUuid){
        return medicationQueryService.findByDrone(droneUuid);
    }

}
