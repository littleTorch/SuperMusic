<template>
  <div id="app">
 
    <HelloWorld />
    <router-view></router-view>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  width: 100%;
  height: 100%;
}

</style>
