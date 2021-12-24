<template>
  <div id="user">

<div id="box">

    <div>
    <router-link exact to="/mainL/login" >登录</router-link>
    <router-link to="/mainL/register">注册</router-link>
    </div>
    <div class="row2">
      <router-view></router-view>
    </div>
</div>
  </div>
</template>

<script>
export default {
  name:'uer',
 

};
</script>

<style>


#user{
  /* background: url("../assets/aa.jpg"); */
  background: rgba(2,2,2,0.5);
    background-size: 100% 100%;
    min-width: 960px;
    min-height: 450px;
    height: 100%;
    position: fixed;
    width: 100%;
     display:flex;
  flex-direction: row;
  justify-content: center;
 align-items:baseline;

}
#box{
  margin-top: 20px;
  border-radius: 10px;
 background: #fff;
  overflow: hidden;
}
#box a{
  display: block;
  float: left;
  width: 180px;
  height: 50px;
  line-height: 50px;
  text-align: center;

}
.active{
    background: rgba(20, 77, 119, 0.4);

  }


</style>
