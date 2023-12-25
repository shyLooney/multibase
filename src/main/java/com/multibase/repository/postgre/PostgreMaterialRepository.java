package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Material;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreMaterialRepository extends CrudRepository<Material, Long> {
}
