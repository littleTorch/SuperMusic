<template>

  <el-container>
    <el-header>歌手</el-header>
    <el-main>
      <div class="con">
        <div class="block" v-for="item of singerData" @click="clickSinger(item)">
          <el-avatar shape="circle" :size="150"  :src="item.icon"></el-avatar>
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
</template>


<script>
  export default {
    data() {
      return {
        singerData: [],
        // singer: "",
        page: {
          currentPage: 1, // 当前页
          pageSize: 24, // 每页显示条目个数
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
          console.log(res);
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
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    padding: 0;
  }

  .el-header{
    background-color: #eff1f3;
    color: #333;
    text-align: left;
    line-height: 60px;
    font-weight: bolder;
    font-size: 50px;
  }

  .el-container {
    margin-bottom: 40px;
  }

  .el-avatar{
    float: left;
    margin: 15px;
  }
  .el-pagination{
    text-align: center;
    padding: 40px 0 0 0;
  }
</style>