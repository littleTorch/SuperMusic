<template>
<div class="singerMain">
  <div class="elCon">
    <el-container >
      <el-header>
        <h5>歌手</h5>
        <div class="search">

          <el-input
                  placeholder="请输入歌手"
                  prefix-icon="el-icon-search"
                  v-model="input1">
          </el-input>
        </div>
      </el-header>
      <el-main class="singerBox">
        <div class="con">
          <div class="block" v-for="item of singerData" @click="clickSinger(item)">
            <el-avatar shape="circle" :size="120"  :src="item.icon"></el-avatar>
            <p>{{item.singerName}}</p>
            <!--          <a v-bind:href='axios.get("/singer")'>{{singerData}}</a>-->
          </div>
        </div>
      </el-main>
      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="page.currentPage"
              :page-size="page.pageSize"
              :total="page.totalCount"
              layout="total, prev, pager, next"
      ></el-pagination>
    </el-container>
  </div>

</div>

</template>


<script>
  export default {
    data() {
      return {
        singerData: [],
        // singer: "",
        page: {
          currentPage: 1, // 当前页
          pageSize: 32, // 每页显示条目个数
          totalCount: '' // 总条目数
        },
      }
    },
    created() {
      this.singer(1)
    },
    methods: {
      singer(currentPage) {
        this.singerData=[];
        this.axios.get("/singer/page",{
          params:{
            pageSize: this.page.pageSize,
            currentPage: currentPage,
          }
        }).then(res => {
          // console.log(res);
          this.singerData=res.data;
          if (res.data.code == 200) {
            this.singerData = res.data.data.records;
            this.page.totalCount = res.data.data.total;
            this.page.pageSize = res.data.data.size;
            this.page.currentPage = res.data.data.current;
          } else {
            this.singerData = '';
          }
        })
      },
      clickSinger(id){
        console.log(id)
        this.$router.push({
          path: '/Singer_profile',
          query: {
            dataObj: id
          }
        });
      },
      //pageSize改变调用
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      // 页数改变调用
      handleCurrentChange(val) {
        this.singer(val)
      },
      }

  }

</script>


<style>
  *{
    padding: 0;
    margin: 0;
  }
  .singerMain{

    display: flex;
    justify-content: center;
  }
  .elCon{
    width: 1200px;
    min-width:900px;
    height: 100%;
  }
  .singerBox{
    background-color: #E9EEF3;
  }
  .el-main {

    
    color: #333;
    padding: 0;
  }

  .el-header{
    background-color: #eff1f3;
    color: #333;
    text-align: left;
    line-height: 60px;
    font-weight: bolder;


  }
  .el-header h5{
    float: left;
    font-size: 50px;
  }
  .search{
    float: right;
    line-height: 60px;
    height: 60px;
    overflow: hidden;
  }

  .el-container {
    margin-bottom: 40px;
  }

  .el-avatar{
  }
  .el-pagination{
    text-align: center;
    padding: 40px 0 0 0;
  }
  .con{
    width: 100%;
    text-align: center;

  }
  /*每个歌手div*/
  .block{

    width:125px;
    height: 180px;
    float: left;
    overflow: hidden;
    text-align: center;
    margin: 8px;
  }
  .block img{
    margin: 0 auto;
  }
</style>