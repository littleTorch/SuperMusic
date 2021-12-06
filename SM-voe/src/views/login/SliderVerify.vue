<template>
<div>
    <!-- 滑动子组件  :show="false" 显示-->
      <slide-verify
    :l="42"
    :r="10"
    :w="310"
    :h="155"
    :imgs="picArray"
   :accuracy=3
    slider-text="向右滑动完成验证"
    ref="slideverify"
    @success="onSuccess"
    @fail="onFail"
    @refresh="onRefresh"
  ></slide-verify>

</div>
</template>

<script>
export default {
  name: "SliderVerify",
  data() {
    return {
      //在data中引入`assets`中的图片可以通过`require`的方式来引入
      picArray: [
        require("@/assets/img/001.jpg"),
        require("../../assets/img/002.jpg"),
        
      ]
    };
  },
  methods: {
    onSuccess() {//往父级传递验证通过的函数
      this.$emit("success");
    },
    onReset() {//重置图片验证组件
      this.$refs.slideverify.reset();
    },
    onFail() {},    
    onRefresh() {},
  },
};
</script>
