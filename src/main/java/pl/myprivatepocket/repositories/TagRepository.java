package pl.myprivatepocket.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.myprivatepocket.models.entity.TagEntity;

import java.util.Optional;

public interface TagRepository extends CrudRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);
}
