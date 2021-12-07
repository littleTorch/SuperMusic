<template>
  <div>
    <el-container>
      <el-main
        ><el-row :gutter="20">
          <el-col :span="4" :offset="4">
            <div>
              <h3>排行榜</h3>
              <el-divider />
              <template>
                <ul
                  class="infinite-list"
                  v-infinite-scroll="load"
                  style="overflow: auto"
                >
                  <li
                    v-for="(item, index) in count"
                    :key="index"
                    class="infinite-list-item"
                  >
                    <div class="left-item">
                      <el-image
                        style="width: 80px; height: 80px"
                        :src="url"
                        :fit="fit"
                      />
                      <div class="left-item">
                        <h2>xxxx</h2>
                      </div>
                    </div>
                  </li>
                </ul>
              </template>
            </div></el-col></el-row
        ></el-main
      ></el-container
    >
  </div>
</template>
            </div>
          </el-col>
          <el-col :span="12">
            <div></div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      count: 0,
      url: "",
      fit:""
    };
  },
  created(){
    this.getAll();
  },
  methods: {
    load() {
      this.count += 2;
    },
    getAll(){
      this.axios.get("playlist").then((response) => {
        let res = response.data;
        console.log(response);
      });
    },
  },
};
</script>
<style scoped>
.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 10px 0 !important;
}
.left-item {
  float: right;
}
</style>