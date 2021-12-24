<template>
<div class="singerMain">
  <div class="elCon">
    <el-container >

      <el-header>
        <h5>歌手</h5>

        <!--搜素框-->
        <div class="search">
          <el-form :model="select" id="searchForm">
            <el-row>
              <el-col :span="100">
                <el-form-item  id="singerSeacher" >
                  <el-input v-model="select.singerName" placeholder="请输入内容"></el-input>
                </el-form-item>
              </el-col>
              <el-button
                      style="margin-left: 10px;"
                      class="btn-left"
                      type="primary"
                      icon="el-icon-search"
                      @click="singer(1)"
              >查询
              </el-button>
            </el-row>
          </el-form>
        </div>

      </el-header>

      <el-main class="singerBox">

        <div class="con">
          <div class="block" v-for="(item,index) of singerData" :key="index" @click="clickSinger(item)">
            <el-avatar shape="circle" :size="120"  :src="item.icon"></el-avatar>
            <p>{{item.singerName}}</p>
          </div>
        </div>

      </el-main>

      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="page.currentPage"
              :page-size="page.pageSize"
              layout="prev, pager, next"
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
        select: {
          singerName: ''
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
            arg: this.select.singerName,
            arg2: "1",
            pageSize: this.page.pageSize,
            currentPage: currentPage,
          }
        }).then(res => {
          // console.log(res);
          this.singerData=res.data;
          if (res.data.code == 200) {
            this.singerData = res.data.data.records;
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
#searchForm{
  width: 350px;
  height: 60px;
  float: left;
}
#singerSeacher{
  padding-top: 10px;
}


  .el-container {
    margin-bottom: 40px;
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