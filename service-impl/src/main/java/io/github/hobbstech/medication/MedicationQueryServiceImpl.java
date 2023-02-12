package io.github.hobbstech.medication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class MedicationQueryServiceImpl implements MedicationQueryService {

    private final MedicationRepository medicationRepository;

    private final MedicationMapper medicationMapper;

    @Override
    public Collection<MedicationDto> findByDrone(String uuid) {
        return medicationRepository.findByDroneUuid(uuid)
                .stream()
                .map(medicationMapper::toDto)
                .toList();
    }
}
