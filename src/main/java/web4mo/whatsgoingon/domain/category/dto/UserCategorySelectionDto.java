package web4mo.whatsgoingon.domain.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web4mo.whatsgoingon.domain.category.entity.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor

public class UserCategorySelectionDto {
    private String userId;
    private Map<String,List<String>> categorykeywords;

    @Builder
    public UserCategorySelectionDto(String userId,Map<String,List<String>> categorykeywords ) {
        this.userId = userId;
        this.categorykeywords=categorykeywords;
    }
}

