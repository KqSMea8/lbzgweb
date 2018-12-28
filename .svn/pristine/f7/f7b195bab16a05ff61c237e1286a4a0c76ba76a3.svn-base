<template>
  <ListPage ref="page" title="询价结果登记" api="/api/providerInquire/list" 
  :model="this" :beforeLoad="beforeLoad" class="customerlist">  
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button @click="add" v-power="'cggl.xjjgdj.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.providerName" placeholder="供应商名称"></Input>
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
    <EditPage ref="edit" @on-save="query"></EditPage>
  </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import EditPage from './EditPage';
  import page from '@/assets/js/page';

  export default {
    components: {
      ListPage,
      DataRowOperateBar, 
      EditPage
    },
    data() {
      let that = this;
      return {
        columns: [
          /*{
            title: '供应商代码',
            key: 'providerId',
            fixed:'left',
            width: 100,
          },*/
          {
            title: '名称',
            key: 'providerName', 
            fixed:'left',
            align: 'left',
             width: 150,
          },
          /*{
            title: '物料代码',
            key: 'materId', 
            fixed:'left',
            align: 'left',
            width: 120,
          },*/
          {
            title: '物料名称',
            key: 'materName', 
            fixed:'left',
            align: 'left',
            width: 120,
          },
          {
            title: '操作',
            key: '',
            width: 80,
            fixed:'left',
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.update(row);
                  }
                }
              },'编辑');
            }
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
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            width: 100,
            group: 'unit',
          }), 
          page.table.initArgColumn({
            title: '采购类别',
            key: 'subType',
            width: 100,
            group: 'material_subtype',
          }), 
          {
            title: '适用工序或产品',
            key: 'applyToProductType',
            width: 120,
            align: 'center',
          },
          {
            title: '单价（元）',
            key: 'price',
            width: 100,
            align: 'center',
          },
          {
            title: '交货期',
            key: 'deliveryDate', 
            align: 'left',
            width: 100,
          }, 
          {
            title: '付款方式',
            key: 'payType', 
            align: 'center',
            width: 100,
          }, 
          {
            title: '联系人',
            key: 'linkMan',
            width: 120,
            align: 'center',
          },
          {
            title: '地址',
            key: 'address',
            width: 200,
            align: 'left',
          },
          {
            title: '联系电话',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          }, 
          {
            title: '询价日期',
            key: 'inquireTime',
            width: 120,
            align: 'center',
          }, 
          {
            title: '备注',
            key: 'remark',
            width: 120,
            align: 'center',
          } 
        ],
        list: [],
        total:0,
        queryParam: {

        },
        queryForm: {
         providerName:''
        },
        industry:[],
        loading: 0
      }
    },
    mounted() {
      this.reset();
    },
    computed: {},
    methods: { 
      query() {
        this.$refs.page.query();
      }, 
      reset() {
        Object.assign(this.queryForm,{
          providerName:''
        }); 
        this.query();
      },  
      beforeLoad(){

      },
      rowCommand(name, params) {
        if (name === '编辑') {
          this.update(params.row);
          return;
        }

        if(name === '启用' || name === '禁用'){
          var status = 1;
          if(params.row.status === 1 ) {
             status = 2
        }
        this.$http.post('/api/provider/updateStatus', {
          providerId: params.row.providerId,
          status: status
          }).then((res) => {
              if (res.data.code === 0) {
                this.query();
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
         });
        }
      }, 
      
      add() {
        this.$refs.edit.open({id:0});
      }, 
      update(item) {
        this.$refs.edit.open(item);
      },
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css">
  .customerlist .status-2{
    color:#ff6600;
  }
</style>
