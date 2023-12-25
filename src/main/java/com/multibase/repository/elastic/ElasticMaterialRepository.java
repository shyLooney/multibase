package com.multibase.repository.elastic;

import com.multibase.entity.elastic.Material;
import org.springframework.data.repository.CrudRepository;

public interface ElasticMaterialRepository extends CrudRepository<Material, Long> {
    Iterable<Material> findAllByContentContains(String str);
}
