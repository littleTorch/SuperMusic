<template>
  <div class="main11">
    <div class="main22">
      <el-row :gutter="0">
  <el-col :span="5"><div class="grid-content1 bg-purple1">
   

        <div class="list1">

    <div class="rankingList1">
            <h3>云音乐特色榜</h3>
            <ul>
              <li>
                <a href="#" class="listBox1" v-for="(item,index) in m" :key="index" @click="replaceDoc(item)">
                  <div class="listBox2" >
                <img 
                :src="item.icon" 
                alt="" 
                class="el-img1">
                <div class="listText1">
                  <p class="text2">{{item.playlistName}}</p>
                  <p class="text1">{{item.playlistComment}}</p>
                </div>
                        </div>
                </a>
              </li>
      
            </ul>
        </div>
      
        </div>
        </div>
    </el-col>
    <!-- 显示内容 -->
  <el-col :span="19">
    <div class="grid-content2 bg-purple2">

        <div id="Rmain">
          <div id="Rhead">
            <i class="el-icon-s-flag"></i>
            <h3>排行榜</h3>
          </div>

          <ul>
            <li>
                <a  href="#" v-for="(item,index) in song" :key="index" class="RmainA">
                  <span> {{index+1}}</span> 
                    <p>{{item.name}}</p>
                    <i class="el-icon-video-play"></i>
                </a>
              </li>
          </ul>

        </div>

     <!-- <router-view/> -->
    </div>
    </el-col>
 
</el-row>
    </div>
  </div>
</template>
        
<script>
import ListL from '../../components/RankingList/list1.vue'
export default {
  components:{
      ListL
  },
  data() {
    return {
      count: 0,

      // mylistL1:[]
      m:[],
      song:[]

    };
  },
  created(){
    this.getToplist();
    this.replaceDoc2();
  },
  methods: {
   getToplist(){
     this.axios.get("/playlist").then((response) => {
          let res = response.data;
          console.log(res.data);
          this.m=res.data;

        })
    .catch(error=>{
      console.log("请求错误");
    })
   
   },
     replaceDoc2(){
         
              this.axios.get("/playlist-song/playlistid/60131").then(res=>{
                    this.song=res.data.data;
                    console.log(this.song)
                })

        }, 
   replaceDoc(item){
           let listId=item.id;
            console.log(listId);
              this.axios.get("/playlist-song/playlistid/"+listId).then(res=>{
                    this.song=res.data.data;
                    console.log(this.song)
                })

        }

  
  }
};
</script>
<style scoped>

.main11{
  display: flex;
  justify-content: center;
}
.main22{
  width: 1000px;
  min-width: 900px;
}
 .grid-content1 {
    min-height: 36px;
    overflow: hidden;
    /* height: calc(100vh - 50px); */
  }
  .bg-purple1 {
    background: rgba(243, 243, 243, 0.2);
  }
   .grid-content2 {
    min-height: 36px;
  }
  .bg-purple2 {
  }
  ul,li{
    list-style-type: none;
  }
  /* 左边显示 */
  .rankingList1{
   
    width: 100%;
    margin: 20px 0;
  }
   .rankingList1 h3{
     margin: 15px 10px;
   }
   .rankingList1 a{
      transition: all 0.5s;
   }
   .rankingList1 a:hover{
       
     background: rgba(243, 243, 243, 0.5);
     	box-shadow: 1px 1px 3px #888888 inset;
   }
  .listBox1{
    display: block;
   width: 100%;
    height: 100px;
  
  }
  .listBox2{
padding: 15px 20px;
  }
  .el-img1{
    float: left;
    width: 70px;
    height: 70px;
  }
  .el-img1 img{
    width: 100%;
  }
  .listText1{
     float:left;
     width: 70px;
  
    margin-left: 10px;
    font-size: 10px;
  }
  .text2{
white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
 .text1 {
    margin-top: 15px;
    color: #ccc;
   overflow: hidden;
text-overflow: ellipsis; 
display: -webkit-box;
-webkit-line-clamp: 2;

-webkit-box-orient: vertical;

overflow:hidden;

text-overflow:ellipsis;

  }
  /* 右边显示 */
#Rhead{
  height: 80px;
  width: 700px;
  border-bottom: 3px solid red;
  margin-left: 15px;
}
#Rhead i,#Rhead h3{
  float: left;
  line-height: 80px;
}
#Rhead i{
  font-size: 35px;
}
.RmainA{
  height: 70px;
  display: block;
  line-height: 70px;
  color: #000;
  transition: all 0.5s;
}
.RmainA:hover{
 background: rgba(243, 243, 243, 0.5);
}
.RmainA span{
  display: block;
  margin-left: 15px;
  width: 35px;
  float: left;
}
.RmainA p{
  float: left;
}
.RmainA i{
  float: right;
  margin-right: 50px;
  font-size: 35px;
  line-height: 70px;
}
</style>