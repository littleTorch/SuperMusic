<template>
  <div class="home">
    <div class="homeMain">
      <HelloWorld v-if="banners" :mybanners="banners"/>
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
        }
  
  },methods:{
      getData(){
          this.axios.get("/home-slide/getImgsUrl").then((res) => {
              this.banners=res.data.data;
          });
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