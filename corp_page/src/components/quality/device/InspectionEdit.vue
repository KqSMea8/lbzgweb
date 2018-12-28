<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customeredit">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            驳回原因
          </div>
          <FormItem label="供应商代码" prop="pmapId">
            <Input v-model="formItem.pmapId" type="textarea" :rows="3"></Input>
          </FormItem>
          <FormItem label="驳回人员" prop="providerName">
            <Input v-model="formItem.providerName" placeholder=""  readonly="readonly" class="width-1"/>
          </FormItem>
          <FormItem label="驳回日期" prop="createTime">
            <Input v-model="formItem.createTime" placeholder=""  readonly="readonly" class="width-1"/>
          </FormItem>


          <FormItem>
<!--            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" >重置</Button>-->
            <Button type="ghost" @click="close" style="margin-left: 8px">关闭</Button>
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
  import SelArea from '@/components/selarea';//区域插件

  export default {
    components: {
      Loading, SelArea,  //声明新组件
    },
    data() {
      return {
        loading: 0,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        industry: [],
        //表单对象
        formItem: {
          providerId: '',
          providerName: '',
          address: '',
          invoiceAddress: '',
          bank: '',
          bankCardNo: '',
          faxNo: '',
          linkMan: '',
          linkPhone: '',
          taxNo: '',
          legalPerson: '',
          postcode: '',
          industry: '',
          area :''
        },
        oriItem: {},
        //验证
        ruleValidate: {
          providerId: [
            {required: true, whitespace: true, message: '代码不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          providerName: [
            {required: true, whitespace: true, message: '名称不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      };
    },
    computed: {
      title() {
          return "外检申请单驳回原因";
      },
    },
    mounted(){
      this.loadGroups()
    },
    methods: {
      save() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            this.saveItem();
          } else {
            return;
          }
        });
      },

      saveItem() {
        let url = '';
        let msg = '';
        if (this.isEdit) {
          url = '/api/provider/update';
          msg = '修改成功';
        } else {
          url = '/api/provider/add';
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

      open(item) {//判断编辑页面，新建页面
        this.show = true;
        //this.$refs['form'].resetFields();

        if (item.providerId && item.providerId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        }
        console.log(this.oriItem);
        Object.assign(this.formItem,this.oriItem);
      },

      close() {
        this.show = false;
      },

      reset() {
        $.extend(this.formItem,this.oriItem);
      },

      loadGroups() {
//        this.loading = 1;
//        //查询角色列表数据
//        this.$http.post("/api/arg/list", {}).then((res) => {
//          this.loading = 0;
//          if (res.data.code === 0) {
//            this.industry = res.data.data;
//          } else {
//            this.$Message.error(res.data.message);
//          }
//        }).catch((error) => {
//          this.loading = 0;
//          this.$Message.error("请求失败，请重新发送")
//        });
        this.industry = this.$args.argMap['industry'];
      },
    }
  }

</script>

<style type="text/css">
  .form-sub-title {
    font-size: 14px;
    margin: 0 0 12px 18px;
    color: #2b85e4;
  }

  .customeredit .width-1{
    width: 160px;
  }
</style>
