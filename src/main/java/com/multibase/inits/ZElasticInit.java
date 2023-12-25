package com.multibase.inits;

import com.multibase.entity.elastic.Material;
import com.multibase.repository.elastic.ElasticMaterialRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;

@Configuration
@DependsOn("generator")
@Log
public class ZElasticInit {
    private ArrayList<com.multibase.entity.postgre.Material> materials;

    public ZElasticInit(ArrayList<com.multibase.entity.postgre.Material> materials) {
        this.materials = materials;
    }

    @Bean
    public CommandLineRunner insertElastic(ElasticMaterialRepository repository) {
        log.info("Elastic insert start");

        ArrayList<Material> materialList = new ArrayList<>();
        for (var item : materials) {
            Material material = new Material(item.getId(), item.getContent());
            materialList.add(material);
        }

        repository.saveAll(materialList);


        log.info("Elastic insert end");
        return args -> {

        };
    }
}
