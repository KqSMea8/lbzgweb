<template>
  <ListPage ref="page" title="" api="/api/storage/stock/list" 
  :model="this" class="customerlist">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;仓库初始化
    </div>
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Select v-model="queryForm.storageId" style="width:150px" placeholder="仓库" @on-change="switStorage">
              <Option v-for="(item,index) in storage" :value="item.storageId" :key="index">{{ item.name }}</Option>
            </Select>
          </td>
          <td><Button @click="add" v-power="'ckgl.ckcsh.add'" icon="plus">添加</Button></td>
          <td><Button @click="importExcel" v-power="'ckgl.ckcsh.import'" icon="upload">导入</Button></td>
          <td>&nbsp;&nbsp;</td>
          <td><Button @click="complate" v-power="'ckgl.ckcsh.init'" icon="checkmark">完成初始化</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.keyword" placeholder="物料名称、物料编码、批号" style="width:220px;" ></Input>
          </td>
          <td>
            <SelPlaceInput ref="selplace"
              v-model="queryForm.placeId"
              :storageId="queryForm.storageId"
              :displayText="queryForm.placeName"
              @input-item="queryForm.placeName=arguments[0].placeName"
            ></SelPlaceInput>
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
    <StockEdit ref="edit" @on-save="query"></StockEdit>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import StockEdit from './StockEdit';
  import SelPlaceInput from '@/components/storage/selplace/SelPlaceInput';
  import page from '@/assets/js/page';

  export default {
    components: {
      ListPage,
      DataRowOperateBar,
      Loading,
      StockEdit,
      SelPlaceInput
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '物料编码',
            key: 'materId',
            align: 'left'
          },
          {
            title: '物料名称',
            key: 'materName',
            align: 'left',
          },
          {
            title: '规格',
            key: 'spec',
            width: 80,
            align: 'center',
          },
          {
            title: '型号',
            key: 'model',
            width: 80,
            align: 'center',
          },
          {
            title: '批次',
            key: 'batch',
            width: 80,
            align: 'center',
          },
          {
            title: '货位',
            key: 'placeName',
            width: 140,
            align: 'center',
          },
          {
            title: '数量',
            key: 'quantity',
            width: 80,
            align: 'center',
          }, 
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            width: 80,
            group: 'unit',
            align:'center'
          }), 
          {
            title: '单价',
//            key: 'price',
            width: 80,
            align: 'center',
            render: function(h,params){
                var price = params.row.price;
                return h('span', parseFloat(price).toFixed(3));
            }
          },
          {
            title: '金额',
//            key: 'amount',
            width: 80,
            align: 'center',
            render: function(h,params){
              var amount = params.row.amount;
              return h('span', parseFloat(amount).toFixed(3));
            }
          },
          {
            title: '操作',
            width: 80,
            align: 'center',
            render: function (h, params) {
              return h(DataRowOperateBar, {
                props: {
                  commands:'编辑,删除'
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
          storageId:'',
          keyword: '',
          placeId:'',
          placeName:''
        },
        storage:[],
        loading: 0
      }
    },
    mounted: function () {
      this.loadStorage();  
      this.reset();
    },
    computed: {},
    methods: {
      loadStorage: function () {
        this.loading = 1;
        this.$http.post("/api/storage/listAll", {}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            var data = res.data.data;
            var rows = [];
            data.map((item)=>{
              if(item.initStatus == 0){
                rows.push(item);
              }
            });
            this.storage = rows;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
      switStorage(){
        this.reset();
      },
      query () {
        if(this.queryForm.storageId == ''){
          this.$Message.error('请选择仓库');
          return;
        }
        this.$refs.page.query();
      },
      reset: function () {
        Object.assign(this.queryForm,{
          keyword: '',
          placeId:'',
          placeName:'',
        });
        this.query();
      }, 
      rowCommand (name, params) {
        if(name === '启用' || name === '禁用'){
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
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var storageId = params.row.storageId;
              var placeId = params.row.placeId;
              var materId = params.row.materId;
              var batch = params.row.batch;
              this.loading = 1;
              this.$http.post('/api/storage/stock/delete?'
                + 'storageId=' + storageId
                + '&placeId=' + placeId
                + '&materId=' + materId
                + '&batch=' + batch, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  this.load();
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
      },
      goBack: function () {
        this.$router.go(-1);
      }, 
      add: function () {
        if(this.queryForm.storageId != '--'){
          this.$refs.edit.open({storageId:this.queryForm.storageId});
        }else{
          this.$Message.error('请选择仓库');
        }
      },
      update: function (item) {
        item.id = 1;
        this.$refs.edit.open(item);
      },
      importExcel(){

      },
      complate(){
        if(this.queryForm.storageId == '--'){
          this.$Message.error('请选择仓库');
          return;
        }

        this.$Modal.confirm({
          title: '确认完成仓库初始化',
          content: '<p>完成仓库初始化后不可修改，确定完成操作吗？</p>',
          onOk: () => {
            this.loading = 1;
            var url = '/api/storage/update';
            var form = {
              storageId:this.queryForm.storageId,
              initStatus:1
            };
            this.$http.post(url, form).then((res) => {
              this.loading = 0;
              if (res.data.code === 0) {
                this.$Message.success('操作成功');
                this.queryForm.storageId = '--';
                this.loadStorage();
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.message)
            });
          }
        });
      },
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css">
   
</style>
