<template>
    <div id="app">

        <HelloWorld/>
        <router-view></router-view>
        <div v-if="isPlay" id="footer">
            <aplayer autoplay
                     listMaxHeight="200px"
                     :music="songList[0]"
                     :list="songList"
            />
        </div>
    </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import aplayer from "vue-aplayer";

export default {
    name: 'app',
    components: {
        HelloWorld, aplayer
    },
    provide() {
        return {
            addSong: this.addSong
        }
    },
    created() {

    },
    data() {
        return {
            isPlay: false,
            songList: []
        }
    },
    methods: {
        addSong(song) {
            this.isPlay = true;
            if (!this.findElem(this.songList,"id",song.id)){
                this.songList.push({
                    id: song.id,
                    title: song.name,
                    artist: song.singer.singerName,
                    src: song.songUrl,
                    pic: song.pictureUrl,
                })
            }
        },
        findElem(arrayToSearch, attr, val) {
            for (var i = 0; i < arrayToSearch.length; i++) {
                if (arrayToSearch[i][attr] == val) {
                    return true;
                }
            }
            return false;
        },
    },
}
</script>

<style>
#app {
    width: 100%;
    height: 100%;
}

#footer {
    position: fixed;
    left: 0px;
    bottom: 0px;
    width: 100%;
    height: auto;
    max-height: 300px;
    background-color: #eee;
    z-index: 9999;
}
</style>
