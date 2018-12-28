<template>
  <Modal
    v-model="display"
    :title="title"
    :mask-closable="false"
    :closable="false"
    :footerHide="true">
    <div class="procbackmodal">
      <div class="procbackmodal-radio">
        <RadioGroup v-model="back">
          <Radio :label="1"> 
              <span>回退到发起人</span>
          </Radio>
          <Radio :label="2"> 
              <span>回退到上节点</span>
          </Radio>
        </RadioGroup>
      </div>
      <div>
        <Input v-model="remark" type="textarea" :rows="4" placeholder=""></Input>
      </div>
      <div class="procbackmodal-footer">
        <Button type="primary" @click="ok">确 定</Button>
        <Button @click="cancel">取 消</Button>
      </div> 
    </div>
  </Modal>
</template>
<script>

export default {
  components: { 
      
  },
  data() {
    var that = this;
    return { 
      loading:1,
      display:false,
      title:"回退流程",
      back:1,
      remark:'' 
    }
  },
  mounted:function(){ 
     
  },
  computed:{
    page:function(){
      return {
         
      };
    }
  },
  methods:{ 
    show(){
      this.display = true;
      this.remark = '';
    },
    ok:function(){
      this.display = false;
      this.$emit('ok');
    },
    cancel:function(){ 
      this.display = false;
      this.$emit('cancel');
    }
  }
}

</script>

<style type="text/css">
  .procbackmodalg{}
  .procbackmodal-radio{
    margin-bottom: 10px;
  }
  .procbackmodal-footer{
    margin-top: 10px;
    text-align: center;
  }
  .procbackmodal-footer .ivu-btn{
    margin: 10px;
  }
</style>