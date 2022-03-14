package com.spring.data.springdata.config;

import com.spring.data.springdata.dto.EmployeeDTO;
import com.spring.data.springdata.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MappingConfig {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(employeeDTOMapping);
        return mapper;
    }

    /*
     * Extracts the fields from Employee to build the full name in the dto
     * source = employee object, destination = string object
     */
    private static final Converter<Employee, String> fullNameConverter = mappingContext -> {
        Employee source = mappingContext.getSource();
        return source.getFirstName() + " " + source.getLastName();
    };

    /**
     * Instructions for mapping an employee into a dto object
     * source = employee object, destination = dto object
     */
    private static final PropertyMap<Employee, EmployeeDTO> employeeDTOMapping =
            new PropertyMap<Employee, EmployeeDTO>() {
                @Override
                protected void configure() {
                    using(fullNameConverter).map(source).setFullName(null);
                }
            };
}
