<template>
    <el-main>
        <!--搜索框-->
        <el-form :model="select" label-width="80px">
            <el-row>
                <el-col :span="5">
                    <el-form-item label="歌单名:">
                        <el-input v-model="select.playListName" placeholder="请输入内容"></el-input>
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
            <el-table-column align="center" prop="playlistName" label="歌单名" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="playlistComment" label="歌单描述" show-overflow-tooltip></el-table-column>
            <el-table-column align="center" prop="icon" label="歌单图片路径" show-overflow-tooltip></el-table-column>
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

        <el-dialog title="新增歌单" :visible.sync="addOneVisible" width="40%">
            <el-form
                style="text-align:center"
                :model="addOneForm"
                label-width="150px"
            >
                <el-form-item label="歌单名：">
                    <el-input v-model="addOneForm.playlistName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌单描述：">
                    <el-input type="textarea" autosize v-model="addOneForm.playlistComment" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌单图片路径：">
                    <el-input v-model="addOneForm.icon" placeholder=""></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="addOneVisible = false">取 消</el-button>
                        <el-button type="primary" @click="addOne">确 定</el-button>
                      </span>
        </el-dialog>

        <!--<el-dialog title="修改歌单" :visible.sync="updateVisible" width="40%">-->
        <!--    <el-form-->
        <!--        style="text-align:center"-->
        <!--        :model="updataData"-->
        <!--        label-width="150px"-->
        <!--    >-->
        <!--        <el-form-item label="歌单名：">-->
        <!--            <el-input v-model="updataData.playlistName" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="歌单描述：">-->
        <!--            <el-input type="textarea" autosize v-model="updataData.playlistComment" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="歌单图片路径：">-->
        <!--            <el-input v-model="updataData.icon" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--    </el-form>-->
        <!--    <span slot="footer" class="dialog-footer">-->
        <!--        <el-button @click="updateVisible = false">取 消</el-button>-->
        <!--        <el-button @click="setOne" type="primary">确 定</el-button>-->
        <!--      </span>-->
        <!--</el-dialog>-->

        <!--<el-dialog title="歌单详情" :visible.sync="detailsVisible" width="40%">-->
        <!--    <el-form-->
        <!--        style="text-align:center"-->
        <!--        :model="detailsData"-->
        <!--        label-width="150px"-->
        <!--        disabled-->
        <!--    >-->
        <!--        <el-form-item label="歌单名：">-->
        <!--            <el-input v-model="detailsData.playlistName" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="歌单描述：">-->
        <!--            <el-input type="textarea" autosize v-model="detailsData.playlistComment" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="歌单图片路径：">-->
        <!--            <el-input v-model="detailsData.icon" placeholder=""></el-input>-->
        <!--        </el-form-item>-->
        <!--    </el-form>-->
        <!--    <span slot="footer" class="dialog-footer">-->
        <!--        <el-button @click="detailsVisible = false">取 消</el-button>-->
        <!--        <el-button @click="detailsVisible = false" type="primary">确 定</el-button>-->
        <!--      </span>-->
        <!--</el-dialog>-->


        <el-dialog title="修改歌单" :visible.sync="updateVisible" width="40%">
            <el-form
                style="text-align:center"
                :model="updataData"
                label-width="150px"
            >
                <el-form-item label="排行榜名：">
                    <el-input v-model="updataData.playlistName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="排行榜描述：">
                    <el-input type="textarea" autosize v-model="updataData.playlistComment" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="排行榜图片路径：">
                    <el-input v-model="updataData.icon" placeholder=""></el-input>
                </el-form-item>
                <el-button @click="interiorAddOneClick" size="mini" type="primary" icon="el-icon-plus">单个新增</el-button>
                <el-button @click="interiorDeleteList" size="mini" type="primary" icon="el-icon-delete">批量删除</el-button>
                <!--数据表格-->
                <el-table
                    :data="SongTableData"
                    max-height="300px"
                    @selection-change="interiorHandleSelectionChange"
                    style="width: 100%"
                    size="mini"
                    tooltip-effect="dark">
                    <el-table-column align="center" type="selection"></el-table-column>
                    <el-table-column align="center" prop="name" label="歌名" show-overflow-tooltip></el-table-column>
                    <el-table-column align="center" prop="singer.singerName" label="歌手"></el-table-column>
                    <el-table-column label="操作" width="250" align="center">
                        <template slot-scope="scope">
                            <el-popconfirm
                                style="margin-left: 10px;"
                                title="您确定删除吗？"
                                icon="el-icon-info"
                                iconColor="red"
                                @confirm="interiorDeleteOneClick(scope.row.id)">
                                <el-button size="mini" type="danger" slot="reference">删除</el-button>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
                <!--数据分页-->
                <el-pagination
                    @current-change="interiorHandleCurrentChange"
                    :current-page="interiorPage.currentPage"
                    :page-size="interiorPage.pageSize"
                    :total="interiorPage.totalCount"
                    layout="total, prev, pager, next"
                ></el-pagination>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateVisible = false">取 消</el-button>
                <el-button @click="setOne" type="primary">确 定</el-button>
              </span>
        </el-dialog>

        <el-dialog title="歌单详情" :visible.sync="detailsVisible" width="40%">
            <el-form
                style="text-align:center"
                :model="detailsData"
                label-width="150px"
                disabled
            >
                <el-form-item label="歌单名：">
                    <el-input v-model="detailsData.playlistName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌单描述：">
                    <el-input type="textarea" autosize v-model="detailsData.playlistComment" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="歌单图片路径：">
                    <el-input v-model="detailsData.icon" placeholder=""></el-input>
                </el-form-item>
                <el-button @click="interiorAddOneClick" size="mini" type="primary" icon="el-icon-plus">单个新增</el-button>
                <el-button @click="interiorDeleteList" size="mini" type="primary" icon="el-icon-delete">批量删除</el-button>
                <!--数据表格-->
                <el-table
                    :data="SongTableData"
                    max-height="300px"
                    @selection-change="interiorHandleSelectionChange"
                    style="width: 100%"
                    size="mini"
                    tooltip-effect="dark">
                    <el-table-column align="center" type="selection"></el-table-column>
                    <el-table-column align="center" prop="name" label="歌名" show-overflow-tooltip></el-table-column>
                    <el-table-column align="center" prop="singer.singerName" label="歌手"></el-table-column>
                    <el-table-column label="操作" width="250" align="center">
                        <template slot-scope="scope">
                            <el-popconfirm
                                style="margin-left: 10px;"
                                title="您确定删除吗？"
                                icon="el-icon-info"
                                iconColor="red"
                                @confirm="interiorDeleteOneClick(scope.row.id)">
                                <el-button size="mini" type="danger" slot="reference">删除</el-button>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
                <!--数据分页-->
                <el-pagination
                    @current-change="interiorHandleCurrentChange"
                    :current-page="interiorPage.currentPage"
                    :page-size="interiorPage.pageSize"
                    :total="interiorPage.totalCount"
                    layout="total, prev, pager, next"
                ></el-pagination>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="detailsVisible = false">取 消</el-button>
                <el-button @click="detailsVisible = false" type="primary">确 定</el-button>
              </span>
        </el-dialog>

        <!--嵌套对话框--增加歌单歌曲-->
        <el-dialog
            title="添加歌曲"
            :visible.sync="interiorDialogVisible"
            width="30%"
            center>
            <el-select
                v-model="addSong"
                multiple
                filterable
                remote
                reserve-keyword
                placeholder="请输入关键词"
                :remote-method="remoteMethod"
                :loading="loading">
                <el-option
                    v-if="item.singer"
                    v-for="item in allSong"
                    :key="item.id"
                    :label="item.name+'---'+item.singer.singerName"
                    :value="item.id">
                </el-option>
            </el-select>
            <span slot="footer" class="dialog-footer">
            <el-button @click="interiorDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSongClick">确 定</el-button>
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
            interiorPage: {
                currentPage: 1, // 当前页
                pageSize: 6, // 每页显示条目个数
                totalCount: 0 // 总条目数
            },
            tableData: '',
            tableChecked: [],
            select: {
                playListName: ''
            },
            detailsVisible: false,
            detailsData: '',
            updateVisible: false,
            updataData: '',
            addOneVisible: false,
            addOneForm: {
                playlistName: '',
                playlistComment: '',
                icon: '',
                playlistType:'0',
            },
            interiorDialogVisible: false,
            SongTableData: '',
            interiorTableChecked: [],
            currentPlaylistId: '',
            allSong:'',
            addSong:'',
            loading: false,
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
            this.axios.get("playlist/page", {
                params: {
                    arg: this.select.playListName,
                    arg2: "0",
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
        selSong(pageNo) {
            console.log(this.currentPlaylistId)
            this.axios.get("playlist-song/page", {
                params: {
                    arg: this.currentPlaylistId,
                    pageSize: this.interiorPage.pageSize,
                    currentPage: pageNo,
                }
            }).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.SongTableData = res.data.data.records;
                    this.interiorPage.totalCount = res.data.data.total;
                    this.interiorPage.pageSize = res.data.data.size;
                    this.interiorPage.currentPage = res.data.data.current;
                } else {
                    this.SongTableData = '';
                }
            })
        },
        addOne() {
            this.axios.post("playlist/add", JSON.stringify(this.addOneForm)).then(res => {
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
            this.axios.put("playlist/updata", JSON.stringify(this.updataData)).then(res => {
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
            this.axios.delete("/playlist/dels", {
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
            this.currentPlaylistId = row.id;
            this.selSong(1)
            console.log(row)
            this.updateVisible = true;
            this.updataData = JSON.parse(JSON.stringify(row));
        },
        AddOneClick() {
            this.addOneVisible = true;
        },
        details(row) {
            console.log(row);
            this.currentPlaylistId = row.id;
            this.selSong(1)
            this.detailsVisible = true;
            this.detailsData = row;
        },
        //表格前的复选框点击事件
        handleSelectionChange(val) {
            this.tableChecked = val;
        },
        interiorHandleSelectionChange(val) {
            this.interiorTableChecked = val;
        },
        //pageSize改变调用
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        // 页数改变调用
        handleCurrentChange(val) {
            this.sel(val)
        },
        interiorHandleCurrentChange(val) {
            this.selSong(val)
        },
        interiorAddOneClick() {
            this.interiorDialogVisible = true;
        },
        interiorDeleteList() {
            this.$confirm('是否删除所选信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(() => {
                let ids = [];
                this.interiorTableChecked.forEach(item => {
                    ids.push({
                        arg: item.id,
                        arg2: this.currentPlaylistId,
                    });
                });
                console.log(ids)
                this.SongDel(ids);
            })
        },
        interiorDeleteOneClick(id) {
            console.log(id)
            let ids = [{
                arg: id,
                arg2: this.currentPlaylistId,
            }];
            console.log(ids)
            this.SongDel(ids);
        },
        SongDel(ids) {
            console.log(JSON.stringify(ids))
            this.axios.delete("/playlist-song/dels", {
                data: JSON.stringify(ids)
            }).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.selSong(this.interiorPage.currentPage)
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
        //添加歌曲模糊查询监听器
        remoteMethod(query) {
            if (query !== '') {
                this.loading = true;
                this.loading = false;
                this.axios.get("song/like", {
                    params: {
                        arg: query,
                    }
                }).then(res => {
                    this.allSong = res.data.data;
                })
            } else {
                this.allSong = [];
            }
        },
        addSongClick(){
            let addform={
                playlistId:this.currentPlaylistId,
                songIds:this.addSong
            }
            this.axios.post("playlist-song/add", JSON.stringify(addform)).then(res => {
                console.log(res);
                if (res.data.code == 200) {
                    this.interiorDialogVisible = false;
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                    this.selSong(this.interiorPage.currentPage)
                } else {
                    this.$message({
                        type: "error",
                        duration: 1000,
                        message: res.data.msg
                    })
                }
            })
        },
    }
}
</script>

<style scoped>

</style>