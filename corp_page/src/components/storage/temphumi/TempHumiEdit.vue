<template>
  <Modal v-model="show" :title="title" :mask-closable="false" :closable="false">
    <div class="admin-add-page">
      <Loading :loading="loading">
      <div class="page-form">
         <Form ref="form" :model="formItem" :label-width="100">
            <FormItem label="仓库代码" prop="storageId" >
                {{formItem.storageId}}
            </FormItem>
            <FormItem label="仓库名称" prop="name">
                <SelStorage v-model="formItem.storageId" style="width:150px;"> </SelStorage> 
            </FormItem>
            <FormItem label="温度" prop="temp">
                <Input  v-model="formItem.temp" style="width:150px"></Input>&nbsp;°C
            </FormItem>
            <FormItem label="湿度"  prop="humi">
                <Input  v-model="formItem.humi" style="width:150px"></Input>&nbsp;%
            </FormItem>
            <FormItem label="备注" prop="remark">
              <Input  v-model="formItem.remark"></Input>
           </FormItem>
           <FormItem id="clear">
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" >重置</Button>
            <Button type="ghost" @click="close" style="margin-left: 8px">取消</Button>
           </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <div slot="footer">
    </div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import SelStorage from '@/components/storage/input/SelStorage'; 
  export default {
    components: {
      Loading,
      SelStorage
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
          id:'',
          storageId:'',
          collTime:'',
          temp:'',
          humi:'',
          remark:''
        },
        oriItem:{
        },
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑温湿度台账";
        } else {
          return "新建温湿度台账";
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
          url = '/api/storageTemp/update';
          msg = '修改成功';
        } else {
          url = '/api/storageTemp/add';
          msg = '添加成功';
        }

        delete this.formItem.tmStamp;

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

        if (item.storageId && item.storageId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
          storageId:'',
          collTime:'',
          temp:'',
          humi:'',
          remark:''},item);
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
        this.$http.post("/api/storageTemp/list", {argGroup:'storageId'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.storageId = res.data.data;
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
