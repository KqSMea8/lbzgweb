<template>
  <Modal
    v-model="show"
    :title="title"
    :mask-closable="false"
    :closable="false">
    <div :class="page">
      <div class="page-ping">
        <Spin fix size="large" ></Spin>
      </div>
      <div class="page-form">
         <Form ref="formItem" :model="item" :label-width="100">
            <FormItem label="申请人" prop="title">
                <Input v-model="item.title" placeholder="不超过30个字符"></Input>
            </FormItem>
            <FormItem label="开始时间" prop="title">
                <Input v-model="item.beginTime" placeholder="不超过30个字符"></Input>
            </FormItem>
            <FormItem label="结束时间" prop="title">
                <Input v-model="item.endTime" placeholder="不超过30个字符"></Input>
            </FormItem>
             <FormItem label="申请原因" prop="description">
                <Input v-model="item.dec"  type="textarea" :rows="4" placeholder="不超过200个字符"></Input>
            </FormItem>
            <FormItem>
                <Button type="primary" @click="send">立即发送</Button>
                <Button type="primary" @click="save">保存待发</Button>
                <Button type="primary">查看流程</Button>
                <Button type="text" @click="cancel">取消</Button>
            </FormItem>
          </Form>
      </div>
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
      id:null,
      loading:0,
      show:false,
      title:"请假单",
      item:{
        id:'',
        title:'',
        titleFormat:'',
        content:'',
        status:''
      },
      ruleValidate: {
          title: [
              { required: true, message: '类型名称不能为空', trigger: 'blur' },
              { type: 'string', max: 30, message: '不能超过30个字', trigger: 'blur' }
          ],
          titleFormat: [
              { required: true, message: '类型标题不能为空', trigger: 'blur' }
          ],
          content: [
              { required: true, message: '内容不能为空', trigger: 'blur' }
          ],
          status: [
              { required: true, message: '请选择状态' }
          ],
          isInit: [
              { required: true, message: '请选择是否默认'}
          ],

      }
    }
  },
  mounted:function(){

  },
  computed:{
    page:function(){
      return {
        'page':1,
        'loading':this.loading
      };
    }
  },
  methods:{
    open: function(id) {
      this.show = true;
      this.id = id;
      if(id > 0){
        this.load();
      }
    },
    load:function(){
      this.loading = 0;
      this.item = {
         id:this.id,
         title:'请假单',
         beginTime:'2018-05-14',
         endTime:'2018-05-14',
         dec:'请假理由'
       }
    },
    save:function(){
      this.show = false;
      this.$emit('on-save');
    },
    send:function(){

    },
    reset:function(){
      this.load();
    },
    cancel:function(){
      this.show = false;
      this.$emit('on-cancel');
    }
  }
}

</script>

<style type="text/css">

</style>
