<template>
<i-table :columns="columns" :data="list" :height="height" :loading="loading==1"></i-table>
</template>
<script>
  // 简单列表组件，适合显示单据明细，配合列表查询页面使用
  // 默认以单据对象中的 detailList 字段为数据源
  export default {
    components: {
    },
    props:{
      api:{
        type:String,
        default:''
      },
      columns:{
        type:Array,
        default:()=>{return [];}
      }
    },
    data() {
      return {
        list: [],
        loading:0,
        height:100,
      }
    },
    mounted: function () {

    },
    computed: {},
    methods: {
      load(objId) {
        this.loading = 1;
        this.$http.post(this.api + objId).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            this.list = res.data.data;
          } else {
            this.list = [];
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      clear(){
        this.list = [];
      }
    },
  }

</script>