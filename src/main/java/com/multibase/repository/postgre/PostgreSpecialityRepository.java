package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Chair;
import com.multibase.entity.postgre.Speciality;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreSpecialityRepository  extends CrudRepository<Speciality, Long> {

}
