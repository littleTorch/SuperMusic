<template>
    <div id="songSheet">
        <div id="songBox12">
            <div id="songH">
                <h3><i class="el-icon-headset iconSOng"></i> 歌单</h3>
                <!--搜素框-->
                <div class="searchSong">
                    <el-form :model="select">
                        <el-row>
                            <el-col :span="100">
                                <el-form-item  >
                                    <el-input v-model="select.playlistName" placeholder="请输入内容"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-button
                                    style="margin-left: 10px;"
                                    class="btn-left"
                                    type="primary"
                                    icon="el-icon-search"
                                    @click="sel(1)"
                            >查询
                            </el-button>
                        </el-row>
                    </el-form>
                </div>
            </div>

            <a href="#" v-for="(item,index) of songSheet" :key="index" @click="profile(item)" class="songA">
                <img :src="item.icon" alt="">
                <p>{{item.playlistName}}</p>
            </a>

        </div>
        <!--分页箭头-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.currentPage"
                :page-size="page.pageSize"
                layout="prev, pager, next"
                style="display:none"
        ></el-pagination>

    </div>
</template>


<script>
export default{
    data() {
        return {
            songSheet: [],

            page: {
                currentPage: 1, // 当前页
                pageSize: 44, // 每页显示条目个数
                totalCount: '' // 总条目数
            },

            select: {
                playlistName: ''
            },
        }
    },
    created() {
        // this.playlist();
        this.sel(1)
    },
    methods: {
        // playlist(){
        //     this.songSheet=[];
        //     this.axios.get("/playlist").then(res => {
        //         this.songSheet=res.data.data;
        //     })
        // },
            sel(currentPage) {
            this.songSheet=[];
            this.axios.get("/playlist/page",{
                params:{
                    arg: this.select.playlistName,
                    arg2: "0",
                    pageSize: this.page.pageSize,
                    currentPage: currentPage,
                }
            }).then(res => {
                if (res.data.code == 200) {
                    this.songSheet=res.data;
                    this.songSheet = res.data.data.records;
                    this.page.pageSize = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                } else {
                    this.songSheet = '';
                }
            })
        },
        profile(id) {
            console.log(id);
            sessionStorage.setItem("songlist", JSON.stringify(id));
            this.$router.push({name : 'SongSheet_profile'})
        }, //pageSize改变调用
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        // 页数改变调用
        handleCurrentChange(val) {
            this.sel(val)
        },
    }
}
</script>

<style>
#songSheet{
    display: flex;
    justify-content: center;
}
#songH{
    height: 60px;
    width: 900px;
    margin: 10px auto 20px;
    border-bottom: 3px solid red;
     
}
#songH h3{
   font-size: 25px;
   float: left;
    margin: 10px auto;
}
.iconSOng{
    line-height: 40px;
    float: left;
    font-size: 32px;
    font-weight: bold;
}
.searchSong{
    float: right;
}
#songBox12{
    
    width: 1000px;
    min-width: 900px;
  
    
}
.songA{
    display: block;
    float: left;
    width: 120px;
    height: 150px;
    text-align: center;
    margin: 10px 2px;
    color: #000;
    transition: all 0.5s;
}
.songA:hover{
     background: rgba(243, 243, 243, 1);
}

.songA img{
    width: 100px;
}
.songA p{
    
}
</style>