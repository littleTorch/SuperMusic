<template>
  <div class="home">
    <div class="homeMain">
      <HelloWorld :mybanners="banners"/>
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
  },data(){
    return{
      banners:[require('../assets/img/banner/banner1.jpg'),require('../assets/img/banner/banner2.jpg'),require('../assets/img/banner/banner3.jpg'),
        require('../assets/img/banner/banner4.jpg'),require('../assets/img/banner/banner5.jpg'),require('../assets/img/banner/banner6.jpg'),
      ],
   
        }
  
  },methods:{
    
      getData(){
      this.axios.getChengpin6()
      .then(res =>{
        console(res.data)
        //res.data.??.map(item => ({??"item.数据名称可以套娃继续点."}))
          // this.banners = res.data.banners.map(item =>({img: item.imageUrl}))
          //this.??.map(item => item. )
      //     console.log(this.banners.map(item => item.img))
      // console.log(res.data)
      })
      .catch(error =>{
        console.log('请求失败');
      })
    }
  
  },
 created(){
  //  this.getData();

 }
}
</script>

<style scoped>
.home{
 
  display: flex;
  justify-content: center;
}
.homeMain{
   width: 1200px;
  min-width: 900px;
}
</style>