<template>
    <div class="list1">

 <div class="rankingList1">
        <h3>云音乐特色榜</h3>
        <ul>
          <li><a href="#" class="listBox1" v-for="(item,index) in mylistL" :key="index">
               <div class="listBox2">
                 
      <img 
      :src="item.icon" 
      alt="" 
       class="el-img1">
      <div class="listText1">
        <p class="text2">{{item.playlistName}}</p>
        <p class="text1">{{item.playlistComment}}</p>
      </div>
               </div>
            </a></li>
  
        </ul>
    </div>
  
    </div>
</template>

<script>
export default {
    name:"l",
     props: {
       mylistL:{
            type:Array,
            required:true
        }
  },
    data(){
        return{
    
        }
    }
    ,
    methods:{
    
    },  created(){
  },
}
</script>


<style scoped>
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
</style>

