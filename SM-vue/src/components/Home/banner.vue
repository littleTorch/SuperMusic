<template>
  <div class="hello">
    
     <el-carousel indicator-position="outside">
    <el-carousel-item v-for="(item,index) in mybanners" :key="index">
      <img :src="item" alt="">
    </el-carousel-item>
  </el-carousel>



  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
       mybanners:{
            type:Array,
            required:true
        }
  },
  
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.hello{
    width: 100%;
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  img{
    width: 100%;
    height: 100%;
  }

</style>
