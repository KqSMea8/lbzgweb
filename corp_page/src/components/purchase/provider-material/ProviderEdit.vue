<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customeredit">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
           <FormItem label="供应商代码" prop="providerId">
            <Input v-model="formItem.providerId" placeholder="" :disabled="isEdit" class="width-1" />
          </FormItem>
          <FormItem label="物料代码" prop="materId">
            <Input v-model="formItem.materId" placeholder="" :disabled="isEdit" class="width-1"/>
          </FormItem>
          <FormItem label="物料名称" prop="materName">
            <Input v-model="formItem.materName" placeholder="" :disabled="isEdit" class="width-1" />
          </FormItem>
          <FormItem label="物料类别" prop="materType">
             <Select v-model="formItem.materType" style="width:150px" placeholder="物料类别">
              <Option :value="'A'">A 类</Option>
              <Option :value="'B'">B 类</Option>
              <Option :value="'C'">C 类</Option>
            </Select>
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
  import SelArea from '@/components/selarea';

  export default {
    components: {
      Loading, SelArea,  //声明新组件
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        //表单对象
        formItem: {
          providerId:'',
          materId:'',
          materName:'',
          materType:'',
        },
        oriItem:{
        },
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑供应商物料信息";
        } else {
          return "新建供应商物料信息";
        }
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
          url = '/api/providerMaterial/materType/update';
          msg = '修改成功';
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
        this.$refs['form'].resetFields();
        if (item.providerId && item.providerId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
            providerId:'',
            materId:'',
            materName:'',
            materType:'',
            },item);
        }
        Object.assign(this.formItem,this.oriItem);
      },

      close() {
        this.show = false;
      },
      
      reset() {
        $.extend(this.formItem,this.oriItem);
      },
      
      loadGroups() {
        this.loading = 1;
        //查询角色列表数据
        this.$http.post("/api/arg/list", {}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.industry = res.data.data;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
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
