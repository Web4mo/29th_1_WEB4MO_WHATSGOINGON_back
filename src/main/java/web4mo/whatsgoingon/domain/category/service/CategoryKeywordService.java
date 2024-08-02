package web4mo.whatsgoingon.domain.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web4mo.whatsgoingon.domain.category.dto.MediaSelectionDto;
import web4mo.whatsgoingon.domain.category.dto.UserCategorySelectionDto;
import web4mo.whatsgoingon.domain.category.entity.*;
import web4mo.whatsgoingon.domain.category.repository.MediaRepository;
import web4mo.whatsgoingon.domain.category.repository.MediaUserRepository;
import web4mo.whatsgoingon.domain.category.repository.UserCategoryKeywordRepository;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.constant.ConstantDescs.NULL;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryKeywordService {
    private final UserCategoryKeywordRepository userCategoryKeywordRepository;
    private final UserRepository userRepository;
    private final MediaUserRepository mediaUserRepository;
    private final MediaRepository mediaRepository;

    //카테고리 키워드 저장 및 수정
    @Transactional
    public List<UserCategoryKeyword> saveCategorykeyword(UserCategorySelectionDto userCategorySelectionDto){
        log.info("유저 선택: "+userCategorySelectionDto.toString());
        String userId = userCategorySelectionDto.getUserId();

        Optional<Member> optionalMember = userRepository.findByLoginId(userId);
        if(optionalMember.isEmpty()){
            throw new IllegalStateException("회원이 아닙니다.");
        }
        Member user= optionalMember.get();
        if(userCategorySelectionDto.getCategorykeywords().isEmpty()){
            throw new IllegalStateException("선택한 카테고리 키워드가 없습니다.");
        }

        if(userCategoryKeywordRepository.existsByMember(user)){
            userCategoryKeywordRepository.deleteByMember(user);
        }
        for(Map.Entry<String,List<String>> entry: userCategorySelectionDto.getCategorykeywords().entrySet() ){
            Category category=Category.valueOfCategory(entry.getKey());
            List<String> userKeyword=entry.getValue();
            for(String key: userKeyword){
                UserCategoryKeyword userCategoryKeyword=UserCategoryKeyword.builder()
                        .member(user).keyword(key).category(category).build();
                userCategoryKeywordRepository.save(userCategoryKeyword);
            }
        }
        log.info("키워드만"+userKeywords(user));
        log.info("카테고리만"+ userCategories(user));
        return userCategoryKeywordList(user);
    }

    //키워드 - 카테고리 목록 가져오기
    public List<UserCategoryKeyword> userCategoryKeywordList(Member member){
        return userCategoryKeywordRepository.findByMember(member);
    }

    //유저가 선택한 키워드만 가져오기
    public List <String> userKeywords(Member member){
        List<String> keywords = new ArrayList<>();
        for(UserCategoryKeyword keyword: userCategoryKeywordRepository.findByMember(member)) {
            keywords.add(keyword.getKeyword());
        }
        return keywords;
    }

    //유저가 선택한 카테고리만 가져오기
    public List<Category> userCategories(Member member){
        List<Category> categories=new ArrayList<>();
        for(UserCategoryKeyword keyword: userCategoryKeywordRepository.findByMember(member)) {
            categories.add(keyword.getCategory());
        }
        return categories;
    }

    //언론사 저장하기
    @Transactional
    public List<Media> saveMedia(MediaSelectionDto mediaSelectionDto){
        if(mediaSelectionDto.getMedias().isEmpty()){
            return null;
        }
        String userId = mediaSelectionDto.getUserId();

        Optional<Member> optionalMember = userRepository.findByLoginId(userId);
        if(optionalMember.isEmpty()){
            throw new IllegalStateException("회원이 아닙니다.");
        }
        Member user= optionalMember.get();

        if(mediaUserRepository.existsByMember(user)){
            mediaUserRepository.deleteByMember(user);
        }
        if(mediaSelectionDto.getMedias()==null){
            MediaUser noMedia=MediaUser.builder().media(null).member(user).build();
            mediaUserRepository.save(noMedia);
            return null;
        }
        for(Map.Entry<String,String> entry: mediaSelectionDto.getMedias().entrySet() ){
            Media media;
            if(!mediaRepository.existsByName(entry.getKey())){
                media=Media.builder().name(entry.getKey()).link(entry.getValue()).build();
                mediaRepository.save(media);
            }
            else{
                continue;
            }
            MediaUser mediaUser=MediaUser.builder().media(media).member(user).build();
            mediaUserRepository.save(mediaUser);
        }
        log.info(""+getMediaLinks(user));
        return mediaRepository.findByName(user.getLoginId());
    }

    //미디어 링크 가져오기
    public List<String> getMediaLinks(Member member){
        if(!mediaUserRepository.existsByMember(member)){
            throw new IllegalStateException("선택한 언론사가 없습니다.");
        }
        List<MediaUser> usermediaList=mediaUserRepository.findByMember(member);
        List<String> mediaLinks = new ArrayList<>();
        for(MediaUser mediaUser: usermediaList){
            mediaLinks.add(mediaUser.getMedia().getLink());
        }
        return mediaLinks;

    }


}
