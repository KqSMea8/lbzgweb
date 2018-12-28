<template>
   <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div lass="page">
        <Loading :loading="loading">
        <div class="page-form">
          <Form ref="form" :model="item" :rules="ruleValidate" :label-width="80">
                <FormItem label="部门名称" prop="deptName">
                    <Input size="small" v-model="item.deptName" placeholder="请输入部门名称"></Input>
                </FormItem>
                <FormItem>
                     <Button  type="primary" @click="save">保存</Button>
                     <Button  type="ghost" @click="cancel" style="margin-left: 8px">取消</Button>
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

export default {
  components: {
     Loading
  },
  data() {
    return {
      loading:0,
      show:false,
      item:{
        deptName:''
      },
      title:'新建部门',
      isEdit:0,
      ruleValidate: {
        deptName: [
          {required:true,whitespace: true,message: '部门名称不能为空', trigger: 'blur'},
          {type: 'string', max: 30, message: '不能超过30个字', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    open(isEdit,deptName) {
      this.$refs['form'].resetFields();
      this.show = true;
      this.isEdit = isEdit;
      this.loading = 1;
      if(isEdit===1){
        this.title = '修改部门名称';
        this.item.deptName = deptName;
        this.loading = 0;
      }else{
        this.title = '新建部门';
        this.loading = 0;
      }
    },
    cancel(){
      this.show = false;
    },
    save(){
       this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = 1;
          this.show = false;
          if(this.isEdit){
            this.$emit('on-submit-edit',this.item.deptName);
          }
          else{
            this.$emit('on-submit',this.item.deptName);
          }
       }else{
          return;
       }
    });
  }
}
}

</script>

<style type="text/css">

</style>
