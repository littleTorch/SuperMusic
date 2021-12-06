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
      banners:[],
      //要放在父组件才会生效
      // swiperOptions: {
      //     pagination: {
      //       el: '.swiper-pagination'
      //     }         ,
      //         loop: true,
      //   autoplay:true,
      // autoplay: {
      //     disableOnInteraction: false
      // }
        }
  
  },methods:{
    
      getData(){
      this.$api.getChengpin6()
      .then(res =>{
        //res.data.??.map(item => ({??"item.数据名称可以套娃继续点."}))
          this.banners = res.data.banners.map(item =>({img: item.imageUrl}))
          //this.??.map(item => item. )
          console.log(this.banners.map(item => item.img))
      console.log(res.data)
      })
      .catch(error =>{
        console.log('请求失败');
      })
    }
  
  },
 created(){
   this.getData();

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