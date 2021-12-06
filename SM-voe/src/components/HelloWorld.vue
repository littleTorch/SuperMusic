<template>
  <div class="hello">

    <div id="nav">
      <div id="navigation">
        <ul >
          <li class="navImg">
            <img src="../assets/logo.png" alt="">
          </li>
          <li>
            <router-link exact to="/">首页</router-link>
          </li>
          <li>
            <router-link to="/SongSheet">歌单</router-link>
          </li>
          <li>
            <router-link to="/Singer">歌手</router-link>
          </li>
          <li>
            <router-link to="/mySongSheet">我的歌单</router-link>
          </li>
          <li>
            <router-link to="/RankingList">排行榜</router-link>
          </li>
          <li>
            <router-link to="/Admin">管理员</router-link>
          </li>


        </ul>

<div class="search">
      
  <el-input
    placeholder="请输入内容"
    prefix-icon="el-icon-search"
    v-model="input1">
  </el-input>
</div>

<div id="navR">

    <el-dropdown trigger="click" >
      <span class="el-dropdown-link">
      个人中心
      </span>
      <el-dropdown-menu slot="dropdown"  class="dMenu">
          <ul >
            <li>
              <router-link to="/mainL/login" > 登录</router-link>
            </li>
            <li>
              <router-link to="/mainL/register">注册</router-link>
            </li>
          </ul>
      </el-dropdown-menu>
    </el-dropdown>

</div>

      </div>
    </div>
   
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
    data() {
    return {
      input1: '',
    
    }
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

ul {
  list-style-type: none;
}
li {
  display: inline-block;
}
a {
  color: #42b983;
}
.active{
    background: rgba(134, 221, 205, 0.5);
  }
  /* 导航栏 */
  #nav{
    width: 100%;
    height: 50px;
    background: cornsilk;
    display: flex;
    justify-content: center;
  }
  #navigation{
    min-width: 900px;
    width: 1200px;
    height: 100%;
    display: flex;
    justify-content: space-between;
  }
  a:hover{
    background: lightcoral;
  }
.navImg{
  width: 120px;
  height: 50px;
}
.navImg img{
  width: 100%;
  height: 100%;
}
ul li{
  float: left;
}
ul li a{
  display: block;
  height: 50px;
  line-height: 50px;
  padding: 0 15px;
}
.search{
  line-height: 50px;
}
/* 下拉菜单 */
.navR{

}
 .el-dropdown-link {
    cursor: pointer;
      color: #42b983;
      line-height: 50px;
      display: block;
      height: 50px;
      padding: 0 20px;
      font-weight: 500;
  }
  .el-dropdown-link:hover{
    background: lightcoral;
  }
  .dMenu{
    width: 92px;
    text-align: center;
  }

  .dMenu a{
    display: block;
    width: 100%;

  }  
</style>
