<template>
  <i-table :columns="columns" :data="list" :height="tableHeight" :loading="loading == 1" border ></i-table>
</template>
<script>
  export default {
    components: {  
    },
    props:{
      billId:{
        type:String,
        default:''
      }
    },
    data() {
      let that = this;
      return {
        tableHeight:100,
        columns: [
          {
            title:'#', 
            fixed: 'left',
            width:50,
            align:'center',
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '物料代码',
            key: 'materId',
            fixed: 'left',
            align: 'left',
            width: 120,
          },
          {
            title: '物料名称',
            key: 'materName',
            fixed: 'left',
            align: 'left',
            width: 120,
          },
          {
            title: '规格',
            key: 'spec',
            align: 'center',
            width: 120,
          },
          {
            title: '型号',
            key: 'model',
            align: 'center',
            width: 120,
          },
          {
            title: '批次号',
            key: 'materId',
            align: 'center',
            width: 120,
          },
          {
            title: '生产日期',
            key: 'madeDate',
            align: 'center',
            width: 120,
            render:(h, params)=>{
              var row = params.row;
              return h('span', row.madeDate && row.madeDate.length>=10?row.madeDate.substring(0,10):row.madeDate);
            }
          },
          {
            title: '货位',
            key: 'placeId',
            align: 'left',
            width:150
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'left',
            width:150
          },
          {
            title: '到厂日期',
            key: 'arrivalDate',
            align: 'left',
            width:150
          },
          {
            title: '有效日期',
            key: 'validityDate',
            align: 'left',
            width:150
          },
          {
            title: '质保期',
            key: 'qualityTime',
            align: 'left',
            width:150
          },
          {
            title: '单位',
            key: 'unit',
            align: 'left',
            width:150
          },
          {
            title: '单价',
            key: 'price',
            align: 'left',
            width:150
          },
          {
            title: '金额',
            key: 'amount',
            align: 'right',
            width:100,
          }, 
          {
            title: '备注',
            key: 'amount',
            align: 'left',
            width:100,
          },
        ],
        list: [],
        inStockId:'',
        loading: 0
      }
    },
    mounted: function () { 
      
    },
    computed: {
    }, 
    watch:{
      billId(val,old){ 
        window.clearTimeout(this.handle);
        this.handle = 
        window.setTimeout(()=>{ 
          this.load(val);
        },20);
      }
    },
    methods: { 
      load(inStockId) { 
        if(inStockId){
          this.loading = 1;
          this.$http.post("/api/inStock/get?inStockId=" + inStockId).then((res) => {
            this.loading = 0;
            this.list = [];
            if (res.data.code === 0) { 
              if(res.data.data != null){
                this.list = res.data.data.detailList;
              }
            } else {
              this.$Message.error(res.data.message);
            }
          }).catch((error) => {
            this.loading = 0;
            this.$Message.error("请求失败，请重新发送")
          });
        }else{
          this.list = [];
        }
      },
    }
  }

</script>

<style type="text/css">
   
</style>
