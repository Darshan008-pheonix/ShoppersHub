package com.sph.owner.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sph.owner.entity.OwnerStatus;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(ctx -> {
            OwnerStatus status = ctx.getSource();
            if (status == null) return null;

            return switch (status) {
                case ACTIVE -> 1;
                case INACTIVE -> 0;
                case BLOCKED -> 2;
                case DELETED -> 3;
            };
        }, OwnerStatus.class, Integer.class);

        return mapper;
    }
}
