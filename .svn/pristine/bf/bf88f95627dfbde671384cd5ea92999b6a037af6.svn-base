<template>
    <ListPage title="检验记录修订" ref="page" api="/api/revision/list" @onCurrentRowChange="curRowChg"
              :model="this" :beforeLoad="beforeLoad">

        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        检验物料批次号:
                    </td>
                    <td>
                        <Input v-model="queryForm.batchId" placeholder="模糊查询" ></Input>
                    </td>
                    <td>
                        <Select type="text" v-model="queryForm.textValue">
                        <!--<Select type="text"  v-model="item.materType">-->
														<Option value="1">化验员</Option>
                            <Option value="2">检验员</Option>
                            <!--<Option :value="'3'">办公用品</Option>-->
                        </Select>
                    </td>
										<td v-if="queryForm.textValue==1"><Input v-model="queryForm.analyst">{{queryForm.inspector=""}}</Input></td>
										<td v-if="queryForm.textValue==2"><Input v-model="queryForm.inspector">{{queryForm.analyst=""}}</Input></td>
                    <td>采购日期</td>
                    <td><DatePicker type="daterange" v-model="queryForm.createTime"
                                    split-panels placeholder="开始日期"
                                    style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
                    <td>
                        <Button @click="query"  type="primary" icon="ios-search">查询</Button>
                    </td>
                    <td>
                        <Button @click="reset" >重置</Button>
                    </td>
                    <hr>
                </tr>
            </table>
        </div>
    </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import page from '@/assets/js/page';
  import ListPageDetail from '@/components/page/ListPageDetail';
  import SelStorage from '@/components/storage/input/SelStorage';
  import SelStockOperType from '@/components/storage/input/SelStockOperType';

  import SelProvider from '@/components/provider/SelectProvider';

  export default {
    components: {
      ListPage,
      ListPageDetail,
      DataRowOperateBar,
      //SelProvider  //声明新组件
    },
    data() {
      let that = this;
      return {

        curRowId:'',
        columns: [
            {
                title:'#',
                fixed: 'left',
                width:50,
                align:'center',
                render:(h,params)=>{
                    return h('span', params.index + 1);
                }
            },
            {
                title: '台帐类型',
                key: 'ledgerId',
            },
            {
                title: '版本号',
                key: 'versionId',
            },
            {
                title: '文件批次号',
                key: 'batchId',
            },
          page.table.initDateColumn({
                title: '创建日期',
                key: 'createTime',
          }),
          page.table.initDateColumn({
            title: '化验员',
            key: 'analyst',
          }),
          {
                title: '检验员',
                key: 'inspector',
          },
          {
                title: '操作',
                key: '',
                width: 80,
                /*fixed:'left',*/
                align: 'center',
                render:(h,params)=>{
                    var row = params.row;
                    return h('a',{
                        props:{

                        },
                        on:{
                            click:()=>{
                                this.edit(row);
                            }
                        }
                    },'编辑');
                }
            },
        ],
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          subType:'',
          status:null,
          batchId:'',
          createTime:null,
					textValue:0,
					analyst:'',
					inspector:''
        },
        loading: 0,
        types:[
          {argCode:'1',argText:'设备配件'},
          {argCode:'2',argText:'常规物料'},
          {argCode:'3',argText:'办公用品'}
        ],
      }
    },
    mounted: function () {
      this.reset();
    },
    computed: {},
      computed: {
          computedUnitName () {
              return function (unit){
                  return this.$args.getArgText("unit",unit)
              }
          },
          computedMaterType () {
              return function (materType){
                  if(materType =="1"){
                      return "设备配件";
                  }else if(materType =="2"){
                      return "常规物料";
                  }else if(materType =="3"){
                      return "办公用品";
                  }else{
                      return "";
                  }
              }
          },
      },
    methods: {
      query() {
        this.$refs.page.query();
      },
      beforeLoad(){
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
          subType:'',
          status:null,
          batchId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },
      curRowChg(row){  
        if(row!=null){
           this.curRow = row;
           this.curRowId = row.batchId;
           this.$refs.detail.load(this.curRowId);
        }else{
          this.curRow = null;
          this.curRowId = null;
          this.$refs.detail.clear();
        }
      },
      switStorage(){
        this.reset();
      },
        add: function () {
        this.$router.push({path:'/quality-redo/edit?forward'});
      },
      edit(row){
         if (row.ledgerId=='1001'){
              var url="/quality-record/VisualInspectionEditable?forward&batchId="
          }else if (row.ledgerId=='1002'){
             var url="/quality-record/FerroAlloyEditable?forward&batchId="
          }else if (row.ledgerId=='1004'){
              var url="/quality-record/BrickTubeEditable?forward&batchId="
          }else if (row.ledgerId=='1006'){
              var url="/quality-record/VMethodCoatingEditable?forward&batchId="
          }else if (row.ledgerId=='1008'){
              var url="/quality-record/ResinChromeOreEditable?forward&batchId="
          }else if (row.ledgerId=='1009'){
              var url="/quality-record/CheckLimeEditable?forward&batchId="
          }else if (row.ledgerId=='1010'){
              var url="/quality-record/WaterGlassEditable?forward&batchId="
          }else if (row.ledgerId=='1011'){
              var url="/quality-record/OriginalSandEditable?forward&batchId="
          }else if (row.ledgerId=='1012'){
              var url="/quality-record/ElectrodeEditable?forward&batchId="
          }

         this.$router.push(url+row.batchId+"&versionId="+row.versionId+"&provider=" +row.provider
             +"&materName="+row.materName+"&inspectionDate="+row.inspectionDate
             +"&analyst="+row.analyst+"&inspector="+row.inspector
             +"&auditor="+row.auditor+"&auditTime="+row.auditTime);
         /* this.$router.push(uri+row.batchId+"&ledgerId="+row.ledgerId);*/
          /*alert(this.batchId);*/
/*        console.log(this.curRowId);
        if(this.curRowId){
          this.$router.push({
              curRowId:"2",

            path:'/quality-record/edit?forward&id=' + this.curRowId
          })
        }else{
          this.$Message.error('请选择要编辑的入库单');
        }*/
        },
      update(item) {
            this.$refs.edit.open(item);
        },
     /* update: function (item) {
        this.$refs.edit.open(item);
      }*/
    }
  }

</script>

<style type="text/css">

</style>
