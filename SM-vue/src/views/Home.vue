<template>
    <div class="home">
        <div class="homeMain">
            <HelloWorld v-if="banners" :mybanners="banners"/>
        </div>


    </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/Home/banner.vue'

export default {
    name: 'Home',
    components: {
        HelloWorld
    }, data() {
        return {
            banners: [],
            singers:[],
            songSheet:[],
        }

    }, methods: {
        getData() {
            this.axios.get("/home-slide/getImgsUrl").then((res) => {
                this.banners = res.data.data;
            });
            this.axios.get("/singer/page",{
                params: {
                    pageSize: 8,
                    currentPage: Math.ceil(Math.random()*10),
                }
            }).then((res) => {
                this.singers = res.data.data.records;
                console.log(this.singers)
            });
            this.axios.get("/playlist/page",{
                params: {
                    arg2: "1",
                    pageSize: 8,
                    currentPage: Math.ceil(Math.random()*10),
                }
            }).then((res) => {
                this.songSheet = res.data.data.records;
                console.log(this.songSheet)
            });
        }

    },
    created() {
        this.getData();

    }
}
</script>

<style scoped>
.home {

    display: flex;
    justify-content: center;
}

.homeMain {
    width: 1200px;
    min-width: 900px;
}
</style>