<template>
   <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page"> 
        <div class="page-form">
          <Form ref="form" :model="item" :rules="ruleValidate" :label-width="80">
            <FormItem label="撤消原因" prop="remark">
                <Input size="small" v-model="item.remark" placeholder=""  type="textarea"></Input>
            </FormItem>
            <FormItem>
                 <Button  type="primary" @click="ok">确定</Button>
                 <Button  type="ghost" @click="cancel" style="margin-left: 8px">取消</Button>
            </FormItem>
          </Form>
        </div> 
    </div>
    <div slot="footer"></div>
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
      show:false,
      item:{
        remark:''
      },
      title:'确认撤销',
      isEdit:0,
      ruleValidate: {
         
      }
    }
  },
  methods:{
    open() {
      this.$refs['form'].resetFields();
      this.show = true;
    },
    cancel(){
      this.show = false;
    },
    ok(){
       this.$refs['form'].validate((valid) => {
          if (valid) { 
            this.show = false;
            this.$emit('on-ok',this.item.remark);
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
