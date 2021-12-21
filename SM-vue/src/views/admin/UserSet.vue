<template>
    <el-main style="margin-top: 10px">
        <!--搜索框-->
        <el-form :model="select" label-width="80px">
            <el-row>
                <el-col :span="5">
                    <el-form-item label="用户名:">
                        <el-input v-model="select.userName" placeholder="请输入内容"></el-input>
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
            <el-table-column align="center" prop="username" label="用户名" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="password" label="密码" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="phone" label="电话"></el-table-column>
            <el-table-column align="center" prop="nickname" label="昵称" show-overflow-tooltip></el-table-column>
            <el-table-column :formatter="sexFTR" align="center" prop="sex" label="性别"
                             show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="email" label="邮箱" show-overflow-tooltip></el-table-column>
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

        <el-dialog title="新增用户" :visible.sync="addOneVisible" width="40%">
            <el-form
                ref="addOneForm"
                :model="addOneForm"
                label-width="80px"
            >
                <el-form-item label="用户名:">
                    <el-input v-model="addOneForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码:">
                    <el-input v-model="addOneForm.password"></el-input>
                </el-form-item>
                <el-form-item label="性别:">
                    <el-radio-group v-model="addOneForm.sex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="昵称:">
                    <el-input v-model="addOneForm.nickname"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:">
                    <el-input v-model="addOneForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="addOneForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="addOneVisible = false">取 消</el-button>
                        <el-button type="primary" @click="addOne">确 定</el-button>
                      </span>
        </el-dialog>

        <el-dialog title="修改用户" :visible.sync="updateVisible" width="40%">
            <el-form
                :model="updataData"
                label-width="80px"
            >
                <el-form-item label="用户名:">
                    <el-input v-model="updataData.username"></el-input>
                </el-form-item>
                <el-form-item label="密码:">
                    <el-input v-model="updataData.password"></el-input>
                </el-form-item>
                <el-form-item label="性别:">
                    <el-radio-group v-model="updataData.sex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="昵称:">
                    <el-input v-model="updataData.nickname"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:">
                    <el-input v-model="updataData.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="updataData.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateVisible = false">取 消</el-button>
                <el-button @click="setOne" type="primary">确 定</el-button>
              </span>
        </el-dialog>

        <el-dialog title="用户详情" :visible.sync="detailsVisible" width="40%">
            <el-form
                :model="detailsData"
                label-width="80px"
                disabled
            >
                <el-form-item label="用户名:">
                    <el-input v-model="detailsData.username"></el-input>
                </el-form-item>
                <el-form-item label="密码:">
                    <el-input v-model="detailsData.password" disabled></el-input>
                </el-form-item>
                <el-form-item label="性别:">
                    <el-radio-group v-model="detailsData.sex">
                        <el-radio :label="1">男</el-radio>
                        <el-radio :label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="昵称:">
                    <el-input v-model="detailsData.nickname"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:">
                    <el-input v-model="detailsData.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="detailsData.email"></el-input>
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
                userName: ''
            },
            detailsVisible: false,
            detailsData: '',
            updateVisible: false,
            updataData: '',
            addOneVisible: false,
            addOneForm: {
                username: '',
                password: '',
                phone: '',
                icon: '',
                nickname: '',
                sex: 0,
                email: '',
            },
            singers: [],
        }
    },
    created() {
        this.sel(1)
        this.selSonger();
    },
    mounted() {
        this.$nextTick(() => {
            this.tableHeight = window.innerHeight - 270;
        });
    },
    methods: {
        sexFTR(row) {
            if (row.sex == "0") {
                return "男"
            } else {
                return "女"
            }
        },
        sel(pageNo) {
            this.axios.get("user/page", {
                params: {
                    arg: this.select.userName,
                    pageSize: this.page.pageSize,
                    currentPage: pageNo,
                }
            }).then(res => {
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
            this.axios.post("user/add", JSON.stringify(this.addOneForm)).then(res => {
                if (res.data.code == 200) {
                    this.addOneVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.addOneForm=this.$options.data().addOneForm;
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
            this.axios.put("user/updata", JSON.stringify(this.updataData)).then(res => {
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
            this.axios.delete("/user/dels", {
                data: JSON.stringify(ids)
            }).then(res => {
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
                this.del(ids);
            })
        },
        deleteOneClick(id) {
            let ids = [id];
            this.del(ids);
        },
        updateClick(row) {
            this.updateVisible = true;
            this.updataData = JSON.parse(JSON.stringify(row));
        },
        AddOneClick() {
            this.addOneVisible = true;
        },
        details(row) {
            this.detailsVisible = true;
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
    }
}
</script>

<style scoped>

</style>