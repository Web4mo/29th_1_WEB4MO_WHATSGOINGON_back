package web4mo.whatsgoingon.entity;

import lombok.Getter;

@Getter
public enum Category {
    //정치/경제/사회/ 생활 문화/ IT과학/ 세계/스포츠/연예
    POLITICS("정치"),
    ECONOMY("경제"),
    SOCIETY("사회"),
    LIFESTYLE_CULTURE("생활문화"),
    IT_SCIENCE("IT과학"),
    WORLD("세계"),
    SPORTS("스포츠"),
    ENTERTAINMENT("연예");

    final private String name;

    private Category(String name){
        this.name=name;
    }

}