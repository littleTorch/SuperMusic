<template>
    <div class="admin">

        <div class="adminMain">

            <div class="adminL">
                <ul>
                    <li>
                        <router-link exact to="/Admin/first" >首页管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/1">歌单管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/singer">歌手管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/2t">排行榜管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/f3st">用户管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/f3">权限管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/fi4st">角色管理</router-link>
                    </li>
                    <li>
                        <router-link to="/Admin/fir5t">日志管理</router-link>
                    </li>
                </ul>
                </div>  
                
            <div class="adminR">
                <router-view></router-view>
                </div>    

        </div>

    </div>
</template>


<script>
export default {
    
}
</script>


<style scoped>
.admin{
    justify-content: center;
    display: flex;
   
}
.adminMain{
    width: 1200px;
    min-width: 900px;
    display: flex;
     height: calc(100vh - 50px);
}
.adminL{
    flex: 1 ;
    background: rgba(15, 15, 15, 0.15);
 
}

.adminR{
    flex: 4 ;
}
.adminL ul li{
    list-style-type: none;
}
.adminL a{
    display: block;
    color: cornflowerblue;
    height: 50px;
    line-height: 50px;
    width: 100%;
    text-align: center;
}
.adminL a:hover{
      background: rgba(15, 15, 15, 0.3);  
}
.active{
    background: rgba(15, 15, 15, 0.5);
}
</style>


