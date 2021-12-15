<template>
    <el-main>
        <el-row style="margin-bottom: 10px">
            <el-button @click="AddOneClick" type="primary" icon="el-icon-plus">新增轮播图</el-button>
        </el-row>

        <!--数据表格-->
        <el-table
            :data="tableData"
            :height="tableHeight"
            @selection-change="handleSelectionChange"
            border
            style="width: 100%"
            tooltip-effect="dark">
            <el-table-column align="center" prop="imgName" label="图片名" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="details(scope.row)" type="primary" size="mini">详情</el-button>
                    <el-popconfirm
                        style="margin-left: 10px;"
                        title="您确定删除吗？"
                        icon="el-icon-info"
                        iconColor="red"
                        @confirm="del(scope.row.imgName)">
                        <el-button size="mini" type="danger" slot="reference">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="新增轮播图" :visible.sync="addOneVisible" width="30%">
            <el-form
                style="text-align:center"
                :model="addOneForm"
            >
                <el-form-item>
                    <el-upload
                        class="upload-demo"
                        drag
                        :http-request="uploadImg"
                        multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10MB</div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addOneVisible = false">取 消</el-button>
                <el-button type="primary" @click="addOneVisible = false">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="图片详情" :visible.sync="detailsVisible" width="80%">
            <el-image :src="detailsData" lazy></el-image>
            <span slot="footer" class="dialog-footer">
                <el-button @click="detailsVisible = false">取 消</el-button>
                <el-button @click="detailsVisible = false" type="primary">确 定</el-button>
              </span>
        </el-dialog>
    </el-main>
</template>

<script>
export default {
    data() {
        return {
            tableHeight: 0,
            tableData: [],
            tableChecked: [],
            detailsVisible: false,
            detailsData: '',
            addOneVisible: false,
        }
    },
    created() {
        this.sel()
    },
    mounted() {
        this.$nextTick(() => {
            this.tableHeight = window.innerHeight - 270;
        });
    },
    methods: {
        uploadImg(params) {
            var form = new FormData();
            form.append("file", params.file);
            this.axios({
                method: "post",
                url: "/home-slide/uploadImg",
                data: form,
                Headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then((res) => {
                this.$message({
                    type: "success",
                    duration: 1000,
                    message: res.data.msg
                })
                this.sel();
            });
        },
        sel() {
            this.axios.get("/home-slide/getImgsUrl").then((res) => {
                let imgs= res.data.data;
                this.tableData=[];
                for (let i = 0; i < imgs.length; i++) {
                    let a={imgName:imgs[i]}
                    this.tableData.push(a)
                }
            });
        },
        del(name) {
            this.axios.delete("/home-slide/del", {
                data: name
            }).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.updateVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.sel()
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
        AddOneClick() {
            this.addOneVisible = true;
        },
        details(row) {
            this.detailsVisible = true;
            let str=window.location.href;
            //获取当前链接用正则匹配出服务器地址再拼接上去
            // let urlhead=str.match(/^.*[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/)[0];
            //挂到公网上使用
            // this.detailsData = "http://sm2.v.frp.fit/home-slide/showImg?imgUrl="+row.imgName;
            //本地开发使用
            this.detailsData = "http://localhost:8080/home-slide/showImg?imgUrl="+row.imgName;
        },
        //表格前的复选框点击事件
        handleSelectionChange(val) {
            this.tableChecked = val;
        },
    }
}
</script>

<style scoped>

</style>