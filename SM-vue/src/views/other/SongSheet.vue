<template>

    <div>
        <el-avatar v-for="item of songSheet" shape="square" :size="100" :src="item.icon"></el-avatar>
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
        this.playlist()
    },
    methods:{
        playlist() {
            this.axios.get("/playlist").then(
                res =>{
                    console.log(res);
                    this.songSheet=res.data.data;
                    console.log(this.songSheet)
                })
        }
   }
}
</script>