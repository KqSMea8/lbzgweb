<template>
  <ListPage ref="page" api="/api/selfMaterMakeApply/list" 
  :model="this" @onCurrentRowChange="curRowChg" :beforeLoad="beforeLoad">
    <div class="page-title" slot="page-title">
    自用件制作申请
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
      <!--     <td>
            <SelStorage v-model="queryForm.storageId" 
            @on-change="switStorage" style="width:150px;"> 
            </SelStorage> 
          </td> -->
          <td>
            <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
              <Radio :label="null">所有</Radio>
              <Radio :label="0">待提交</Radio>
              <Radio :label="1">审核中</Radio>
              <Radio :label="2">通过</Radio>
              <Radio :label="3">驳回</Radio>
            </RadioGroup>
          </td>
          <td class="page-tools">
            <Button @click="add" v-power="'ckgl.rk.add'" icon="plus">新建</Button>
          </td>
          <td class="page-tools" v-if="queryForm.status==0"> 
            <Button @click="edit" v-power="'ckgl.rk.edit'" icon="edit">编辑</Button> 
            &nbsp;
            <Button @click="del" v-power="'ckgl.rk.del'" icon="android-delete">删除</Button>
            &nbsp;
            <Button @click="sendAudit" v-power="'ckgl.rk.audit'" icon="checkmark">送审</Button>  
          </td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.materName" placeholder="自用件名称" ></Input>
          </td> 
          <td>
            <Input v-model="queryForm.texture" placeholder="材质" ></Input>
          </td> 
          <td>
            <Input v-model="queryForm.proposer" placeholder="申请人" ></Input>
          </td> 
          <td>创建日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="创建日期"
           style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
          <td>
            <Button @click="query"  type="primary" icon="ios-search">查询</Button>
          </td>
          <td>
            <Button @click="reset" >重置</Button>
          </td>
        </tr>
      </table>
    </div>
    <ListPageDetail ref="detail" slot="page-datatable-detail"
    api="/api/selfMaterMakeApply/get?selfMaterMakeApplyId="
    :columns="columns1">
    </ListPageDetail>
  </ListPage> 
    
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import ListPageDetail from '@/components/page/ListPageDetail';
  //import SelStorage from '@/components/storage/input/SelStorage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar'; 
  import page from '@/assets/js/page';
  import process from '@/components/process-start';
    
  export default {
    components: { 
      ListPage,
      ListPageDetail,
      DataRowOperateBar,
      process,
      page,
    },
    data() {
      let that = this;
      return {
        curRow:null,  
        columns: [
          {
            type: 'selection',
            width: 60,
            align: 'center',
            fixed: 'left',
          }, 
          {
            title: '单号',
            key: 'selfMaterMakeApplyId',
            width: 150,
            fixed: 'left',
            render:(h,params)=>{
              var row = params.row;
              var text = row.selfMaterMakeApplyId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{ 
                  }
                }
              },text);
            }
          },
          page.table.initPersonColumn({
            title: '申请人',
            key: 'proposer',
            align: 'left',
            width: 120,
          }),
           page.table.initDepartmentColumn({
            title: '申请部门',
            key: 'department',
          }),
            page.table.initDateColumn({
            title: '申请日期',
            key: 'createTime',
          }),
          {
            title: '驳回原因',
            key: 'wfInstRemark',
            align: 'center',
            width: 200,
          },        
          page.table.initDateColumn({
            title: '驳回日期',
            key: 'wfInstTime',
          }),
          page.table.initMapColumn({
            title: '状态',
            key: 'status',
            data:{
              '0':'待提交',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }), 
        ],
        columns1: [     
          {
            title: '自用件名称',
            key: 'materName',
            fixed: 'left',
            align: 'left',
            width: 200,
          },
          {
            title: '规格',
            key: 'spec',
            align: 'left',
            width: 200,
          },
          {
            title: '单重',
            key: 'weight',
            align: 'left',
            width: 200,
          },
          {
            title: '材质',
            key: 'texture',
            align: 'left',
            width:200
          },
          {
            title: '数量',
            key: 'quantity',
            align: 'left',
            width:120
          },
          {
            title: '工期要求',
            key: 'needDate',
            align: 'left',
            width:300
          },
          {
            title: '用途说明',
            key: 'remark',
            align: 'left',
            width:300,
          }
        ],
        queryForm: {
          materName:'',
          texture:'',
          proposer:'',
          status:0,
          createTime:null,
        },
        loading: 0,
        oriItem:{},
      }
    },
    mounted: function () {
      this.reset();
    },
    methods: {
     query() {
        this.$refs.page.query();
      },
      beforeLoad(){
        this.oriItem=this.$refs.detail;
        var queryParam = this.$refs.page.queryParam;
        queryParam.createTimeStart = '';
        queryParam.createTimeEnd = '';
        delete queryParam.createTime;
        if(this.queryForm.createTime.length > 0){
          queryParam.createTimeStart = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          queryParam.createTimeEnd = page.formatDate(this.queryForm.createTime[1]);
        }
      }, 
      reset() {
        Object.assign(this.queryForm,{
          materName:'',
          texture:'',
          proposer:'',
          status:0,
          selfMaterMakeApplyId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.selfMaterMakeApplyId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },

      edit(){
        if(this.curRow){
            this.$router.push({
              path:'/selfmater/edit?forward&id=' + this.curRow.selfMaterMakeApplyId
            })
        }
      },
      sendAudit(){
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        var start = 0;
        var handle = ()=>{
          if(start >= selection.length){
            return;
          }
          var item = selection[start];
         this.submitAudit(item,(err)=>{
            if(err){
              this.$refs.page.load();
              this.$Message.error(err);
            }else{
              start ++ ;
              if(start < selection.length){
                handle();
              }else{
                this.$refs.page.load();
              }
            } 
          }); 
        }
        handle();
      },

      submitAudit(oriItem,callback){   
         this.$http.post("/api/selfMaterMakeApply/get?selfMaterMakeApplyId=" + oriItem.selfMaterMakeApplyId,{}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {            
            if(res.data.data){
              oriItem = res.data.data;
              oriItem.proposerName = this.$args.getArgText('empList',res.data.data.proposer);
              oriItem.departmentName = this.$args.getArgText('deptList',res.data.data.department);
              process.startSelfMater(oriItem,(err,instid)=>{              
                  if(err){
                    callback(err);
                  }else{
                    var data = {wfInstId:instid};
                    data.selfMaterMakeApplyId = oriItem.selfMaterMakeApplyId;
                    this.submitAuditCallBack(data,callback);
                  }
              });

            }else{
              this.$Message.error('数据不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
    
      },

      submitAuditCallBack(data,callback){ 
        var api = '/api/selfMaterMakeApply/submit';
        this.loading = 1;
        this.$http.post(api,data).then((res) => {
          this.loading = 0;
          if(res.data.code == 0){
            callback(null); 
          }else{
            callback(res.data.message);
          } 
        }).catch((error) => { 
          this.loading = 0;
          callback('提交失败！'); 
        });
      },

      del(){
       this.$Modal.confirm({
          title: '删除确认',
          content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
          onOk: () => {
            if(this.curRow){
            this.loading = 1;
            this.$http.post('/api/selfMaterMakeApply/delete?selfMaterMakeApplyId='+this.curRow.selfMaterMakeApplyId
            ).then((res) => {
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
            this.query();
          }
        }
        });
      },
      add() { 
        this.$router.push({path:'/selfmater/edit?forward'})
      }
    }
  }

</script>

<style type="text/css">
  
  
</style>
