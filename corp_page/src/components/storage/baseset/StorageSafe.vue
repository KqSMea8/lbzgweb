<template>
  <div class="page customerlist">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;安全库存
    </div> 
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
        <td>
            <Select v-model="queryForm.storageId" style="width:150px" placeholder="仓库" @on-change="switStorage">
              <Option v-for="(item,index) in storage" :value="item.storageId" :key="index">{{ item.name }}</Option>
            </Select>
          </td>
          <td>&nbsp;&nbsp;</td>
          <td><Button @click="add" v-power="'ckgl.aqkc.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0"> 
        <tr>
          
          <td>
            <Input v-model="queryForm.materName" placeholder="物料名称" style="width:220px;" ></Input>
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
    <Loading :loading="loading">
    <div class="page-datatable">
      <i-table :columns="columns" :data="list"></i-table>
      <div style="height:10px;"></div>
      <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top"></Page> 
    </div>
    </Loading> 
    <StorageSafeEdit ref="edit" @on-save="query"></StorageSafeEdit> 
  </div>
</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import StorageSafeEdit from './StorageSafeEdit';
  
  export default {
    components: {
      DataRowOperateBar,
      Loading,
      StorageSafeEdit, 
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
            width: 100,
            align: 'center',
          },
          {
            title: '型号',
            key: 'model',
            width: 100,
            align: 'center',
          },
          {
            title: '最小库存量',
            key: 'minQuantity',
            width: 120,
            align: 'center',
          },
          {
            title: '安全库存量',
            key: 'safeQuantity',
            width: 120,
            align: 'center',
          },
          {
            title: '最大库存量',
            key: 'maxQuantity',
            width: 120,
            align: 'center',
          },
          {
            title: '单位',
            key: 'unitName',
            width: 80,
            align: 'center'
          },
          {
            title: '备注',
            key: '备注',
            align: 'center'
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
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          storageId:'',
          materName: ''
          // placeId:'',
          // placeName:''
        }, 
        storage:[],//仓库类别数组
        loading: 0
      }
    },
    mounted: function () {
      this.loadStorage(); //仓库列表选择
      this.query();//条件查询显示
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
      load() {  
        var pagebar = this.$refs.pagebar; 
        this.loading = 1;
        this.queryParam.page = pagebar.currentPage;
        this.queryParam.pageSize = pagebar.currentPageSize;
        if(this.queryParam.storageId == null 
          || this.queryParam.storageId == ''){
          this.queryParam.storageId = '--';
        }
        this.$http.post("/api/storage/stockSafe/list", this.queryParam).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.loading = 0;
            var data = res.data.data;//返回的集合
            data.rows.map((item)=>{ 
              item.unitName = this.$args.getArgText('unit',item.unit);
            });
            this.list = data.rows;//返回的数据对象
            this.total = data.total;//返回的对象数量
            this.selection = [];
            // 同步条件信息到表单
            Object.assign(this.queryForm, this.queryParam);
          } else {
            this.loading = 0;
            this.list = null;
            this.total = 0;
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
        Object.assign(this.queryParam,this.queryForm)
        this.load();
      }, 
      pageChange (page) {
        this.load();
      },
      pageSizeChange(pageSize){
        var pagebar = this.$refs.pagebar;
        if(pagebar.currentPage == 1){
          this.load();
        }
      }, 
    
      rowCommand (name, params) {
        if (name === '编辑') {
          if(!this.$user.hasPower('ckgl.aqkc.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);//编辑安全库存参数，跳转至下方的update()方法中
          return;
        }
        if (name === '删除') {//  /api/storage/stockSafe/delete?storageId=&materId=
          if(!this.$user.hasPower('ckgl.aqkc.del')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var storageId = params.row.storageId;
              var materId = params.row.materId;
              this.loading = 1;
              this.$http.post('/api/storage/stockSafe/delete?' 
                + 'storageId=' + storageId
                + '&materId=' + materId, {}).then((res) => {
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
      reset: function () {
        Object.assign(this.queryForm,{
          materName: '',
          // placeId:'',
          // placeName:'',
        });
        this.query();
      },

      add: function () { //  必须事先选择某个仓库才可以新建,需要仓库的id传入
        if(this.queryForm.storageId != '--'){
          this.$refs.edit.open({storageId:this.queryForm.storageId});//跳转至安全库存的---新建页面
        }else{
          this.$Message.error('请选择仓库');
        } 
      },

     //安全库存编辑页面
      update: function (item) {
        item.id = 1;//置灰不能编辑物料编码
        this.$refs.edit.open(item);//跳转至安全库存---编辑页面  StorageSafeEdit.vue
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
