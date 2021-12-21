<template>
    <div class="singerMain1">
        <div class="singerCon2">
            <el-container>
                <el-aside>
                    <div class="block2">
                        <p id="b-top"></p>
                        <el-avatar shape="square" :size="100" :src="singer.icon"></el-avatar>
                        <p>{{singer.singerName}}</p>
                        <p id="b-span">{{singer.profile}}</p>

                    </div>
                </el-aside>
                <el-main>
                    <allsong :songList="song"/>
                </el-main>
            </el-container>
        </div>
    </div>



</template>

<script>
import allsong from '../../components/Singer_profile/allsong.vue'
    export default {
  components: { allsong },
        data() {
            return{
                singer: "",
                song:[]
            }
        },
        created() {
            this.profile()
            this.singerId()
        },
        methods: {
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

<style >

    .el-aside {
      
    
    }

    .block2 {
       background: rgba(243, 243, 243, 0.5);
       height: 100%;
       text-align: center;
       overflow: hidden;
    }
    #b-top{
        height: 20px;
        width: auto;
    }
   .block2 p{
       margin-top: 20px;
    }
    #b-span{
    text-indent:2em;
    text-align: left;
    }
    .singerMain1{
        display: flex;
        justify-content: center;
    }
.singerCon2{
    width: 1200px;
    min-height: 900px;
}

</style>