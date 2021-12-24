<template>
    <div id="sSheet">
        <div  id="SheetBox">

        <div id="SheetBoxTop">
            <img :src="songSheet.icon" alt="" id="SheetbImg">
            <div id="SheetbR">
                  <h2>{{songSheet.playlistName}}</h2>
                  <h3>简介：</h3>
            <p>{{songSheet.playlistComment}}</p>
            </div>
          
        </div>
        <div id="SheetBoxB">
            <a href="#" v-for="(item,index) in song" :key="index" class="SheetBA">
                <img :src="item.pictureUrl" alt="" class="SheetImg">
                <p>P{{item.name}}</p>
                 <i class="el-icon-video-play"></i>
            </a>

        </div>
        </div>
      

    </div>
 
</template>

<script>
    export default {
        data() {
            return {
                songSheet: [],
                song: []
            }
        },
        created() {
            this.songsheetprofile();
            this.songsheetid()
        },
        methods: {
            songsheetprofile() {
                this.songSheet=this.$route.params.id;
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
/* 
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
    } */
    *{
        padding: 0;
        margin: 0;
    }
#sSheet{
    display: flex;
    justify-content: center;
}
#SheetBox{
    width: 900px;
    min-width: 800px;
    background: rgba(243, 243, 243, 0.8);
    min-height: calc(100vh - 50px);
   height: 100%;
   overflow: hidden;
}
#SheetBoxTop{
    margin: 25px 25px 20px;
    height: 250px;
    clear: both;
    
}
#SheetbImg{
    display: block;
    width: 150px;
    height: 150px;
    float: left;
}
#SheetbR{
    float: left;
    margin-left: 25px;
}
#SheetbR h3,#SheetbR p{
    margin-top: 10px;
}
/* 歌曲 */
#SheetBoxB{
    margin: 10px 25px;
    overflow: hidden;
   
}
.SheetBA{
    display: block;
    width: 100%;
    height: 180px;
    float: left;
    line-height: 180px;
    transition: all 0.5s;

}
.SheetBA:hover{
    background: rgba(182, 182, 182,0.8);
}
.SheetImg{
    width: 150px;
    height: 150px;
    border-radius: 150px;
    display: block;
    margin-top: 15px;
}
.SheetBA img,.SheetBA p{
    float: left;
}
.SheetBA p{
    margin-left: 15px;
    font-size: 18px;
     width:580px;
    height: auto;
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}
.SheetBA i{
    float: right;
    font-size: 75px;
    line-height: 180px;
    margin-right: 25px;
    color: #000;
}
</style>