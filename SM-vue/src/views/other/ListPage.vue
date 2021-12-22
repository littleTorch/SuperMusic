<template>
    <div>
        <div v-for="item in song" >
            {{item.name}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "ListPage",

        data() {
            return {
                song: ''
            }
        },
        created(){
            this.singerId()
        },
        methods: {
            singerId(){
                this.axios.get("/playlist-song/playlistid/"+this.$route.params.id).then(res=>{
                    this.song=res.data.data
                    console.log(this.song)
                })
            }
    }
    }
</script>

<style scoped>

</style>