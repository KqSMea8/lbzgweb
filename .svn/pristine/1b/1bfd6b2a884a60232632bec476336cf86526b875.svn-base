<template>
  <ListPage ref="page" title="" api="/api/productStock/list" 
  :model="this" class="customerlist">
    <div class="page-title">
      <a @click="goPage('/productstock/mgr')">产成品库</a> &gt; 盘点
    </div>
    <div class="page-tools">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <RadioGroup v-model="tabPage" type="button" @on-change="goTab">
              <Radio :label="1">盘点</Radio>
              <Radio :label="2">录入</Radio>
              <Radio :label="3">审核</Radio>
            </RadioGroup>
          </td>
          <td><Button @click="setCheck" v-power="'ckgl.ccppd.set'" icon="plus">标记盘点</Button></td> 
          <td><Button @click="setUnCheck" v-power="'ckgl.ccppd.unset'" icon="minus">取消标记</Button></td> 
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.keyword" placeholder="物料名称、物料编码、图号、铸件号" style="width:220px;" ></Input>
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
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading';  
  import page from '@/assets/js/page'; 

  export default {
    components: {
      ListPage,
      DataRowOperateBar,
      Loading,
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            type: 'selection',
            width: 60,
            align: 'center',
          }, 
          {
            title: '物料编码',
            key: 'materId',
            width: 200,
            align: 'left'
          },
          {
            title: '物料名称',
            key: 'materName',
            width: 200,
            align: 'left'
          },
          {
            title: '客户编号',
            key: 'customerId',
            width: 120,
            align: 'left',
          },
          {
            title: '图号',
            key: 'drawing',
            width: 100,
            align: 'center',
          },
          {
            title: '铸件号',
            key: 'casting',
            width: 100,
            align: 'center',
          },
          page.table.initArgColumn({
            title: '单位',
            key: 'unit',
            width: 80,
            group: 'unit',
            align:'center'
          }), 
          {
            title: '数量',
            key: 'quantity',
            width: 80,
            align: 'center',
          }, 
          {
            title: '盘点',
            width: 80,
            align: 'center',
            render(h, params) {
              var row = params.row;
              if(row.checkStatus == 0){
                return h('span', {
                  props: {
                    style:''
                  }
                },'--');
              }
              if(row.checkStatus == 1){
                return h('span', { 
                  style:"color:#2d8cf0", 
                },'盘点');
              }
              if(row.checkStatus == 2){
                return h('span', { 
                  style:"color:#eb5954", 
                },'审核');
              }
            }
          },
        ],
        queryForm: {
          storageId:'100',
          keyword: '',
        }, 

        loading: 0,
        tabPage:1,
      }
    },
    mounted() { 
      this.reset();
    },
    computed: {},
    methods: {
      switStorage(){
        this.reset();
      },
      query () {
        if(!this.queryForm.storageId){
          this.$Message.error('请选择仓库');
          return;
        } 
        this.$refs.page.query();
      },
      reset() {
        Object.assign(this.queryForm,{
          keyword: '',
          placeId:'',
          placeName:'',
        });
        this.query();
      },
      goBack() {
        this.$router.go(-1);
      },
      goTab(){
        var pages = ['/productstock/checkstock','/productstock/checkstock/input','/productstock/checkstock/audit'];
        this.goPage(pages[this.tabPage-1]);
      },
      setCheck() {
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        var data = [];
        selection.map((item)=>{
          if(item.checkStatus != 2){
            data.push(Object.assign({},item,{checkStatus:1,checkQuantity:null,checkRemark:''}));
          }
        }); 
        this.save(data);
      },
      setUnCheck() {
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        var data = [];
        selection.map((item)=>{
          if(item.checkStatus != 2){
            data.push(Object.assign({},item,{checkStatus:0,checkQuantity:null,checkRemark:''}));
          }
        });
        this.save(data);
      },
      save(rows){ 
        var url = '/api/productStock/check/batch'; 
        this.loading = 1;
        this.$http.post(url, rows).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success('操作成功');
            this.$refs.page.load();
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        }); 
      }, 
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css">
   
</style>
