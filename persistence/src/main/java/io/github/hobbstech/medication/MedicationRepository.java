package io.github.hobbstech.medication;

import io.github.hobbstech.util.BaseRepository;

import java.util.Collection;
import java.util.Optional;

interface MedicationRepository extends BaseRepository<Medication> {

    Optional<Medication> findByUuid(String uuid);

    Collection<Medication> findByDroneUuid(String uuid);
}
