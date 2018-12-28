<template>
  <div class="page chgpwd">
    <div class="page-bar"><Icon type="document"></Icon> 修改密码</div>
    <Loading :loading="loading">
      <Form label-width="80" class="chgpwd-from">
          <Form-item label="旧密码" prop="trueName">
            <Input  placeholder="" size="small"/>
          </Form-item>

          <Form-item label="新密码" prop="trueName">
            <Input  placeholder="" size="small"/>
          </Form-item>

          <Form-item label="确认密码" prop="trueName">
            <Input  placeholder="" size="small"/>
          </Form-item>

          <Form-item label="">
            <Button class="btn-save" type="primary" @click="save">确定</Button>
          </Form-item>
      </Form>
    </Loading>
  </div>
</template>
<script>
import Loading from '@/components/loading';

export default {
  components: {
    Loading
  },
  data() {
    var that = this;
    return {
      loading:0
    }
  },
  mounted:function(){
    this.query();
  },
  computed:{

  },
  methods:{
    load: function() {

    },
    save:function(){
    }
  }
}

</script>

<style type="text/css">
  /*通用样式*/
  .page{
    padding: 10px;
  }
  .page-bar{
    height: 42;
    line-height: 41px;
    padding: 0px 10px;
    margin: -10px -10px 0px -10px;
    font-size: 14px;
    border-bottom: 1px solid #eee;
    cursor: default;
    background-color: #F9FAFB;
  }
  .chgpwd-from{
    width: 340px;
    margin: auto;
    margin-top: 40px;
  }

  .chgpwd .btn-save{
    width: 100px;
  }
</style>
