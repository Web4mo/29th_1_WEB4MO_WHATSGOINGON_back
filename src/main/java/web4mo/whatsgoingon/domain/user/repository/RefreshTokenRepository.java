package web4mo.whatsgoingon.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web4mo.whatsgoingon.domain.user.dto.TokenDto;
import web4mo.whatsgoingon.domain.user.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    //boolean existsById(String userId);
    void deleteByUserId(String userId);
    RefreshToken findRefreshTokenByUserId(String userId);
}
