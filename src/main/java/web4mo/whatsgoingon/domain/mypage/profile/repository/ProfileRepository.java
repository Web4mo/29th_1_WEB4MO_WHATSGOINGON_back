package web4mo.whatsgoingon.domain.mypage.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web4mo.whatsgoingon.domain.mypage.profile.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

}
