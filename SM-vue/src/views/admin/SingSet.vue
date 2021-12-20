<template>
    <el-main style="margin-top: 10px">
        <!--搜索框-->
        <el-form :model="select" label-width="80px">
            <el-row>
                <el-col :span="5">
                    <el-form-item label="歌曲名:">
                        <el-input v-model="select.singerName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                </el-col>
                <el-button
                    style="margin-left: 20px;"
                    class="btn-left"
                    size="mini"
                    type="primary"
                    icon="el-icon-search"
                    @click="sel(1)"
                >查询
                </el-button>
                <el-button @click="AddOneClick" size="mini" type="primary" icon="el-icon-plus">单个新增</el-button>
                <el-button @click="deleteList" size="mini" type="primary" icon="el-icon-delete">批量删除</el-button>
            </el-row>
        </el-form>
        <!--数据表格-->
        <el-table
            :data="tableData"
            :height="tableHeight"
            @selection-change="handleSelectionChange"
            border
            style="width: 100%"
            tooltip-effect="dark">
            <el-table-column align="center" type="selection"></el-table-column>
            <el-table-column align="center" prop="singerName" label="歌手名" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="profile" label="歌手描述" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="icon" label="歌手图片" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="details(scope.row)" type="primary" size="mini">详情</el-button>
                    <el-button @click="updateClick(scope.row)" type="success" size="mini">编辑</el-button>
                    <el-popconfirm
                        style="margin-left: 10px;"
                        title="您确定删除吗？"
                        icon="el-icon-info"
                        iconColor="red"
                        @confirm="deleteOneClick(scope.row.id)">
                        <el-button size="mini" type="danger" slot="reference">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <!--数据分页-->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page.currentPage"
            :page-size="page.pageSize"
            :total="page.totalCount"
            layout="total, prev, pager, next"
        ></el-pagination>

        <el-dialog title="新增歌曲" :visible.sync="addOneVisible" width="80%">
            <el-form
                :model="addOneForm"
                label-width="120px"
            >
                <el-form-item label="歌手名：">
                    <el-input v-model="addOneForm.singerName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手描述：">
                    <el-input autosize type="textarea" v-model="addOneForm.profile" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手图片：">
                    <el-input v-model="addOneForm.icon" placeholder=""></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="addOneVisible = false">取 消</el-button>
                        <el-button type="primary" @click="addOne">确 定</el-button>
                      </span>
        </el-dialog>

        <el-dialog title="修改歌曲" :visible.sync="updateVisible" width="80%">
            <el-form
                :model="updataData"
                label-width="120px">
                <el-form-item label="歌手名：">
                    <el-input v-model="updataData.singerName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手描述：">
                    <el-input autosize type="textarea" v-model="updataData.profile" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手图片：">
                    <el-input v-model="updataData.icon" placeholder=""></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateVisible = false">取 消</el-button>
                <el-button @click="setOne" type="primary">确 定</el-button>
              </span>
        </el-dialog>

        <el-dialog title="歌手详情" :visible.sync="detailsVisible" width="80%">
            <el-form
                style="text-align:center"
                :model="detailsData"
                label-width="120px"
                disabled
            >
                <el-form-item label="歌手名：">
                    <el-input v-model="detailsData.singerName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手描述：">
                    <el-input autosize type="textarea" v-model="detailsData.profile" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌手图片：">
                    <el-input v-model="detailsData.icon" placeholder=""></el-input>
                </el-form-item>
            </el-form>
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
            page: {
                currentPage: 1, // 当前页
                pageSize: 12, // 每页显示条目个数
                totalCount: 0 // 总条目数
            },
            tableData: '',
            tableChecked: [],
            select: {
                singerName: ''
            },
            detailsVisible: false,
            detailsData: '',
            updateVisible: false,
            updataData: '',
            addOneVisible: false,
            addOneForm: {
                singerName: '',
                profile: '',
                icon: '',
            },
            singers:[],
        }
    },
    created() {
        this.sel(1)
    },
    mounted() {
        this.$nextTick(() => {
            this.tableHeight = window.innerHeight - 270;
        });
    },
    methods: {
        sel(pageNo) {
            this.axios.get("singer/page", {
                params: {
                    arg: this.select.singerName,
                    pageSize: this.page.pageSize,
                    currentPage: pageNo,
                }
            }).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.tableData = res.data.data.records;
                    this.page.totalCount = res.data.data.total;
                    this.page.pageSize = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                } else {
                    this.tableData = '';
                }
            })
        },
        addOne() {
            this.axios.post("singer/add", JSON.stringify(this.addOneForm)).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.addOneVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.sel(this.page.currentPage)
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
        setOne() {
            console.log(JSON.stringify(this.updataData))
            this.axios.put("singer/updata", JSON.stringify(this.updataData)).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.updateVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.sel(this.page.currentPage)
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
        del(ids) {
            console.log(JSON.stringify(ids))
            this.axios.delete("/singer/dels", {
                data: JSON.stringify(ids)
            }).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.updateVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.sel(this.page.currentPage)
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
        deleteList() {
            this.$confirm('是否删除所选信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(() => {
                let ids = [];
                this.tableChecked.forEach(id => {
                    ids.push(id.id);
                });
                console.log(ids)
                this.del(ids);
            })
        },
        deleteOneClick(id) {
            console.log(id)
            let ids = [id];
            this.del(ids);
        },
        updateClick(row) {
            console.log(row)
            this.updateVisible = true;
            this.updataData = JSON.parse(JSON.stringify(row));
        },
        AddOneClick() {
            this.addOneVisible = true;
        },
        details(row) {
            this.detailsVisible = true;
            console.log(row);
            this.detailsData = row;
        },
        //表格前的复选框点击事件
        handleSelectionChange(val) {
            this.tableChecked = val;
        },
        //pageSize改变调用
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        // 页数改变调用
        handleCurrentChange(val) {
            this.sel(val)
        },
        //重置表单内容
        resetForm(formName) {
            if (this.$refs[formName]) {
                this.$refs[formName].resetFields();
            }
        },
    }
}
</script>

<style scoped>

</style>