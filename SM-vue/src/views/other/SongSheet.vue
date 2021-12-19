<template>

    <div>
        <el-avatar v-for="item of songSheet" shape="square" :size="100" :src="item.icon" @click.native="profile(item)">
        </el-avatar>
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
        this.playlist();
    },
    methods:{
        playlist() {
            this.axios.get("/playlist").then(
                res =>{
                    console.log(res);
                    this.songSheet=res.data.data;
                })
        },
        profile(id) {
            console.log(id);
            this.$router.push({
                path: '/SongSheet_profile',
                query: {
                    dataObj: id
                }
            })
        }
   }
}
</script>