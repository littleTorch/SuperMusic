package com.torch.supermusic.climbing.josnpojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MusicList {
        private String name;
        private int id;
        private List<ArBean> ar;
        private AlBean al;

        @NoArgsConstructor
        @Data
        public static class AlBean {
            private int id;
            private String name;
            private String picUrl;
        }

        @NoArgsConstructor
        @Data
        public static class ArBean {
            private int id;
            private String name;
        }

}
