<template>
<ListPage ref="page" title="" api="/api/storageTemp/list" 
  :model="this" :beforeLoad="beforeLoad">
  <div class="page-title">
      <a @click="goPage('/storage/mgr')">仓库管理</a> &gt; 温湿度台账记录
    </div>
  <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button @click="add" v-power="'ckgl.wdjl.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <SelStorage v-model="queryForm.storageId" 
            @on-change="switStorage" style="width:150px;"> 
            </SelStorage>
          </td>
          <td>登记日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.collTime1"
           split-panels placeholder="登记日期"
           style="width: 180px" :clearable="false" :transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset"  type="default">重置</Button>
          </td>
        </tr>
      </table>
  </div> 
  <AdminEdit ref="edit" @on-save="query"></AdminEdit>
  <AdminAdd ref="edit" @on-save="query"></AdminAdd> 
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import AdminEdit from '@/components/storage/temphumi/TempHumiEdit';
  import AdminAdd from'@/components/storage/temphumi/TempHumiEdit'; 
  import SelStorage from '@/components/storage/input/SelStorage'; 

  import page from '@/assets/js/page';

export default {
  components: {
    DataRowOperateBar,
    Loading,
    AdminEdit,
    AdminAdd,
    SelStorage,
    ListPage
  },
  data() {
    var that = this;
    return {
      columns:[
        {
            title: '仓库代码',
            key: 'storageId',
            width:150,
            align:'left',
        },
        page.table.initArgColumn({
           title: '仓库名称',
           key: 'storageId',
           group:'storageList',
           align:'left',
           width:150
         }),
        {
            title: '温度°C',
            width:120,
            key: 'temp',
            align:'center'
        },
        {
            title: '湿度%',
            key: 'humi',
            width:120,
            align:'center',
        },
        {
          title:'登记时间',
          width:160,
          key:"collTime",
          align:'center'
        },
        {
            title: '备注',
            key: 'remark', 
            align:'left',
        },
        {
            title: '操作',
            width: 80,
            align: 'center',
            render(h, params) {

              let setButton = "编辑";
             
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton + ',删除'
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
      queryForm:{
        storageId:this.$user.config.data.storageId,
        collTime1:[]
      },
    }
  },
  mounted:function(){
    this.reset();
  },
  methods:{ 
    requireQuery:function(){
      this.query();
    },
    add:function(){
      this.$refs.edit.open({storageId:''});
    },
    switStorage(){
      this.$user.config.data.storageId = this.queryForm.storageId;
      this.reset();
    }, 
    query:function(){
      this.$refs.page.query();
    },
    beforeLoad(){ 
      var queryParam = this.$refs.page.queryParam;
        queryParam.collTimeStart = '';
        queryParam.collTimeEnd1 = '';
        delete queryParam.collTime1;
        if(this.queryForm.collTime1.length > 0){
          queryParam.collTimeStart = page.formatDate(this.queryForm.collTime1[0]);
        }
        if(this.queryForm.collTime1.length > 1){
          queryParam.collTimeEnd1 = page.formatDate(this.queryForm.collTime1[1]);
        } 
    },
    reset:function(){
      this.queryForm.collTime1=[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())];
      this.query();
    },
     
    rowCommand(name, params) {
        if (name === '编辑') {
          if(!this.$user.hasPower('ckgl.wdjl.edit')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          if(!this.$user.hasPower('ckgl.wdjl.del')){
            this.$Message.error('暂无权限！');
            return;
          }
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = params.row.id;
              this.loading = 1;
              this.$http.post('/api/storageTemp/delete?id=' + aid, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  this.query();
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
  .admin-status-2{
    color:#ff6600;
  }
</style>
