<template>
    <div class="page inquirelist">
        <h3>创建物料检验项目</h3>
        <Layout>
            <Header class="page-searchbox" style="background-color: #f9fafc;">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="margin: 0 10px">
                                <Input v-model="queryForm.bzh" placeholder="标准号"></Input>
                            </td>
                            <td style="margin: 0 10px">
                                <Input v-model="queryForm.clph" placeholder="材料牌号"></Input>
                            </td>
                            <td style="margin: 0 10px">
                                <Button @click="query"  type="primary" icon="ios-search">查询</Button>
                            </td>
                            <td style="margin: 0 10px">
                                <Button @click="reset" >重置</Button>
                            </td>
                            <td style="margin: 0 10px">
                                <Button @click="goBack" >退出</Button>
                            </td>
                        </tr>
                    </table>
                <div style="clear: both;"></div>
            </Header>
                <Content style="padding-top: 20px ">
                <div style="margin-left: 10px;border: solid 1px #999999;float: left;padding: 10px">
                    <div class="page-searchbox">
                        <div class="page-tools">
                            <table cellpadding="0" cellspacing="20">
                                <tr>
                                    <td><Button @click="add" icon="paper-airplane">新建</Button></td>
                                    <td><Button @click="update" :pageFlag="pageFlag" icon="edit">编辑</Button></td>
                                    <td><Button @click="save" icon="paper-airplane">保存</Button></td>
                                    <td><Button @click="reset" icon="edit">取消</Button></td>
                                    <td><Button @click="goBack" icon="paper-airplane">退出</Button></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="inquire-detail-card editable" style="width: 100%;height: 100%;overflow: hidden">
                        <div  class="editable-table" style="margin-bottom:40px ">
                            <table cellspacing="0" cellpadding="0" style="width: 100%;">
                                <tr>
                                <th>序号</th>
                                <th>元素符号</th>
                                <th>元素名称</th>
                                <th>备注</th>
                                </tr>
                                <tr v-for="(item,index) in list" @click="listClick(index)">
                                <td>{{index+1}}</td>
                                <td v-if="pageFlag == 2 && index == xz_index-1"><Input  type="text" v-model="item.baumeDegree" placeholder=""/></td>
                                <td v-else>{{item.baumeDegree}}</td>
                                <td v-if="pageFlag==2"><Input  type="text" v-model="item.testNumber1" placeholder=""/></td>
                                <td v-else>{{item.testNumber1}}</td>
                                <td v-if="pageFlag==2"><Input  type="text" v-model="item.testNumber2" placeholder=""/></td>
                                <td v-else>{{item.testNumber2}}</td>
                                </tr>
                            </table>
                        </div>
                        <Page :total="total" @on-change="pageChange" size="small" @on-page-size-change="pageSizeChange" show-sizer />
                    </div>

                </div>
                <div style="margin-right: 10px;float: left;padding: 26px;padding-left: 36px;border: solid 1px #999999;">
                    <Row :gutter="32" style="display: block">
                        <Col span="12" class="demo-tabs-style1" style="background: #e3e8ee;padding:16px;">
                            <Tabs type="card">
                                <TabPane label="化学成份（%）"><Hxcf ></Hxcf></TabPane>
                                <TabPane label="性能指标">性能指标</TabPane>
                            </Tabs>
                        </Col>
                    </Row>
                </div>
                </Content>
        </Layout>
    </div>
</template>
<script>
    import Hxcf from './ins_sbset/chemical';
  export default {
    components: {
      Hxcf
    },
    data () {
      return {
        queryForm:{
          bzh:'',
          clph:'',
          page: 1,
          pageSize: 10
        },
        pageFlag: 0,
        list:[],
        total:0,
      }
    },
    mounted: function () {
      this.reset();
    },
    methods:{
      load() {
        this.loading = 1;
        this.$http.post("/api/newProviderMaterial/list",this.queryForm).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.list = res.data.data.rows;
            this.total = res.data.data.total;
            this.loading = 0;
          } else {
            this.loading = 0;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },

      query: function () {
        this.load();
      },
      add(){
        this.list.push(this.listNewRow());//增加新行
        this.xz_index=this.list.length;
      },
      listNewRow(){
        var def = {
          "baumeDegree":this.list.length+1,
          "testNumber1":"项目类型名称"+(this.list.length+1),
          "testNumber2":"说明"+(this.list.length+1),
        };
        return def;
      },
      update: function() {
        if (this.pageFlag <= 1){
          this.pageFlag = 2;
        }else{
          this.pageFlag = 0;
        }
      },
      goBack: function () {
        this.$router.go(-1);
        /*this.$router.push('/design');*/
      },
      reset: function () {
        this.list=[];
        this.query();
      },
      save(){
        console.log(this.list);
        this.$http.post("/api/newProviderMaterial/list",this.list).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success("保存成功");
            this.loading = 0;
          } else {
            this.loading = 0;
            this.$Message.error("保存失败");
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新尝试")
        });
      },
      pageChange(p){
        this.queryForm.page = p;
        this.query();
      },
      pageSizeChange(s){
        this.queryForm.pageSize = s;
        this.query();
      },
      listClick(index){
        console.log(this.list[index]);
      },
    }
  }
</script>
<style>
    /*带有表单查询条件的搜索框*/
    .page-searchbox{
        margin-top: 10px;
    }
    .page-searchbox table td{
        padding-right: 6px;
    }
    .page-tools .ivu-radio-group-button .ivu-radio-wrapper,
    .page-searchbox .ivu-radio-group-button .ivu-radio-wrapper{
        height: 28px;
        line-height: 26px;
        padding: 0 12px;
        font-size: 12px;
    }
    .inquire-detail-card .editable-table{
        overflow-x: auto;
    }
    .editable-table table{
        border-collapse:collapse;
        border:  px solid #dedede;
        width: 40%;
    }
    .editable-table table th{
        background: #efefef;
    }
    .inquire-detail-card .editable-table table td,
    .inquire-detail-card .editable-table table th{
        height: 40px;
        border: 1px solid #dedede;
        padding: 0 10px;text-align: center;
    }
    .editable-table .ivu-input,
    .editable-table .ivu-input-number,
    .editable-table .ivu-select,
    .editable-table .ivu-select-selection{
        border: 0px;
    }
    .editable-table .ivu-input-number-input{
        padding-right: 24px;
        text-align: right;
    }
    .editable-table .col-xh{
        text-align: center; cursor: default;
    }
    .editable-table .col-xh.cur{
        background: #e8f8fd;
        color:#20bfee;
        border-right: 2px solid #20bfee;
        cursor: default;font-weight: bold;;
    }
    /*行扩展操作*/
    .editable-table .col-cz input{
        text-align: center;;
    }
    .editable-table .col-yd input{
        text-align: center;;
    }
    .ivu-layout,ivu-layout-header{
        background-color: #ffffff;
    }
    /*
    选项卡
    */
    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content {
        height: 100%;
        margin-top: -16px;
        width: 100%;
    }

    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content > .ivu-tabs-tabpane {
        background: #fff;
        padding: 16px;
    }

    .demo-tabs-style1 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab {
        border-color: transparent;
    }

    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active {
        border-color: #fff;
    }
    .ivu-col-span-12{
        width: 100%;
    }
</style>