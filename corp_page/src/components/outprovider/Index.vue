<template>
  <ListPage ref="page" title="外委厂商" api="/api/outprovider/query" 
  :model="this" :beforeLoad="beforeLoad" class="outProviderList">  
  <div class="page-tools">
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td><Button @click="add" v-power="'cggl.gys.add'" icon="plus">新建</Button></td>
      </tr>
    </table>
  </div>
  <div class="page-searchbox">
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td>
          <Input v-model="queryForm.providerName" placeholder="外委厂商名称"></Input>
        </td>
        <td>
         <Select v-model="queryForm.type" style="width:150px" placeholder="类别" @on-change="switType">
          <Option v-for="item in getTypes()" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
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
<OutproviderEdit ref="edit" @on-save="query"></OutproviderEdit>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import OutproviderEdit from './OutproviderEdit';
  import page from '@/assets/js/page';
  export default {
    components: {
      ListPage,
      DataRowOperateBar, 
      OutproviderEdit,
      page
    },
    data() {
      let that = this;
      return {
        columns: [
        {
          title: '外委厂商代码',
          key: 'providerId',
          width: 200,
        },
        page.table.outProviderName({

        }),
        page.table.initMapColumn({
         title: '类别',
         key: 'type',
         width: 100,
         data:{
          'jjg':'机加工厂',
          'rcl':'热处理厂',
          'mx':'模型厂',
        }
      }), 
        {
          title: '联系人',
          key: 'linkMan',
          align: 'center',
          width: 100,
        },           
        {
          title: '联系电话',
          key: 'linkPhone',
          align: 'center',
          width: 200,
        }, 
        page.table.initMapColumn({
          title: '状态',
          key: 'status',
          width: 100,
          data:{
            '1':'正常',
            '2':'禁用',
          }
        }),
        {
            title: '操作',//操作按钮列表
            width: 100,
            align: 'center',
            render(h, params) {
              let status = params.row.status;
              let setButton = "禁用";
              if (status === 2) {
                setButton = "启用";
              }
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton + ',编辑'
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
          list: [],
          total:0,
          queryParam: {},
          queryForm: {
            providerName: '',
            type:this.$user.config.data.type,
          },
          industry:[], 
          loading: 0
        }
      },
      mounted() {
        this.reset();
      },
      computed: { 
      },
      methods: { 
        query() {
          if(!this.queryForm.type){
          this.$Message.error('请选择类别');
          return;
        }
          this.$refs.page.query();
        }, 
        reset() {
          Object.assign(this.queryForm,{
            providerName: '',
          }); 
          this.query();
        },  
        beforeLoad(){

        },
        switType(){
        this.$user.config.data.type = this.queryForm.type;
        this.reset();
      }, 
        rowCommand(name, params) {
          if (name === '编辑') {
            if(!this.$user.hasPower('cggl.gys.edit')){
              this.$Message.error('暂无权限！');
              return;
            }

            this.update(params.row);
            return;
          }

          if(name === '启用' || name === '禁用'){
            if(!this.$user.hasPower('cggl.gys.disable')){
              this.$Message.error('暂无权限！');
              return;
            }

            var status = 1;
            if(params.row.status === 1 ) {
              status = 2
            }
            this.$http.post('/api/outprovider/update', {
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
          this.$refs.edit.open({});
        }, 
        update(item) {
          this.$refs.edit.open(item);
        },
        goPage(url){
          this.$router.push(url);
        },
        getTypes(){
          var all = this.$args.argMap['out_provider_type'];
          var types = [];
          all.map((type)=>{
            if(this.$user.hasPower('wwjg.wwcs.data.'+type.argCode))
            {
              types.push(type);
            }
          })
          return types;
        }
      }
    }

  </script>

  <style type="text/css">
  .outProviderList .status-2{
    color:#ff6600;
  }
</style>
