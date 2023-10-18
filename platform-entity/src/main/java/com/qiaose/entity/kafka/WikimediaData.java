package com.qiaose.entity.kafka;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wikimedia_recentchange")
@Data
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;
}
