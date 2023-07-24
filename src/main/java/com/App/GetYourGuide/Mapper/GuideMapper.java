package com.App.GetYourGuide.Mapper;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GuideMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isAvailable", target = "isAvailable")
    Guide mapToGuide(GuideDto guideDto);

    @InheritInverseConfiguration
    GuideDto mapToGuideDto(Guide guide);
}
