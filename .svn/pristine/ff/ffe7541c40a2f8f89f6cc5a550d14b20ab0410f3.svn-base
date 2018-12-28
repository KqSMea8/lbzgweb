<template>
  <Modal v-model="show" :closable="false" :mask-closable="false" title="历史单价" >
    <ListPage ref="page" 
    api="/api/providerAdjustPriceLog/list"
    :beforeLoad="beforeLoad" title=""
    :columns="columns" :model="this">
    sdfsdfsfsd
    </ListPage> 
    <div slot="footer">
      <Button type="primary" icon="checkmark" @click="close">关闭</Button>
    </div>
  </Modal>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import page from '@/assets/js/page';
  export default {
    components: {
      ListPage,
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '供应商编码',
            key: 'providerId',
            width: 120,
            fixed: 'left',
          },
          {
            title: '单价（元）',
            key: 'price',
            width: 100,
            align: 'center',
          },
        ],
        formItem:{
          providerId: '',
          materId:'',
        },
        queryForm:{

        },
        show: false,
        display:false
      }
    },
    mounted() {
      
    },
    computed: {},
    methods: {
      query() {
        this.$refs.page.query();
      },
      open(item) { 
        this.show = true; 
        Object.assign(this.formItem,item);
        this.query();
      },
      close() {
        this.show = false;
      },
      beforeLoad(){

      }
    }
  }
</script>

<style type="text/css">
  .inquirelist .status-2{
    color:#ff6600;
  } 
</style>
