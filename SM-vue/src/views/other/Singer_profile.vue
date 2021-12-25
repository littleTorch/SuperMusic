<template>
    <div class="singerMain1">
        <div class="singerCon2">
            <el-container>
                <el-aside>
                    <div class="block2">
                        <p id="b-top"></p>
                        <el-avatar shape="square" :size="100" :src="singer.icon"></el-avatar>
                        <p>{{ singer.singerName }}</p>
                        <p id="b-span">{{ singer.profile }}</p>

                    </div>
                </el-aside>
                <el-main>
                    <div id="songerSong">
                        <ul>
                            <li v-if="song" v-for="(item,index) in song" :key="index">
                                <a href="#" class="songerList">
                                    <div class="songerBox">
                                        <span>
                                            {{ index + 1 }}
                                        </span>
                                        <div class="songerImg">
                                            <img :src="item.pictureUrl" alt="">
                                        </div>
                                        <p>{{ item.name }}</p>
                                        <i :class="cur==index?'el-icon-video-pause':'el-icon-video-play'"
                                           @click="play(item,index)"></i>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </el-main>
            </el-container>
        </div>
    </div>


</template>

<script>
// import allsong from '../../components/Singer_profile/allsong.vue'

export default {
    // components: {allsong},
    data() {
        return {
            singer: "",
            song: [],
            cur: 1000000000,
        }
    },
    inject: ['addSong'],
    created() {
        this.profile()
        this.singerId()
    },
    methods: {
        profile() {
            // this.singer=this.$route.query.dataObj;
            this.singer = JSON.parse(sessionStorage.getItem("currentSingerId"))
            console.log(this.singer)
        },
        singerId() {
            this.axios.get("/song/singerByid/" + this.singer.id).then(res => {
                console.log(res);
                this.song = res.data.data
            })
        },
        play(item, index) {
            this.cur = index;
            console.log("点击播放歌曲")
            this.addSong(item);
        }
    }
}
</script>

<style>


.block2 {
    background: rgba(243, 243, 243, 0.5);
    height: 100%;
    text-align: center;
    overflow: hidden;
}

#b-top {
    height: 20px;
    width: auto;
}

.block2 p {
    margin-top: 20px;
}

#b-span {
    text-indent: 2em;
    text-align: left;
}

.singerMain1 {
    display: flex;
    justify-content: center;
}

.singerCon2 {
    width: 1200px;
    min-height: 900px;
}

#songerSong {
    background: rgba(243, 243, 243, 0.7);
    overflow: hidden;
    padding: 20px 10px;
}

.songerList {
    transition: all 0.5s;
    height: 120px;
    width: 100%;
    display: block;
    color: #000;


}

.songerList:hover {
    background: rgba(173, 173, 173, 0.9);
}

.songerImg {

    width: 100px;
    height: 100px;
    margin: 10px 20px;
    float: left;
}

.songerImg img {
    height: 100%;
    width: 100%;
}

.songerBox span, .songerBox p {
    float: left;
    line-height: 120px;
    font-size: 18px;
    margin-left: 15px;
}

.songerBox p {
    /*width: 580px;*/
    height: auto;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.songerBox i {
    float: right;
    margin-right: 50px;
    line-height: 120px;
    font-size: 50px;
}
</style>