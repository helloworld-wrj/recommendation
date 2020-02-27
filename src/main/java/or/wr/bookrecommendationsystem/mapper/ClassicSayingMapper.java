package or.wr.bookrecommendationsystem.mapper;

import or.wr.bookrecommendationsystem.entity.ClassicSaying;

public interface ClassicSayingMapper {
    int setClassicSayingMapper(String content, String provenance);

    ClassicSaying findClassicSaying();
}
