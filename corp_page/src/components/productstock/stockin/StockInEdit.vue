<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page stockin-edit">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="合同编号" prop="contractId">
            <Input v-model="formItem.contractId" placeholder="" class="width-1" :maxlength="50"/>
          </FormItem>
          <FormItem label="客户" prop="customerName">
            {{formItem.customerName}}
          </FormItem>
           <FormItem label="物料编码" prop="materId">
            {{formItem.materId}}
          </FormItem>
          <FormItem label="物料名称" prop="materId">
               <SelMaterialInput class="width-1" 
              v-model="formItem.materId"
              @input-item="selectMater"
              :displayText="formItem.materName" 
              placeholder=""
              :type="2"
              :transfer="false"></SelMaterialInput>
          </FormItem>
          <FormItem label="图号" prop="drawing">
           {{formItem.drawing}}
          </FormItem>
          <FormItem label="铸件号" prop="casting">
            <Input v-model="formItem.casting" placeholder="" :maxlength="50"/>
          </FormItem>
          <FormItem label="材质" prop="texture">
            <Input v-model="formItem.texture" placeholder="" :maxlength="50"/>
          </FormItem>
          <FormItem label="数量" prop="quantity">
            <Input v-model="formItem.quantity" placeholder="" class="width-1" :maxlength="50"/>
          </FormItem>
          <FormItem label="重量" prop="totalWeight">
            <Input v-model="formItem.totalWeight" placeholder="" class="width-1" :maxlength="50"/>
          </FormItem>
          <FormItem label="仓库" prop="storageId">
            {{formItem.storageId}}
          </FormItem>
          <FormItem label="业务员" prop="proposer">
            <Input v-model="formItem.proposerName" :readonly="true"></Input>
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
  import SelMaterialInput from '@/components/material/selmaterial/SelMaterialInput';

  export default {
    components: {
      Loading,
      SelMaterialInput
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,
        industry: [],
        //表单对象
        formItem: {
          storageId:'100',
          contractId:'',
          customerId:'',
          customerName:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          totalWeight:'',
          createTime:'',
          proposer:'',
          proposerName:'',
        },
        oriItem:{
        },
        //验证
        ruleValidate: {
          contractId: [
            {required: true, whitespace: true, message: '合同编号不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          materId: [
            {required: true, whitespace: true, message: '物料不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          casting: [
            {required: true, whitespace: true, message: '铸件号不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑客户信息";
        } else {
          return "新建入库信息";
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
          url = '/api/customer/update';
          msg = '修改成功';
        } else {
          url = '/api/productStockIn/add';
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
      open(item) {
        this.show = true;
        this.$refs['form'].resetFields();

        if (item.customerId && item.customerId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
          contractId:'',
          customerId:'',
          customerName:'',
          materId:'',
          materName:'',
          drawing:'',
          casting:'',
          texture:'',
          quantity:'',
          totalWeight:'',
          storageId:'100',
          createTime:'',
          proposer:'',},item);
          if(this.$user.empInfo){
            this.oriItem.proposer = this.$user.empInfo.empId;
            this.oriItem.proposerName = this.$user.empInfo.trueName;
          }

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
        this.$http.post("/api/arg/list", {argGroup:'industry'}).then((res) => {
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
      selectMater(mater){
        if(mater != null){
          this.formItem.materId = mater.materId;
          this.formItem.materName = mater.materName;
          this.formItem.drawing = mater.drawing;
          this.formItem.customerId = mater.customerId;
          this.formItem.customerName = mater.customerName;
        }else{
          this.formItem.materId = '';
          this.formItem.materName = '';
          this.formItem.drawing = '';
          this.formItem.customerId = '';
          this.formItem.customerName = '';
        }
        this.$refs['form'].validateField('materId');
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
