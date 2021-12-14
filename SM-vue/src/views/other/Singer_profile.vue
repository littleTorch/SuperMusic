<template>
    <div class="singerMain1">
        <div class="singerCon2">
            <el-container>
                <el-aside>
                    <div class="block2">
                        <el-avatar shape="square" :size="100" :src="singer.icon"></el-avatar>
                        <p>{{singer.singerName}}</p>
                        <p>{{singer.profile}}</p>

                    </div>
                </el-aside>
                <el-main>
                    {{song}}
                </el-main>
            </el-container>
        </div>
    </div>



</template>

<script>
    export default {
        data() {
            return{
                singer: "",
                song: ""
            }
        },
        created() {
            this.profile()
            this.singerId()
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
                // console.log(this.singer)
            },
            singerId(){
                this.axios.get("/song/singerByid/"+this.singer.id).then(res=>{
                    console.log(res);
                    this.song=res.data.data
                })
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
.singerCon2{

}

</style>