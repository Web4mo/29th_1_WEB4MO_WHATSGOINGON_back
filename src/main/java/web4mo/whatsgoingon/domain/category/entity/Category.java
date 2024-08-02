package web4mo.whatsgoingon.domain.category.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Category {
    //정치/경제/사회/ 생활 문화/ IT과학/ 세계/스포츠/연예
    POLITICS("정치", List.of("국회/정당", "북한", "국방.외교")),
    ECONOMY("경제",List.of("금융/증권","산업","부동산","글로벌 경제","생활 경제")),
    SOCIETY("사회",List.of("사건사고","교육","언론","환경","인권/복지","식품/의료")),
    LIFESTYLE_CULTURE("생활문화",List.of("건강정보","도로/교통","여행/래저","음식/맛집","패션/뷰티","공연/전시")),
    IT_SCIENCE("IT과학",List.of("모바일","인터넷/sns","통신","보안/해킹","컴퓨터","게임")),
    WORLD("세계",List.of("아시아/호주","미국/중남미","유럽","중동/아프리카")),
    SPORTS("스포츠",List.of("야구","해외야구","축구","해외축구","농구","배구")),
    ENTERTAINMENT("연예",List.of("방송/TV","드라마","뮤직","해외 연예"));
//    ECONOMY("경제"),
//    SOCIETY("사회"),
//    LIFESTYLE_CULTURE("생활문화"),
//    IT_SCIENCE("IT과학"),
//    WORLD("세계"),
//    SPORTS("스포츠"),
//    ENTERTAINMENT("연예");

//    final private String name;
    private final String name;
    private final List<String> topics;

//    private Category(Map<String, List<String>> name){
//        this.name= name.toString();
//    }

    Category(String name, List<String> topics) {
        this.name=name;
        this.topics=new ArrayList<>(topics);
    }

    public static Category valueofCategory(String name){
        for(Category label: values()){
            if(label.getName().equals(name)){
                return label;
            }
        }
        throw new IllegalStateException("없는 category: " + name);
    }
    public static Category getCategory(String value){
        for(Category label: values()){
            for(String key: label.getTopics()){
                if(key.equals(value)){
                    return label;
                }
            }
        }
        throw new IllegalStateException("속하는 category 없음: " + value);
    }
//    public String getName(){
//        return name;
//    }
//
//    public static Category valueOfCategory(String name){
//        for(Category label: values()){
//            if(label.getName().equals(name)){
//                return label;
//            }
//        }
//        throw new IllegalStateException("없는 category: " + category);
//    }



}
