package com.poc.processorchestrator.common.experiments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class DataLoaderService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    public DataLoaderService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void loadDataFromYaml() {
        Yaml yaml = new Yaml();
        try {
            Resource resource = new ClassPathResource("data.yml");
            InputStream inputStream  = resource.getInputStream();
            //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.yaml");
            List<Department> departmentList = yaml.load(inputStream);
            departmentRepository.saveAll(departmentList);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
