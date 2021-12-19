<template>

    <el-container>
        <el-aside>
            <div class="block2">
                <el-avatar shape="square" :size="100" :src="songSheet.icon">
                </el-avatar>
            </div>
        </el-aside>
        <el-main>
            <div v-for="item in song">
                {{item.songUrl}}
            </div>
        </el-main>
    </el-container>

</template>

<script>
    export default {
        data() {
            return {
                songSheet: '',
                song: []
            }
        },
        created() {
            this.songsheetprofile();
            this.songsheetid()
        },
        methods: {
            songsheetprofile() {
                this.songSheet=this.$route.query.dataObj;
                //songSheet：歌单详情。包括id,封面，歌单简介，歌单创建者，歌单名字，歌单标签，歌单类型（0普通歌单，1排行榜，2我的歌单）
                // console.log(this.songSheet)
            },
            songsheetid() {
                this.axios.get("/playlist-song/playlistid/"+this.songSheet.id).then(
                    res =>{
                        // console.log(res)
                        this.song=res.data.data;
                        //sonng[]: 歌单中歌曲数据。包括歌曲简介，歌曲id，歌曲封面url，歌曲名字，歌曲地址
                        console.log(this.song)
                })
            }

        }
    }
</script>

<style scoped>

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

</style>