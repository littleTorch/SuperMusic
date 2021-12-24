<template>
    <div id="songerSong">
        <ul>
            <li >
                <a href="#" class="songerList"
                v-for="(item,index) in songList" :key="index"
                >
                    <div class="songerBox">
                        <span>
                            {{index+1}}
                        </span>
                        <div class="songerImg">
                                <img :src="item.pictureUrl" alt="">
                        </div>
                    
                        
                        <p>{{item.name}}</p>
                        <i class="el-icon-video-play" @click="play(item)"></i>
                    </div>

                </a>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    props:{
        songList:{
            type:Array,
            required:true
        }
    },
    inject: ['addSong'],
    methods:{
        play(item){
            console.log("点击播放歌曲")
            this.addSong(item);
        }
    }
}
</script>

<style>
#songerSong{
    background: rgba(243, 243, 243, 0.7);
    overflow: hidden;
    padding: 20px 10px;
}

.songerList{
    transition: all 0.5s;
    height: 120px;
    width: 100%;
    display: block;
    color: #000;
  

}
.songerList:hover{
     background: rgba(173, 173, 173,0.9);
}
.songerImg{

  width: 100px;
  height: 100px;
  margin: 10px 20px;
  float: left;
}
.songerImg img{
    height: 100%;
    width: 100%;
}
.songerBox span,.songerBox p{
      float: left;
      line-height: 120px;
      font-size: 18px;
      margin-left: 15px;
}
.songerBox p{
    width:580px;
    height: auto;
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}
.songerBox i{
    float:right;
    margin-right: 50px;
    line-height: 120px;
    font-size: 50px;
}

</style>