<template>
    <ListPage ref="page" title="计量" api="/api/instrument/list"
              :model="this" :beforeLoad="beforeLoad" class="customerlist">
        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>计量器具名称：</td>
                    <td>
                        <Input v-model="queryForm.counterName" placeholder="计量器具名称"></Input>
                    </td>
                    <td>内部编号：</td>
                    <td>
                        <Input v-model="queryForm.internalId" placeholder="内部编号"></Input>
                    </td>
                    <td>在用情况：</td>
                    <td>
                        <Select v-model="queryForm.useCase" style="width:150px" placeholder="在用情况">
                            <Option value=""></Option>
                            <Option value="1">正常</Option>
                            <Option value="2">报废</Option>
                            <Option value="3">维修</Option>
                            <Option value="4">封存</Option>
                        </Select>
                    </td>
                    <td>状态：</td>
                    <td>
                        <Select v-model="queryForm.status" style="width:150px" placeholder="状态">
                            <Option value=""></Option>
                            <Option value="1">外检中</Option>
                            <Option value="2">正常使用</Option>
                        </Select>
                    </td>
                    <td>类别：</td>
                    <td>
                        <Select v-model="queryForm.type" style="width:150px" placeholder="类别">
                            <Option value=""></Option>
                            <Option value="1">A类</Option>
                            <Option value="2">B类</Option>
                            <Option value="3">C类</Option>
                        </Select>
                    </td>
                    <td>安装位置：</td>
                    <td>
                        <Input v-model="queryForm.installationSite" placeholder="安装位置"></Input>
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
        <div class="page-tools">
            <table cellpadding="10" cellspacing="10">
                <tr>
                    <td><Button @click="add"  icon="plus">添加计量工具</Button></td>
                    <td><Button @click="update" icon="edit">编辑计量工具</Button></td>
                    <td><Button @click="scrapTo"  icon="android-delete">报废</Button></td>
                    <td><Button @click="addApp"  icon="paper-airplane">新建外检申请单</Button></td>
                    <td><Button @click="Inspection" icon="paper-airplane">外检申请记录管理</Button></td>
                </tr>
            </table>
        </div>
        <ScrapAdd ref="scrap"></ScrapAdd>
    </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import QualityAdd from './QualityAdd';
  import AddApp from './AddApplication';
  import ScrapAdd from './ScrapAdd';

  export default {
    components: {
      ListPage,
      AddApp,
      DataRowOperateBar,
      QualityAdd,
      ScrapAdd
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            type: 'selection',
            width: 60,
          },
          {
            title: '序号',
            width:100,
            render:(h,params)=>{
              return h('span', params.index + 1);
            }
          },
          {
            title: '物料编码',
            key: 'materId',
            width: 100,
          },
          {
            title: '计量器具名称',
            key: 'counterName',
            width: 110,
          },
          {
            title: '规格等级',
            key: 'spec',
            width: 100,
          },
          {
            title: '型号',
            key: 'model',
            width: 100,
          },
          {
            title: '产地',
            key: 'orgin',
            width: 100,
          },
          {
            title: '出厂编号',
            key: 'factoryId',
            width: 100,
          },
          {
            title: '内部编号',
            key: 'internalId',
            width: 100,
          },
          {
            title: '安装位置',
            key: 'installationSite',
            width: 100,
          },
          {
            title: '领用日期',
            key: 'getTime',
            width: 100,
          },
          {
            title: '在用情况',
            key: 'useCase',
            width: 100,
            render:(h,params)=>{
              var status = params.row.useCase;
              var setButton = "正常";
              if (status == 2) {
                setButton = "报废";
              }
              if (status == 3) {
                setButton = "维修";
              }
              if (status == 4) {
                setButton = "封存";
              }
              return h('span',{class:'status-'+status},setButton);
            }
          },
          {
            title: '类别',
            key: 'type',
            width: 100,
            render:(h,params)=>{
              var type = params.row.type;
              var setButton = "A类";
              if (type == 2) {
                setButton = "B类";
              }
              if (type == 3) {
                setButton = "C类";
              }
              return h('span',{class:'status-'+type},setButton);
            }
          },
          {
            title: '检定周期',
            key: 'retestingPeriod',
            width: 100,
          },
          {
            title: '检定日期',
            key: 'testingTime',
            width: 100,
          },
          {
            title: '报审日期',
            key: 'reportTime',
            width: 100,
          },
          {
            title: '状态',
            key: 'status',
            width: 100,
            render:(h,params)=>{
              var status = params.row.status;
              var setButton = "外检中";
              if (status == 2) {
                setButton = "正常使用";
              }
              return h('span',{class:'status-'+status},setButton);
            }
          },
          {
            title: '生产日期',
            key: 'createTime',
            width: 100,
          },
          {
            title: '质保期',
            key: 'qualityTime',
            width: 100,
          },
          {
            title: '备注',
            key: 'remarks',
            width: 100,
          },
          {
            title: '操作',//操作按钮列表
            width: 80,
            align: 'center',
            render(h, params) {
              return h(DataRowOperateBar, {
                props: {
                  commands: '编辑'
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
          counterName:'',
          internalId:'',
          useCase:'',
          status:'',
          type:'',
          installationSite:''
        },
        queryForm2: {
          internalId:'',
        },
        loading:0,
        industry:[],
				ruleValidate:{}
      }
    },
    mounted() {
      this.reset();
    },
    computed: {},
    methods: {
        load(){
            list.map((item)=>{item._checked=false;});
        },
        /**/
      query() {
        this.$refs.page.query();
      },
      reset() {
        Object.assign(this.queryForm,{
          counterName:'',
          internalId:'',
          useCase:'',
          status:'',
          type:'',
          installationSite:''
        });
        this.query();
      },
      beforeLoad(){

      },
      rowCommand(name, params) {
        if (name === '编辑') {
          this.update(params.row);

          return;
        }
      },
      innerCheckRow(index){
            for(var i = 0;i<this.list.length;i++){
                var item = this.list[i];
                item._checked = index == i && item.status!=2;
            }
       },
      add() {
        this.goPage("/device/add");
      },
      Inspection() {
          this.goPage("/device/Inspection?forward");
      },
      addApp() {
        var selection = this.$refs.page.getSelection();
        //this.goPage("/device/AddApp?instrumentId=123");
        var ss="";
        for(var i in selection){
            ss+=selection[i].instrumentId+",";
        }
        if (!$.isEmptyObject(selection)) {
          this.goPage("/device/AddApp?instrumentId="+ss);
        }else{
          this.$Message.error("请选择");
        }
      },
      update(item) {
          var selection = this.$refs.page.getSelection();

          if(selection.length>1){
              this.$Message.info("只能选择一个");
          }else if (selection.length<1) {
              this.$Message.info("请选择计量器具");
          }else {
              if(item.instrumentId!=null){
                  this.goPage("/device/add?forward&id="+item.instrumentId);
              }else{
                  var selection = this.$refs.page.getSelection();
                  if (!$.isEmptyObject(selection)){
                      this.goPage("/device/add?forward&id="+selection[0]['instrumentId']);
                  }else{
                      this.$Message.error("请选择");
                  }
              }
          }
      },
      del(){

      },
      scrapTo(){
        var selection = this.$refs.page.getSelection();
        if(selection.length>1){
          this.$Message.info("只能选择一个");
        }else if (selection.length<1) {
          this.$Message.info("请选择计量器具");
        }else{
          this.$refs.scrap.open(selection[0]);
        }
      },
      goPage(url){
        this.$router.push(url);
      },
    }
  }

</script>

<style type="text/css">
    .customerlist .status-2{
        color:#ff6600;
    }
</style>
