<template>
    <div>
歌单
<button @clink="getData">dddddddd</button>
    </div>
</template>
<script>
export default{
   methods:{

getData(){
            this.$api.getSong()
               .then(res =>{
        
                     console.log(res.data)
      })
      .catch(error =>{
        console.log('请求失败');
      })
        }

   }
        
    
}
</script>