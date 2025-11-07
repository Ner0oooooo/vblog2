<template>
  <div class="app-container">
    <el-form ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="80px">
      <el-form-item label="评论ID" prop="commentId">
        <el-input
          v-model="queryParams.commentId"
          placeholder="请输入评论ID"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="处理状态"
          clearable
          style="width: 240px"
        >
          <el-option label="未处理" value="0" />
          <el-option label="已处理" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="reportList" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="评论ID" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.commentId }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="评论内容" min-width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.commentContent }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="举报用户" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.userNickname }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="举报原因" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.reason }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="处理状态" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '0'" type="warning">未处理</el-tag>
          <el-tag v-else type="success">已处理</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="处理结果" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.result === '0'" type="danger">删除评论</el-tag>
          <el-tag v-else-if="scope.row.result === '1'" type="success">保持原样</el-tag>
          <el-tag v-else type="info">未处理</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="处理人" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.handleUserNickname }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="处理时间" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="举报时间" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleReport(scope.row)"
            :disabled="scope.row.status === '1' || $hasBP('btn.comment.report.handle') === false"
          >处理</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            :disabled="$hasBP('btn.comment.report.del') === false"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 处理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="评论内容" prop="commentContent">
          <el-input v-model="form.commentContent" type="textarea" :rows="3" readonly />
        </el-form-item>
        <el-form-item label="举报原因" prop="reason">
          <el-input v-model="form.reason" type="textarea" :rows="2" readonly />
        </el-form-item>
        <el-form-item label="处理结果" prop="result">
          <el-radio-group v-model="form.result">
            <el-radio label="0">删除评论</el-radio>
            <el-radio label="1">保持原样</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCommentReport, getCommentReport, handleCommentReport, deleteCommentReport } from '@/api/comment/report'

export default {
  name: 'CommentReport',
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 评论举报列表
      reportList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        commentId: null,
        status: null
      },
      // 表单参数
      form: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询评论举报列表 */
    getList() {
      this.loading = true
      listCommentReport(this.queryParams).then(response => {
        this.reportList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.resetForm('form')
    },
    // 表单重置
    resetForm(formName) {
      this.$refs[formName]?.resetFields()
    },
    /** 处理按钮操作 */
    handleReport(row) {
      getCommentReport(row.id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '处理评论举报'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          handleCommentReport(this.form).then(response => {
            this.$message.success('处理成功')
            this.open = false
            this.getList()
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除评论举报数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return deleteCommentReport(ids)
      }).then(response => {
        this.$message.success('删除成功')
        this.getList()
      }).catch(function() {
        this.$message.info('已取消删除')
      })
    }
  }
}
</script>