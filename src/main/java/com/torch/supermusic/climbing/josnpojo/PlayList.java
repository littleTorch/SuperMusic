package com.torch.supermusic.climbing.josnpojo;

import com.alibaba.fastjson.annotation.JSONField;
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

    private String id;

}
