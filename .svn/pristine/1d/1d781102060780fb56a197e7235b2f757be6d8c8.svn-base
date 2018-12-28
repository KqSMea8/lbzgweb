<template>
  <ListPage ref="page" title="" api="/api/material/list" 
  :model="this" :beforeLoad="beforeLoad" class="material-list"> 
      <div class="page-searchbox">
        <table cellpadding="0" cellspacing="0" >
          <tr>
            <td >
              <Input v-model="queryForm.drawing" placeholder="图号" style="width:150px" @on-enter="query" />
            </td>
            <td >
               <Input v-model="queryForm.materId" placeholder="物料编码" style="width:150px" @on-enter="query" />
            </td>
            <td >
               <Input v-model="queryForm.materName" placeholder="物料名称" style="width:150px" @on-enter="query" />
            </td>
            <td >
              <Button icon="ios-search" type="primary" @click="query">查询</Button>
            </td>
            <td >
              <Button @click="reset">重置</Button>
            </td>
            <td><Button  @click="add"  icon="plus">添加</Button></td>
          </tr>
        </table>
      </div> 
      <div>
        <MaterEdit ref="materEdit" @on-save="load"></MaterEdit>
        <SelectCategory ref="selectCategory"></SelectCategory>
      </div>
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';
  import MaterEdit from '@/components/material/MaterEdit';
  import SelectCategory from '@/components/material-category/SelectMaterialCategory';

export default {
  components: {
    DataRowOperateBar,
    Loading,
    MaterEdit,
    SelectCategory,
    ListPage
  },
  props: {
  },
  data() {
    var that = this;
    return {
      loading:0,
      totalCount:0,
      timer:{},
      columns:[
        {
          title: '物料编码',
          key: 'materId',
          align: 'left',
          width:160
        },
        {
          title: '物料名称  【客户 - 图号】',
          key: 'materName',
          align: 'left', 
          render:(h,params)=>{
            var row = params.row;
            var title = row.materName;
            if(row.type == '2'){
              title =
              [row.materName,'    ','【',
               row.customerName, ' - ',row.drawing,'】'].join('');
            }
            return h('span',{},title);
          }
        },
        {
          title: '规格',
          key: 'spec',
          align: 'left',
          width:60
        },
        {
          title: '型号',
          key: 'model',
          align: 'left',
          width:60
        },
        {
          title: '单位',
          key: 'unitName',
          align: 'center',
          width:60
        },
        {
          title: '物料种类',
          key: 'cateName',
          align: 'left',
          width:100
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
          align:'left',
          width:100,
          render: (h, params) => {
            return  h(DataRowOperateBar,{
                  props:{
                    commands:'编辑,移动,删除'
                  },
                  on:{
                    'on-command': (name)=>{
                      that.rowCommand(name,params)
                    }
                  }
                });
            }
        }
      ],
      list:[],
      queryParam:{

      },
      queryForm:{
         materId: '',
         drawing: '',
         cateCode: '',
         containSub:1,
         materName:'',
      },
      selection:[]
    }
  },
  mounted:function(){
    this.query();
  },
  computed:{
  },
  methods:{
    query() {
      this.$refs.page.query();
    }, 
    beforeLoad(){

    },
    load(){
      this.$refs.page.load();
    },
    reset:function(){
      this.queryForm = {
        materId: '',
         drawing: '',
         cateCode: '', 
         materName:'',
      }
      this.query();
    }, 
    rowCommand:function(name,params){
      if(name == '删除'){
        this.delete(params.row.materId);
        return;
      }
      if(name == '编辑'){
        this.update(params.row.materId);
        return;
      } 
    }, 
    delete:function(materId){
      var that = this;
      this.$Modal.confirm({
        title: '删除确认',
        content: '<p>删除后不能恢复，确定删除该条记录吗？</p>',
        onOk: () => {
          that.loading = 1;
          this.$http.post('/api/material/delete?materId=' + materId,{}).then((res) => {
            if (res.data.code === 0) {
              that.loading = 0;
              this.$Message.success("删除成功");
              that.query();
            } else {
              this.loading = 0;
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.loading = 0;
            this.$Message.error(error.message)
          });
        }
      });
    },
    add:function(){
      this.$refs.materEdit.open({materId:'',cateCode:this.queryForm.cateCode,code:this.queryForm.cateCode});
    },
    update:function(materId){
      this.$refs.materEdit.open({materId:materId});
    },
    save(item){
      var url = '/api/material/update';
      this.loading = 1;
      this.$http.post(url, item).then((res) => {
        this.loading = 0;
        if (res.data.code == 0) {
          this.$Message.success('保存成功');
          this.load();
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
        this.loading = 0;
        this.$Message.error(error.message)
      });
    },
  }
}

</script>

<style type="text/css">
  /*通用样式*/
  .material-list .status-2{
    color:#ff6600;
  }
</style>

