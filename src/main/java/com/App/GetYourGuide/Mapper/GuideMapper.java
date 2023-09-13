package com.App.GetYourGuide.Mapper;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(uses = {OrderMapper.class}, componentModel = "spring")
public interface GuideMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "tours", target = "tours")
    @Mapping(source = "daysOffSinceLastTrip", target = "daysOffSinceLastTrip")
    Guide mapToGuide(GuideDto guideDto);

    @InheritInverseConfiguration
    GuideDto mapToGuideDto(Guide guide);

    @IterableMapping(elementTargetType = GuideDto.class)
    List<GuideDto> mapToGuideDtoList(List<Guide> guidesList);

    default Optional<GuideDto> mapToGuideDto(Optional<Guide> guide){
        return guide.map(this::mapToGuideDto);
    }
}
