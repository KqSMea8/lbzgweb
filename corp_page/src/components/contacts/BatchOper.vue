<template>
    <div class="page">
      <div class="page-bar">
          <table class="page-bar-layout" cellspacing="0" cellpadding="0">
            <tr>
              <td class="page-bar-left">
                <Button size="small" icon="chevron-left" @click="goBack" type="warning">返回</Button>
              </td>
              <td class="page-bar-title">批量导入/导出</td>
              <td class="page-bar-right"></td>
            </tr>
          </table>
      </div>

      <div class="page-content">
          <Tabs :animated="false" class="batch-oper-content">
              <TabPane label="导出信息" class="batch-oper-export">
                导出部门:
                <div @click="selectDept" class="batch-oper-export-dept">
                  <Input v-model="deptNames" placeholder="请选择要导出的部门" style="width: 300px"/>
                </div>
                <a download :href="'/corp/api/contacts/emp/export?deptIds=' + deptIds">
                   <Button type="primary"  class="export-btn">开始导出</Button>
                </a>
                <SelectDept ref="dept" @on-check="checkDept"></SelectDept>
              </TabPane>
              <TabPane label="导入信息" class="batch-oper-import">
                <p style="font-size:14px;">您可以导出通讯录，按照导出的通讯录完善成员的信息后，开始导入</p>
                 <Upload action="/corp/api/file/upload"
                         :format="['xls','xlsx']"
                         :on-success="handleSuccess"
                         :on-error="handleError"
                         :on-format-error="handleFormatError" >
                    <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
                </Upload>
                <Button type="primary" @click="importEmp" class="batch-oper-import-btn">开始导入</Button>
               <!--  <div class="page-datatable">
                  <Table :columns="columns" :data="list"></Table>
                </div> -->
              </TabPane>
          </Tabs>
      </div>
    </div>
</template>
<script>
import Loading from '@/components/loading';
import SelectDept from '@/components/commons/SelectDept';

export default {
  components: {
    Loading,
    SelectDept
  },
  data() {
    return {
      loading:0,
      columns:[
        {
            title: '操作时间',
            key: 'operateTime',
            align: 'center',
            width:200
        },
        {
            title: '操作者',
            key: 'operator',
            align: 'center',
            width:160
        },
        {
           title: '状态',
            key: 'status',
            align: 'center',
            width:80,
            render: (h, params) => {
              return this.option(params.row.status);
            }
        },
        {
          title: '备注',
          key: 'remark',
          align: 'left'
        }
      ],
      list:[],
      deptIds:[],
      deptNames:'',
      fileId:'',
      installed:false

    }
  },
  mounted(){
//    this.install();
  },
  computed:{

  },
  methods:{
    option(status){
      //0.创建 1.处理中 2.完成 3.失败 4.超时
      switch(status)
        {
        case 1:
          return '处理中'
          break;
        case 2:
          return '同步完成'
          break;
        case 3:
          return '同步失败'
          break;
        case 4:
          return '超时'
          break;
        default:
          return '创建'
        }
    },
    goBack(){
        this.$router.back(-1);
    },
    selectDept(){
      this.$refs.dept.open();
    },
    checkDept(depts){
      this.deptIds = depts.map((item) => {return item.deptId});
      this.deptNames = depts.map((item) => {return item.deptName}).join(',');
      this.$refs.dept.close();
    },
    handleSuccess (res, file) {
      this.fileId = res.data;
    },
    handleError (error){
      this.$Message.error('文件上传出错');
    },
    handleFormatError(file){
       this.$Message.error('文件格式不正确');
    },
    importEmp(){
      if(this.fileId==='' || this.fileId === null){
          this.$Message.error('请选择需要上传的文件');
          return;
      }
      this.loading = 1;
      this.$http.post('/api/contacts/emp/import?fileId=' + this.fileId+"&module=contacts.import"+"&installed=" + this.installed ,{}).then((res) => {
          if (res.data.code === 0) {
             this.loading = 0;
             this.$Message.info('导入完成');
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
    },
     install(){
       this.$http.post('/api/corp/contacts/installed', {}).then((res) => {
          if (res.data.code === 0) {
             this.installed = res.data.data === 1 ? true : false;
          } else {
            this.installed = false;
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.installed = false;
          this.$Message.error(error.message)
        });
    }
  }
}

</script>

<style type="text/css">
   .export-btn ,.export-btn:hover{
     margin:10px;
     color: #fff;
     background-color: #20bfee;
     border-color: #20bfee;
   }
   .batch-oper-content {
     max-width:1024px;
     margin: auto;
   }
   .batch-oper-export {
      padding:6px;
      font-size: 14px;
   }
   .batch-oper-export-dept {
      display: inline-block;
   }
   .batch-oper-import {
     padding:6px;
   }
   .batch-oper-import .ivu-upload {
     margin-top: 10px;
     float: left;
   }
   .batch-oper-import .ivu-upload-list {
      margin-top: 50px;
    }
    .batch-oper-import .ivu-upload-list .ivu-upload-list-remove {
       margin-left:10px;
    }
   .batch-oper-import-btn,.batch-oper-import-btn:hover {
     margin-top: 20px;
     margin-left: 50px;
     color: #fff;
     background-color: #20bfee;
     border-color: #20bfee;
   }

  .batch-oper-content .page-datatable .ivu-table td,
  .batch-oper-content .page-datatable .ivu-table th {
    height: 39px;
  }
</style>

