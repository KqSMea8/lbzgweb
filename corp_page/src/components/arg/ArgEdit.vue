<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="argForm" :label-width="80" :rules="ruleValidate" class="form-item">

          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="编码" prop="argCode">
            <Input v-model="formItem.argCode" placeholder="" :disabled="isEdit" />
          </FormItem>
          <FormItem label="标题" prop="argText">
            <Input v-model="formItem.argText" placeholder=""/>
          </FormItem>
          <FormItem label="排序" prop="seq"> 
            <InputNumber :max="99999" :min="0" v-model="formItem.seq"></InputNumber>
          </FormItem>
          <FormItem label="备注" prop="argRemark">
            <Input v-model="formItem.argRemark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder=""/>
          </FormItem>  
          <FormItem>
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" >重置</Button>
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

  export default {
    components: {
      Loading, 
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        //表单对象
        formItem: {
          id: 0,
          seq:0,
          argGroup:'',
          argCode: '',
          argText: '',
          argRemark: '',
        },
        oriItem:{

        },
        //验证
        ruleValidate: {
          argCode: [
            {required: true, whitespace: true, message: '编码不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          argText: [
            {required: true, whitespace: true, message: '标题不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit === 0) {
          return "添加参数";
        } else {
          return "编辑参数";
        }
      }, 
    },
    methods: {
      save() { 
        this.$refs['argForm'].validate((valid) => {
          if (valid) { 
            this.saveArg();
          } else {
            return;
          }
        });
      },
      saveArg() {
        let url = '';
        let msg = '';
        if (this.isEdit) {
          url = '/api/arg/update';
          msg = '修改成功';
        } else {
          url = '/api/arg/add';
          msg = '添加成功';
        }

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
      open(arg) {
        this.show = true; 
        this.$refs['argForm'].resetFields(); 

        this.oriItem = $.extend({
          id: 0,
          sel:0,
          argGroup:'',
          argCode: '',
          argText: '',
          argRemark: ''},arg);

        if (arg.id) {
          this.isEdit = true; 
        } else {
          this.isEdit = false; 
        }

        $.extend(this.formItem,this.oriItem);
        this.loading = 0;
      },
      close() {
        this.show = false;
      },
      reset() {
        $.extend(this.formItem,this.oriItem);
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
