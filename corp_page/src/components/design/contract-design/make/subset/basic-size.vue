<template>
    <div class="page inquirelist">
        <div class="page-title"><!-- 页面布局样式 -->
            尺寸名称
        </div>
        <div class="page-searchbox">
            <div class="page-tools">
                <table cellpadding="10" cellspacing="10">
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
                <table cellspacing="0" cellpadding="0">
                    <thead>
                    <th width="70px">序号</th>
                    <th >尺寸名称</th>
                    <th width="30%">备注</th>
                    </thead>
                    <tbody v-for="(item,index) in list" :key="index">
                    <td>{{index+1}}</td>
                    <td v-if="pageFlag==2"><Input  type="text" v-model="item.testNumber1" placeholder=""/></td>
                    <td v-else>{{item.testNumber1}}</td>
                    <td v-if="pageFlag==2"><Input  type="text" v-model="item.testNumber2" placeholder=""/></td>
                    <td v-else>{{item.testNumber2}}</td>
                    </tbody>
                </table>
            </div>
            <Page :total="total" @on-change="pageChange" @on-page-size-change="pageSizeChange" show-sizer />
        </div>
    </div>

</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import SelProvider from '@/components/provider/SelectProvider';
  import Loading from '@/components/loading';
  export default {
    components: {
      DataRowOperateBar,
      Loading,
      SelProvider,  //声明新组件
    },
    data() {
      return {
        pageFlag: 0,
        list:[],
        total:0,
        queryForm:{
          page: 1,
          pageSize: 10
        },
        loading: 0,
        xz_index:null,
      }
    },
    mounted: function () {
      this.reset();
    },
    computed: {},

    methods: {
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
          "testNumber1":"尺寸名称"+(this.list.length+1),
          "testNumber2":"备注"+(this.list.length+1),
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
    }//methods
  }//export

</script>

<style type="text/css">
    .inquirelist .status-2{
        color:#ff6600;
    }

    .inquirelist .ivu-radio-group-button .ivu-radio-wrapper {
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
</style>
