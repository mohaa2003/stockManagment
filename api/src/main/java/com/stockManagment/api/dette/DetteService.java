package com.stockManagment.api.dette;
import com.stockManagment.api.exceptions.EntityNotFoundException;
import com.stockManagment.api.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetteService {
    private final DetteRepo detteRepo;

    public Integer save(DetteDto dette) {

        return DetteDto.fromEntity(detteRepo.save(DetteDto.toEntity(dette))).getId();

    }

    public DetteDto findById(Integer id) {

        if(id == null){
            log.error("Dette id est null");
            return null;
        }
        Dette dette = detteRepo.findById(id)

                .orElseThrow(()->new EntityNotFoundException(ErrorCodes.DETTE_NOT_FOUND.getDescription()+" with id = "+id,ErrorCodes.DETTE_NOT_FOUND));

        return DetteDto.fromEntity(dette);

    }

    public List<DetteDto> findAll(){

        return detteRepo.findAll().stream().map(DetteDto::fromEntity).collect(Collectors.toList());

    }

    public void delete(Integer id) {
        if (id == null){
            log.error("Dette non valid !");
        }
        detteRepo.deleteById(id);
    }
}

