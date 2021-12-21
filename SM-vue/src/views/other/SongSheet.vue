<template>

    <div id="songSheet">
        <div id="songBox12">
            <div id="songH">
                
                <h3><i class="el-icon-headset"></i> 歌单</h3>
            </div>
             <!-- <el-avatar v-for="item of songSheet" shape="square" :size="100" :src="item.icon" @click.native="profile(item)">

        </el-avatar> -->

        <a href="#" v-for="(item,index) of songSheet" :key="index" @click="profile(item)" class="songA">
            <img :src="item.icon" alt="">
            <p>{{item.playlistName}}</p>
        </a>


        </div>
       
    </div>

</template>


<script>
export default{
    data() {
        return {
            songSheet: [],
        }
    },
    created() {
        this.playlist();
    },
    methods:{
        playlist() {
            this.axios.get("/playlist").then(
                res =>{
                    console.log(res);
                    this.songSheet=res.data.data;
                })
        },
        profile(id) {
            console.log(id);
            this.$router.push({
                path: '/SongSheet_profile',
                query: {
                    dataObj: id
                }
            })
        }
   }
}
</script>

<style>
#songSheet{
    display: flex;
    justify-content: center;
}
#songH{
    width: 900px;
    margin: 10px auto 20px;
    border-bottom: 3px solid red;
     
}
#songH h3{
   font-size: 20px;
    margin: 10px auto;
}
#songH i{
    font-size: 25px;
    font-weight: bold;
}
#songBox12{
    
    width: 1000px;
    min-width: 900px;
  
    
}
.songA{
    display: block;
    float: left;
    width: 120px;
    height: 150px;
    text-align: center;
    margin: 10px 2px;
    color: #000;
    transition: all 0.5s;
}
.songA:hover{
     background: rgba(243, 243, 243, 1);
}

.songA img{
    width: 100px;
}
.songA p{
    
}
</style>