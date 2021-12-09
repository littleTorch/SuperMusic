<template>
  <div class="main11">
    <div class="main22">
      <el-row :gutter="0">
  <el-col :span="5"><div class="grid-content1 bg-purple1">
   

   
    <ListL :mylistL="m"/>
    
    </div></el-col>
    <!-- 显示内容 -->
  <el-col :span="19"><div class="grid-content2 bg-purple2"></div></el-col>
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
      m:[]

    };
  },
  created(){
    this.getToplist();
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
    background: #d3dc77;
  }
  ul,li{
    list-style-type: none;
  }
  /* 左边显示 */
  /* .rankingList1{
    width: 100%;
    margin: 20px 0;
  }
   .rankingList1 h3{
     margin: 15px 10px;
   }
   .rankingList1 a:hover{
      background: rgba(50, 250, 255, 1);
   }0

  .listBox1{
    display: block;
   width: 100%;
    height: 100px;
   
  }
  .listBox2{
 
    padding: 15px 20px;
  }
  .el-img1{
    width: 70px;
    height: 70px;
     float: left;
  }
  .listText1{
    float: left;
    margin-left: 15px;
  }
  .text11{
    margin-top: 15px;
    color: #ccc;
  } */
</style>