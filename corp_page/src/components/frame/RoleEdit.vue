<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false" width="680">
    <div class="page">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="roleForm" :label-width="80" :rules="ruleValidate" class="form-item">

          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="角色名称" prop="roleName">
            <Input v-model="formItem.roleName" placeholder="不超过50个字符" />
          </FormItem>
          <FormItem label="备注" prop="remark">
            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."/>
          </FormItem>
          <div class="form-sub-title">
            <Icon type="gear-b" size="16"></Icon>
            权限设置
          </div>
          <FormItem  prop="powers">
            <PowerCheckTree ref="powerCheckTree"></PowerCheckTree>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" v-if="this.isEdit==1">重置</Button>
            <Button type="ghost" @click="close" style="margin-left: 8px">取消</Button>
          </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import PowerCheckTree from '@/components/frame/PowerCheckTreeComp';

  export default {
    components: {
      Loading,
      PowerCheckTree
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: 0,
        //表单对象
        formItem: {
          roleId: 0,
          roleName: '',
          remark: '',
          powerIds:'',
        },
        //验证
        ruleValidate: {
          roleName: [
            {required: true, whitespace: true, message: '名称不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit === 0) {
          return "添加角色";
        } else {
          return "编辑角色";
        }
      },
    },
    methods: {
      save() {
        this.$refs['roleForm'].validate((valid) => {
          if (valid) {
            this.saveRole();
          } else {
            return;
          }
        });
      },
      saveRole() {
        let url = '';
        let msg = '';
        if (this.isEdit === 0) {
          url = '/api/role/add';
          msg = '添加成功';
        } else {
          url = '/api/role/edit';
          msg = '修改成功';
        }

        var ids = [];
        this.$refs.powerCheckTree.checked.map((p)=>{
          if(p != ''){
            ids.push(p);
          }
        });
        this.formItem.powerIds = ids.join(',');

        this.loading = 1;
        this.$http.post(url, this.formItem).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success(msg);
            this.close();
            this.$emit("on-save");
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
      },
      open(roleId) { 
        this.show = true;
        this.$refs['roleForm'].resetFields();
        this.$refs.powerCheckTree.checked=[];
        this.checked = [];
        if (roleId !== 0) {
          this.isEdit = 1;
          this.getRole(roleId);
        } else {
          this.loading = 0;
          this.isEdit = 0;
          this.$refs.powerCheckTree.load();
        }
      },
      getRole(roleId) {
        this.$http.post('/api/role/detail?roleId=' + roleId, {}).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
            this.formItem = res.data.data;
            var strIds = this.formItem.powerIds.split(",");
            this.$refs.powerCheckTree.checked = strIds.map((str)=>{return str*1;});
            //加载功能点
            this.$refs.powerCheckTree.load();
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
      },
      close() {
        this.show = false;
      },
      reset() {
        this.$refs.powerCheckTree.checked=[];
        this.checked = [];
        this.$refs['roleForm'].resetFields();
        this.getRole(this.formItem.roleId);
      }
    }
  }

</script>

<style type="text/css">
  .form-sub-title {
    font-size: 14px;
    margin: 0 0 12px 18px;
    color: #2b85e4;
  }
</style>
