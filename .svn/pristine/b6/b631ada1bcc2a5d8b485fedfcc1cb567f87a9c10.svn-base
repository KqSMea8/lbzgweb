<template>
<ListPage ref="page" title="询价单" api="/api/inquire/list" :model="this">
  <div class="page-searchbox">
    <table cellpadding="0" cellspacing="0">
      <tr> 
        <td>
           <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
            <Radio :label="1">已提交</Radio>
            <Radio :label="2">已反馈</Radio>
          </RadioGroup>
        </td>
        <td class="page-tools"><Button @click="add" v-power="'xsht.xjd.add'" icon="plus">新建</Button></td>
      </tr>
    </table> 
  </div>
  <div class="page-searchbox">
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td>
          <Input v-model="queryForm.inquireId" placeholder="询价单编号" ></Input>
        </td>
        <td>
          <Button @click="query"  type="primary" icon="ios-search">查询</Button>
        </td>
        <td>
          <Button @click="reset" >重置</Button>
        </td>
      </tr>
    </table> 
  </div> 
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar'
  import page from '@/assets/js/page';

  export default {
    components: {
      DataRowOperateBar,
      ListPage
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '询价单编号',
            key: 'inquireId',
            width: 120,
            render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/inquire/view?forward&id='+row.inquireId});
                  }
                }
              },row.inquireId);
            }
          },
          page.table.initDateColumn({
            title: '录入日期',
            key: 'inDate',
          }),
          page.table.customerNameColumn({
            
          }),
          {
            title: '需求询价',
            key: 'isInquire',
            width: 100,
            align: 'center',
            render:(h, params)=>{
              //<Icon type="checkmark"></Icon>
              if(params.row.isInquire){
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
          {
            title: '含加工工序',
            key: 'hasProcedure',
            width: 100,
            align: 'center',
            render:(h, params)=>{
              if(params.row.hasProcedure){
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
          {
            title: '外销',
            key: 'isForeign',
            width: 80,
            align: 'center',
            render:(h, params)=>{
              if(params.row.isForeign){
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
          {
            title: '工艺设计',
            key: 'isCraftDesign',
            width: 100,
            align: 'center',
            render:(h, params)=>{
              if(params.row.isCraftDesign){
                return h('Icon',{
                    props: {
                      type:'checkmark'
                    }
                });
              }
            }
          },
          {
            title: '业务员',
            key: 'salesName',
            width: 100,
            align: 'center',
          },
          {
            title: '工艺员',
            key: 'designerName',
            width: 100,
            align: 'center',
          }, 
        ],
        queryForm: {
          inquireId: null,
          status:1,
        },
      }
    },
    mounted: function () {
      this.query();
    },
    computed: {},
    methods: { 
      query: function () {
        this.$refs.page.query();
      }, 
      reset: function () {
        Object.assign(this.queryForm,{
          inquireId: '',
          industry:'',
          status:1,
        });
        this.query();
      },
      add: function () {
        this.$router.push({path:'/inquire/edit?forward'})
      }
    },
    activated(){
      this.$refs.page.load();
    },
  }

</script>