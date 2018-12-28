<template>
  <div class="gridreport-page">
    <!--<div class="page-title">表格型报表</div>-->
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td><Button  @click="batch_export" icon="android-exit" >导出</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Select v-model="queryForm.fieldId" >
              <Option v-for="(item,index) in queryFields" :value="item.fieldId" :key="index">{{ item.title }}</Option>
            </Select>
          </td>
          <td>
            =
          </td>
          <td>
            <Input v-model="queryForm.value"   placeholder="" @on-enter="query"/>
          </td>
          <td>
            <Button icon="ios-search" @click="query"  type="primary">查询</Button>
          </td>
        </tr>
      </table>
    </div>
    <Loading :loading="loading">
      <div class="page-datatable">
        <i-table @on-selection-change="select" :columns="columns" :data="list"></i-table>
        <div style="height:10px;"></div>
        <Page ref="pagebar" :total="total" size="small"
          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
      </div>
    </Loading>
  </div>
</template>
<script>
import Loading from '@/components/loading';

export default {
  components: {
    Loading
  },
  data() {
    var that = this;
    return {
      columns:[
        {
          type: 'selection',
          width: 80,
          align: 'center'
        }
      ],
      report: {},
      formFields:{},
      list:[],
      total: 0,　
      queryParam:{

      },
      queryForm:{
        fieldId:'',
        fieldType:'',
        value:''
      },
      selection:[],
      queryFields:[],
      loading:0,
      reportId:0,
      fieldMap: {},
      enumMap: null
    }
  },
  mounted:function(){

  },
  computed:{

  },
  methods:{
    load: function() {
        var that = this;
        var pagebar = that.$refs.pagebar;
        that.loading = 1;
        that.queryForm.page = pagebar.currentPage;
        that.queryForm.pageSize = pagebar.currentPageSize;
        that.queryForm.reportId = that.reportId;
        var fieldObj = that.fieldMap.get(that.queryForm.fieldId);
        if(fieldObj){
            that.queryForm.fieldType = fieldObj.type;
        }
        //查询日志列表数据
        this.$http.post("/api/wf/report/data", that.queryForm).then((res) => {
          if (res.data.code === 0) {
            that.loading = 0;
            that.report = res.data.data;
//            that.list = that.report.rows;
            this.setColValue();
            that.total = res.data.data.total;
            that.selection = [];
            // 同步条件信息到表单
            $.extend(that.queryForm,that.queryParam);
          } else {
            that.loading = 0;
            that.list = null;
            pagebar.total = 0;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          console.log(error)
          this.$Message.error("请求失败，请重新发送")
        });

    },
    setColValue:function () {
      var that = this;
      var dataList = that.report.rows;
      var fMap = that.fieldMap;
      dataList.forEach(function (value,key) {
        for(var p in value){//列
          var formField = fMap.get(p);
          if(formField){
            var fieldId = formField.id;
            var enumObj = that.enumMap.get(fieldId+'');
            //如果能get到说明是枚举类型
            if(enumObj){
              value[p] = enumObj.get(value[p]);
            }
          }
        }
      })
      that.list = dataList;
    },
    query:function(){
        var that = this;
        //将表单信息，赋值给参数对象
        $.extend(that.queryParam,that.queryForm);
        this.$refs.pagebar.currentPage = 1;
        this.load();
    },
    reset:function(){
        // 清空条件
        this.queryForm.title='';
        this.queryForm.beginTime='';
        this.queryForm.endTime='';
        this.queryForm.trueName='';

        var pagebar = this.$refs.pagebar;
        pagebar.current = 1;
        pagebar.currentPage = 1;
        pagebar.currentPageSize = 10;

        this.query();
    },
    pageChange:function(page){
      var that = this;
      //将参数对象数据赋值个表单进行查询
      $.extend(that.queryForm,that.queryParam);
      this.load();
    },
    pageSizeChange:function(pageSize){
      var pagebar = this.$refs.pagebar;
      if(pagebar.currentPage == 1){
        this.load();
      }
    },
    select:function(selection){
      this.selection=selection;
    },
    batch_export:function(){
      if(!this.$user.hasPower('gzrz.dc')){
          this.$Message.error('暂无权限！');
          return;
        }
      let ids = []
      let confirmTitle = '所有'
      let _url = '/corp/api/worklog/export?title=' + this.queryParam.title+'&trueName=' +this.queryParam.trueName
                                  +'&beginTime=' + this.queryParam.beginTime + '&endTime='+this.queryParam.endTime+'&ids='+ids;
      if (this.selection.length > 0) {
        ids = this.selection.map((item) => {
          return item.id
        })
        _url = '/corp/api/worklog/export?title=&trueName=&beginTime=&endTime=&ids='+ids;
        confirmTitle = '选择的'
      }
      this.$Modal.confirm({
        title: '导出确认',
        content: '<p>确定导出'+ confirmTitle +'数据吗？</p>',
        onOk: () =>{
          window.location.href = _url
          return;
        },
        onCancel:() => {
          return;
        }
      });
    },
    show(report){
      // 加载 report 信息
      // 查询数据
//      report = 4

      this.$Message.info('初始化报表 ' + report);
      var that = this;
      //查询日志列表数据
      that.reportId = report;
      this.$http.post("/api/wf/report/get?id="+report, {}).then((res) => {
        if (res.data.code === 0) {
          that.loading = 0;
          that.report = res.data.data;
          that.formFields = JSON.parse(res.data.data.fieldsJson);
          this.buildColumn();
          this.query();
        } else {
          that.loading = 0;
          that.list = null;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
          console.log(error)
        this.$Message.error("请求失败，请重新发送")
      });

    },
    buildColumn(){
      var that = this;
      var formFields = that.formFields;

      //将datafields 转换成json 格式
      var fieldMap = new Map();
      formFields.forEach(function(field,key){
        let id = field.id;
        fieldMap.set(id,field);
        that.buildEnumMap(field);
      })
      that.fieldMap = fieldMap;
    //查询字段
      var querys = that.report.querys;
      var queryArr = querys.split(',');
      that.queryFields = [];
      queryArr.forEach(function(value,key){
        var field = fieldMap.get(value);
        var queryField = {};
        queryField.title = field.title;
        queryField.type = field.type;
        queryField.fieldId = value;
        that.queryFields.push(queryField);
      })

      //显示字段
      var datas = that.report.datas;
      var dataArr = datas.split(',');
      that.columns = [];
      var sel = {
        type: 'selection',
        width: 80,
        align: 'center',
      };
      that.columns.push(sel)
      dataArr.forEach(function(value,key){
        var field = fieldMap.get(value);
        var col = {};
        col.title= field.title;
        col.key = field.id;
        col.align='center';
        //col.width=80;
        that.columns.push(col);
      });
      var startUser = {
        title: '发起人',
        width: 100,
        key:'startUserName',
        align: 'center',
      };
      that.columns.push(startUser)
      var startTime = {
        title: '发起时间',
        width: 180,
        key:'startTime',
        align: 'center',
      };
      that.columns.push(startTime)

    },
    buildEnumMap(field){
      var that = this;
      if(field){
        if(that.enumMap == null){
          that.enumMap = new Map();
        }
        if(field.type == "radio"){
          let mapping = new Map();
          let enumDatas = field.enum_data;
          enumDatas.forEach(function (enumData,idx) {
            mapping.set(enumData['value'], enumData['text']);
          })
          that.enumMap.set(field.id, mapping);
        }
      }
    }
  }
}

</script>

<style type="text/css">
  /*通用样式*/
  .gridreport-page{
    padding:10px;
  }

  .gridreport-page .page-datatable .ivu-table td,
  .gridreport-page .page-datatable .ivu-table th {
    height: 39px;
  }
</style>
