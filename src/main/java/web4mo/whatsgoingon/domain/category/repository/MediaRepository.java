package web4mo.whatsgoingon.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web4mo.whatsgoingon.domain.category.entity.Media;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {
    List<Media> findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}
