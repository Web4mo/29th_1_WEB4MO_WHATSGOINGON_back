package web4mo.whatsgoingon.domain.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web4mo.whatsgoingon.domain.scrap.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
}
