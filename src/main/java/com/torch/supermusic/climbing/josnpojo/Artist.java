package com.torch.supermusic.climbing.josnpojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Artist {
    private String id;
    private String cover;
    private String name;
    private String briefDesc;
}
