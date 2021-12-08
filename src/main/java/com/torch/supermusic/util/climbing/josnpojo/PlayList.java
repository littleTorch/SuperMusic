package com.torch.supermusic.util.climbing.josnpojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayList implements Serializable {

    private String coverImgUrl;

    private String description;

    private String name;

    private Long id;

}
