package com.torch.supermusic.util.climbing.josnpojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Artist {
    private Long id;
    private String cover;
    private String name;
    private String briefDesc;
}
