<template>

    <el-container>
        <el-aside>
            <div class="block">
                <el-avatar shape="square" :size="100" :src="singer.icon"></el-avatar>
                <span>{{singer.profile}}</span>
            </div>
        </el-aside>
        <el-main>
            {{}}
        </el-main>
    </el-container>

</template>

<script>
    export default {
        data() {
            return{
                singer: ""
            }
        },
        created() {
            this.profile()
        },
        methods: {
            // profile(){
            //     this.$route.params.dataobj;
            //     this.axios.get("/singer/byid/"+this.$route.params.dataobj).then(res=>{
            //         console.log(res);
            //         this.singerData=res.data.data;
            //     })
            // }
            profile(){
                this.singer=this.$route.query.dataObj;
                console.log(this.singer)
            }
        }
    }
</script>

<style scoped>

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .block {
        height: 300px;
        line-height: 300px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }


</style>