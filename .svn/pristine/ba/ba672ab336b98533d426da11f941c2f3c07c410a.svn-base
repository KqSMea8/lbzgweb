<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customeredit">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="库存操作代码" prop="operId">
            <Input v-model="formItem.operId" placeholder="" :disabled="isEdit" class="width-1" />
          </FormItem>
          <FormItem label="库存操作名称" prop="name">
            <Input v-model="formItem.name" placeholder=""  class="width-1"/>
          </FormItem>

          <FormItem label="操作类型" prop="type">
            <RadioGroup v-model="formItem.type">
             <Radio :label="1">入</Radio>&nbsp;&nbsp;&nbsp;&nbsp;
             <Radio :label="2">出</Radio>&nbsp;&nbsp;&nbsp;&nbsp;
             <Radio :label="3">移</Radio>&nbsp;&nbsp;&nbsp;
             <Radio :label="4">调</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="操作来源" prop="source">
            <RadioGroup v-model="formItem.source">
             <Radio :label="1">采购</Radio>
             <Radio :label="2">生产</Radio>
             <Radio :label="3">库存</Radio>
             <Radio :label="4">销售</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem>
             <Checkbox  :true-value="1" :false-value="0" v-model="formItem.reject">退货</Checkbox>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <Checkbox  :true-value="1" :false-value="0" v-model="formItem.repair">返修</Checkbox>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px">重置</Button>
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
      Loading, SelArea
    },
    data() {
      return {
        loading: 0,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,

        // departmentName: [],//所属部门
        // typeName: [],//类型名称

        //表单对象
        formItem: {
          operId:'',
          name:'',
          type:'',
          source:'',
          reject:'',
          repair:'',
        },
        oriItem:{
        },
        //验证
        ruleValidate: {
          operId: [
            {required: true, whitespace: true, message: '编码不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          name: [
            {required: true, whitespace: true, message: '名称不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑库存类别信息";
        } else {
          return "新建库存类别信息";
        }
      },
    },
    mounted(){
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
          url = '/api/storage/stockOpertype/update';
          msg = '修改成功';
        } else {
          url = '/api/storage/stockOpertype/add';
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

        if (item.operId && item.operId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
            operId:'',
            name:'',
            type:1,
            source:1,
            reject:0,
            repair:0,},item);
        }
        Object.assign(this.formItem,this.oriItem);
      },
      close() {
        this.show = false;
      },
      reset() {
        $.extend(this.formItem,this.oriItem);
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
