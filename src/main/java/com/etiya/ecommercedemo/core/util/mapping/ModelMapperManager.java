package com.etiya.ecommercedemo.core.util.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{

    private ModelMapper modelMapper;

    @Override
    public ModelMapper getMapper() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }


    // Request ve Response için configurationlar değişebilir.
    /* TODO: iki fonksiyon oluştur: forRequest,forResponse request ve response için konfigürasyonları yapıp geriye modelMapper'i dönsün. */

}
