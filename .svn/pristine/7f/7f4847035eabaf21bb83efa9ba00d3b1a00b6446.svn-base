<template>
<ListPage ref="page" title="客户管理" api="/api/customer/list" 
  :model="this" :beforeLoad="beforeLoad">
   <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button @click="add" v-power="'xsht.kh.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.keyword" placeholder="客户代码、名称查询" ></Input>
          </td>
          <td>
            <Select v-model="queryForm.industry" style="width:150px" placeholder="所属行业">
              <Option value="">所属行业</Option>
              <Option v-for="item in $args.getArgGroup('industry')" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
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

  <CustomerEdit ref="edit" @on-save="query"></CustomerEdit>
  <CustomerView ref="view"></CustomerView>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import CustomerEdit from './CustomerEdit';
  import CustomerView from './CustomerView';
  import page from '@/assets/js/page';

  export default {
    components: {
      DataRowOperateBar,
      Loading,
      CustomerEdit,
      CustomerView,
      ListPage
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '客户代码',
            key: 'customerId',
            width: 120,
            render:(h,params)=>{
              var row = params.row;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$refs.view.open(row);
                    this.$refs.view.clickEdit = ()=>{
                      this.update(row);
                    }
                  }
                }
              },row.customerId);
            }
          },
          page.table.customerNameColumn({
            
          }),
          /*{
            title: '地址',
            key: 'address',
            align: 'left',
            minWidth:140,
          },*/
          {
            title: '联系人',
            key: 'linkMan',
            width: 80,
            align: 'center',
          },
          {
            title: '联系电话',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '传真号码',
            key: 'faxNo',
            width: 140,
            align: 'center',
          },
          {
            title: '状态',
            key: 'status',
            align: 'center',
            width:60,
            render:(h,params)=>{
              var status = params.row.status;
              var setButton = "正常";
              if (status == 2) {
                setButton = "禁用";
              }
              return h('span',{class:'status-'+status},setButton);
            }
          },
          {
            title: '操作',
            width: 80,
            align: 'center',
            render(h, params) {
              let status = params.row.status;
              let setButton = "禁用";
              if (status === 2) {
                setButton = "启用";
              }
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton + ',编辑,删除'
                },
                on: {
                  'on-command': (name) => {
                    that.rowCommand(name, params)
                  }
                }
              });
            } 
          }
        ], 
        queryForm: {
          keyword: '',
          industry:'',
        }, 
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
      beforeLoad(){

      },
      reset() {
        Object.assign(this.queryForm,{
          keyword: '',
          industry:'',
        });
        this.query();
      },
      rowCommand(name, params) {
        if(name === '启用' || name === '禁用'){
          if(!this.$user.hasPower('xsht.kh.disable')){
            this.$Message.error('暂无权限！');
            return;
          }
          var status = 1;
          if(params.row.status === 1 ) {
             status = 2
          }
          this.$http.post('/api/customer/update', {
            customerId: params.row.customerId,
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
        if (name === '编辑') {
          if(!this.$user.hasPower('xsht.kh.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          if(!this.$user.hasPower('xsht.kh.del')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = params.row.customerId;
              this.loading = 1;
              this.$http.post('/api/customer/delete?customerId=' + aid, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  this.$refs.page.load();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.toString())
              });
            }
          });
        }
        if (name === '查看') { 
          this.$refs.view.$off('on-edit'); 
          this.$refs.view.open(row);
          this.$refs.view.clickEdit = ()=>{
            this.update(params.row);
          }
        }
      }, 
      add() {
        this.$refs.edit.open({customerId:''});
      },
      update(item) {
        this.$refs.edit.open(item);
      }
    }
  }

</script>

<style type="text/css">
  .customerlist .status-2{
    color:#ff6600;
  }
</style>
