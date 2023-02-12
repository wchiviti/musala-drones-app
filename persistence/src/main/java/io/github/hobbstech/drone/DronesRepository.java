package io.github.hobbstech.drone;

import io.github.hobbstech.util.BaseRepository;

import java.util.Optional;

interface DronesRepository extends BaseRepository<Drone> {

    Optional<Drone> findByUuid(String uuid);

}
