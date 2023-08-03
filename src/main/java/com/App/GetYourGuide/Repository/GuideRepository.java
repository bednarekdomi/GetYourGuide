package com.App.GetYourGuide.Repository;

import com.App.GetYourGuide.domain.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {


}
