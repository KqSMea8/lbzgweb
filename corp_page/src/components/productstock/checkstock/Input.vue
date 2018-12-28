<template>
  <ListPage ref="page" title="" api="/api/productStock/list" 
  :model="this" class="customerlist" @on-load-data="loadData">
    <div class="page-title">
      <a @click="goPage('/productstock/mgr')">产成品库</a> &gt; 盘点录入
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
          <td><Button @click="checkSubmit" v-power="'ckgl.ccppd.submit'" icon="checkmark">提交审核</Button></td>
        </tr>
      </table>
    </div>
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <Input v-model="queryForm.keyword" placeholder="物料名称、物料编码、批号" style="width:220px;" ></Input>
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
  <div slot="page-table">
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td style="color:#999">
            编辑 <span style="color:#eb5954">{{this.editCount()}}</span> 行，请保存
          </td> 
          <td><Button @click="save"  icon="checkmark" type="primary">保存</Button></td> 
        </tr>
      </table>
    </div> 
    <Editable :editable="false">
      <table cellspacing="0" cellpadding="0">
          <thead>
            <th class="col-xh">序号</th>  
            <th >编辑</th>
            <th >物料编码</th>
            <th >物料名称</th>
            <th >规格</th>
            <th >型号</th>
            <th >批次</th>
            <th >货位</th>
            <th >单位</th>
            <th >库存数量</th>
            <th class="col-quantity col-w3"><span>实盘数量</span></th>
            <th class="col-w2"><span>差异原因</span></th>
          </thead> 
          <tbody>
            <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
              <td :class="{'col-xh':true,'cur':index==curIndex}">
                {{index+1}}
              </td>
              <td>
                <Icon v-if="rowChange(item)" type="record" style="color:#eb5954;font-size: 18px"></Icon>
              </td>
              <td>
                {{item.materId}}
              </td>  
              <td >
                {{item.materName}} 
              </td>
              <td >
                {{item.spec}} 
              </td>
              <td >
                {{item.model}} 
              </td>
              <td >
                {{item.batch}} 
              </td>
              <td >
                {{item.placeName}} 
              </td>
              <td >
                {{$args.getArgText('unit',item.unit)}}  
              </td>
              <td >
                {{item.quantity}} 
              </td>
              <td class="col-quantity col-w3"> 
                <InputNumber  :max="999999" :min="0" v-model="item.inputCheckQuantity" style="width:120px"></InputNumber> 
              </td>
              <td><Input  type="text" v-model="item.inputCheckRemark" /></td> 
            </tr>
          </tbody>
        </table> 
    </Editable>  
  </div>
</ListPage>
</template>
<script>
  import Editable from '@/components/editable-table';
  import ListPage from '@/components/page/ListPage'; 
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading'; 
  import SelPlaceInput from '@/components/storage/selplace/SelPlaceInput';
  import page from '@/assets/js/page';
  import SelStorage from '@/components/storage/input/SelStorage'; 

  export default {
    components: {
      ListPage,
      DataRowOperateBar,
      Loading, 
      SelPlaceInput,
      SelStorage,
      Editable
    },
    data() {
      let that = this;
      return {
        columns: [ 
        ],
        queryForm: {
          storageId:'100',
          keyword: '',
          checkStatus:1,
        },
        list:[],
        loading: 0,
        tabPage:2,
        curIndex:0, 
      }
    },
    mounted() { 
      this.reset();
    },
    computed: { 
    },
    methods: {
      switStorage(){
        this.reset();
      },
      query () {
        if(this.queryForm.storageId == ''){
          this.$Message.error('请选择仓库');
          return;
        }
        this.$refs.page.query();
      },
      reset() {
        Object.assign(this.queryForm,{
          keyword: '',
          checkStatus:1,
        });
        this.query();
      },
      loadData(list){
        list.map((item)=>{
          item.inputCheckQuantity = item.checkQuantity;
          item.inputCheckRemark = item.checkRemark;
          item.change = 0;
        })
        this.list = list;
      },
      rowChange(item){
        return item.checkQuantity != item.inputCheckQuantity
        || item.checkRemark != item.inputCheckRemark;
      },
      editCount(){
        var count = 0;
        this.list.map((item)=>{
          if(this.rowChange(item)){
            count ++ ;
          }
        });
        return count;
      },
      save(){
        var data = [];
        this.list.map((item)=>{
          if(this.rowChange(item)){
            var newItem = Object.assign({},item); 
            newItem.checkQuantity = newItem.inputCheckQuantity;
            newItem.checkRemark = newItem.inputCheckRemark; 
            data.push(newItem);
          } 
        });
        if(data.length == 0){
          this.$Message.error('暂无编辑的数据');
          return;
        }

        this.$http.post('/api/productStock/check/batch', data)
        .then((res) => {
          if (res.data.code === 0) { 
            this.$Message.info('保存成功！');
            this.$refs.page.load();
          } else {
            this.$Message.error(res.data.message)
            this.$refs.page.load();
          }
        })
        .catch((error) => { 
            this.$Message.error(error.toString())
          });
        
      },
      goBack() {
        this.$router.go(-1);
      },
      goTab(){
        var pages = ['/productStock/checkstock','/productStock/checkstock/input','/productStock/checkstock/audit'];
        this.goPage(pages[this.tabPage-1]);
      },
      setCheck() {
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        this.setCheckTo(selection,1);
      },
      setUnCheck() {
        var selection = this.$refs.page.getSelection();
        if(selection.length == 0){
          this.$Message.error('请选择要操作的数据行');
          return;
        }
        this.setCheckTo(selection,0);
      },
      setCheckTo(rows,checkStatus){
        var url = '/api/productStock/check/batch';
        var data = [];
        rows.map((row)=>{
          data.push(Object.assign({},row,{checkStatus:checkStatus}));
        });
        this.loading = 1;
        this.$http.post(url, data).then((res) => {
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
      checkSubmit(){
        if(this.queryForm.storageId == ''){
          this.$Message.error('请选择仓库');
          return;
        }

        this.$Modal.confirm({
            title: '询问',
            content: '<p>确认要提交盘点数据吗？</p>',
            onOk: () => {
              this.$http.post('/api/productStock/check/submit?storageId=' + this.queryForm.storageId, {}).then((res) => {
                if (res.data.code === 0) {
                  this.$Message.success("操作成功！");
                  this.query();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                  this.$Message.error(error.toString())
              });
            }
        })
      },
      goPage(url){
        this.$router.push(url);
      }
    }
  }

</script>

<style type="text/css">
   
</style>
