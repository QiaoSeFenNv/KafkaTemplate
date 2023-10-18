package com.qiaose.kakfa.respository;

import com.qiaose.entity.kafka.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
