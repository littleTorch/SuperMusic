<template>
    <el-container class="container">
        <el-aside width="300px" class="left">
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                router>
                <el-menu-item index="indexSet">
                    <span slot="title">首页管理</span>
                </el-menu-item>
                <el-menu-item index="SongSet">
                    <span slot="title">歌曲管理</span>
                </el-menu-item>
                <el-menu-item index="SingSet">
                    <span slot="title">歌手管理</span>
                </el-menu-item>
                <el-menu-item index="PlaylistSet">
                    <span slot="title">排行榜管理</span>
                </el-menu-item>
                <el-menu-item index="/">
                    <span slot="title">歌单管理</span>
                </el-menu-item>
                <el-menu-item index="UserSet">
                    <span slot="title">用户管理</span>
                </el-menu-item>

                <el-menu-item index="/">
                    <span slot="title">角色管理</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-main class="right">
            <router-view/>
        </el-main>

    </el-container>
</template>

<script>
export default {
    data() {
        return {
            count: 0,
            url: "",
            fit: ""
        };
    },
    created() {
        // this.getAll();
    },
    methods: {}
}
</script>

<style scoped>
.container {
    width: 90%;
    height: 100%;
    overflow: hidden;
    margin: 0 auto;
}

.left {
    height: 100%;
    overflow: hidden;
    padding-bottom: 9999px;
    margin-bottom: -9999px;
    float: left;
    display: inline;
    border: 1px solid silver;
}

.right {
    float: right;
    height: auto;
    border: 1px solid silver;
}

.aa {
    height: 100%;
    border: 1px solid silver;
    position: inherit;
}
</style>