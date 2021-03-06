package com.torch.supermusic.util.climbing.josnpojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MusicList {
        private String name;
        private Long id;
//        歌手
        private List<ArBean> ar;
//        歌曲封面
        private AlBean al;

        @NoArgsConstructor
        @Data
        public static class AlBean {
            private Long id;
            private String name;
            private String picUrl;
        }

        @NoArgsConstructor
        @Data
        public static class ArBean {
            private Long id;
            private String name;
        }

}
