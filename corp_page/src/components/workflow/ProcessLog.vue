<template>
  <Modal
    v-model="show"
    title="日志"
    :mask-closable="false"
    :closable="false"
    :width = "680">
    <div :class="page">
      <div class="page-ping">
        <Spin fix size="large" ></Spin>
      </div>
      <div class="page-datatable">
          <i-table :columns="columns" :data="list" ></i-table>
          <div style="height:10px;"></div>
       </div>
    </div>
    <div slot="footer">
        <Button type="primary" @click="cancel">关闭</Button>
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
      list:[],
      columns:[
        {
            title: '序号',
            width: 80,
            type:'index'
          },
           {
            title: '操作人',
            key: 'operEmpName',
            align: 'center',
            width: 80
          },
          {
            title: '操作类型',
            key: 'operTypeName',
            width: 120,
            align: 'center',
          },
          {
            title: '操作描述',
            key: 'operRemark',
            align: 'center'
          },
          {
            title: '操作时间',
            key: 'createTime',
            align: 'center',
            width: 130
          }
      ]

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
      this.list = [];
      this.id = id;
      this.load(id);
      this.show = true;
    },
    load:function(id){
      this.$http.post('/api/wf/process/processLog',{procInstId:id}).then((res) => {
          if (res.data.code === 0) {
              this.list = res.data.data;
              console.log(this.list);
          } else {
              this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        })
    },
    cancel:function(){
      this.show = false;
    }
  }
}

</script>

<style type="text/css">

</style>
