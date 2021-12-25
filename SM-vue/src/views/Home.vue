<template>
    <div class="home">
        <div class="homeMain">
            <HelloWorld v-if="banners" :mybanners="banners"/>
        </div>

        <div id="homeSinger">
            <div class="homeHeader">
                <i class="el-icon-s-custom"></i>
                <h3>热门歌手</h3>
            </div>
            <div id="singerMain">
                <a href="#" v-for="(item,index) of  singers" :key="index" @click="clickSinger(item)" class="homeSingerBox">
                <img :src="item.icon" alt="">
                <p>{{item.singerName}}</p>
            </a>
            </div> 
        </div>

          <div id="homeSong">
            <div class="homeHeader">
                <i class="el-icon-headset"></i>
                <h3>热门歌单</h3>
            </div>
            <div id="songMain">
                   <a href="#" v-for="(item,index) of songSheet" :key="index" @click="profile(item)" class="homeSongBox">
                <img :src="item.icon" alt="">
                <p>{{item.playlistName}}</p>
            </a>
            </div>
        </div>
    </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/Home/banner.vue'

export default {
    name: 'Home',
    components: {
        HelloWorld
    }, data() {
        return {
            banners: [],
            singers:[],
            songSheet:[],
        }

    }, methods: {
        getData() {
            this.axios.get("/home-slide/getImgsUrl").then((res) => {
                this.banners = res.data.data;
            });
            this.axios.get("/singer/page",{
                params: {
                    pageSize: 10,
                    currentPage: Math.ceil(Math.random()*10),
                }
            }).then((res) => {
                this.singers = res.data.data.records;
                console.log(this.singers)
            });
            this.axios.get("/playlist/random/8").then((res) => {
                this.songSheet = res.data.data;
                console.log(this.songSheet)
            });
        },clickSinger(id){
        console.log(id)
        this.$router.push({
          path: '/Singer_profile',
          query: {
            dataObj: id
          }
        });
      },profile(id) {
            console.log(id);
            this.$router.push({name: 'SongSheet_profile',params: {id: id}})
        }

    },
    created() {
        this.getData();

    }
}
</script>

<style scoped>
.home {
/* 
    display: flex;
    justify-content: center;
    width: 1200px; */
   
}

.homeMain {
    margin: 0 auto;
    width: 1200px;
    min-width: 900px;
    height: auto;
}
.homeHeader{
    width: 900px;
    border-bottom: 3px solid red;
    line-height: 60px;
    height: 60px;
}
.homeHeader i,.homeHeader h3{
    float: left;
    line-height: 60px;
}
.homeHeader i{
    font-size: 25px;
}
#homeSinger,#homeSong{
    width: 1000px;
    margin: 20px auto;
    height: 500px;
     background: rgba(245, 245, 245, 0.7);
}
#singerMain{
    width: 1000px;
    overflow: hidden;
}
.homeSingerBox{
    width: 150px;
    height: 200px;
    padding-top: 10px;
    margin-right: 50px;
    float: left;
    text-align: center;
    font-size: 20px;
    transition: all 0.5s;
   
}
.homeSingerBox:hover,.homeSongBox:hover{
    color: red;
}
.homeSingerBox img{
    width: 150px;
    height: 150px;
    border-radius: 150px;
}
.homeSongBox{
    display: block;
    width: 200px;
    height: 260px;
    padding-top: 10px;
    margin-right: 50px;
    float: left;
    text-align: center;
    font-size: 18px;
    transition: all 0.5s;
}
.homeSongBox img{
    width: 200px;
    height: 200px;
    border-radius: 10px;
    overflow: hidden;
}


</style>