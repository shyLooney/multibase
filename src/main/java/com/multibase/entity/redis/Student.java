package com.multibase.entity.redis;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@RedisHash("Student")
public class Student implements Serializable {
    private String id;
    private String fullName;
    private Date birthday;
    private Long groupId;
    private Long originalId;
}
