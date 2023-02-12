package io.github.hobbstech.medication;

import java.util.Collection;

public interface MedicationQueryService {

    Collection<MedicationDto> findByDrone(String uuid);

}
