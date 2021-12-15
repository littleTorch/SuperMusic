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
          'https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-06-22%2F5ef04a2ea879f.jpg',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091114%2F3x0eck3gbqj3x0eck3gbqj.jpg',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-12-14%2F5c131c5cb3abd.jpg',
          'https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=3911840071,2534614245&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=3908717,2002330211&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=318887420,2894941323&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=3251197759,2520670799&fm=193&f=GIF',
          'https://t7.baidu.com/it/u=3124693600,356058981&fm=193&f=GIF',
      ]
    };
  },
  methods: {
    onSuccess() {//往父级传递验证通过的函数
      this.$emit("success",true);
    },
    onReset() {//重置图片验证组件
      this.$refs.slideverify.reset();
    },
    onFail() {
        this.$emit("success",false);
    },
    onRefresh() {},
  },
};
</script>
