package ru.kpfu.repositories;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.StudentClass;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Long> {
    @Override
    Iterable<StudentClass> findAll();
}
